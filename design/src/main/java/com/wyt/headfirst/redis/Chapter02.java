package com.wyt.headfirst.redis;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Chapter02 {
    public static final void main(String[] args)
            throws InterruptedException {
        new Chapter02().run();
    }

    public void run()
            throws InterruptedException {
        Jedis conn = new Jedis("localhost");
        conn.select(15);

        testLoginCookies(conn);//登录检查
        testShopppingCartCookies(conn);//购物车
        testCacheRows(conn);//数据行缓存
        testCacheRequest(conn);//页面分析
    }

    public void testLoginCookies(Jedis conn)
            throws InterruptedException {
        System.out.println("\n----- testLoginCookies -----");
        String token = UUID.randomUUID().toString();//生成token

        updateToken(conn, token, "username", "itemX");//更新token信息和记录用户浏览的商品记录
        System.out.println("We just logged-in/updated token: " + token);
        System.out.println("For user: 'username'");
        System.out.println();

        System.out.println("What username do we get when we look-up that token?");
        String r = checkToken(conn, token);//验证是否登录
        System.out.println(r);
        System.out.println();
        assert r != null;

        System.out.println("Let's drop the maximum number of cookies to 0 to clean them out");
        System.out.println("We will start a thread to do the cleaning, while we stop it later");

        CleanSessionsThread thread = new CleanSessionsThread(0);//清理会话线程 保证会话为100W个
        thread.start();
        Thread.sleep(1000);
        thread.quit();
        Thread.sleep(2000);
        if (thread.isAlive()) {
            throw new RuntimeException("The clean sessions thread is still alive?!?");
        }

        long s = conn.hlen("login:");
        System.out.println("The current number of sessions still available is: " + s);
        assert s == 0;
    }

    public void testShopppingCartCookies(Jedis conn)
            throws InterruptedException {
        System.out.println("\n----- testShopppingCartCookies -----");
        String token = UUID.randomUUID().toString();

        System.out.println("We'll refresh our session...");
        updateToken(conn, token, "username", "itemX");//关联用户和token 记录浏览商品 和浏览的次数
        System.out.println("And add an item to the shopping cart");
        addToCart(conn, token, "itemY", 3);//商品添加到购物车
        Map<String, String> r = conn.hgetAll("cart:" + token);//根据token 取出购物车中所有的商品
        System.out.println("Our shopping cart currently has:");
        for (Map.Entry<String, String> entry : r.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();

        assert r.size() >= 1;

        System.out.println("Let's clean out our sessions and carts");
        CleanFullSessionsThread thread = new CleanFullSessionsThread(0);//启动定期清理会话的线程
        thread.start();
        Thread.sleep(1000);
        thread.quit();
        Thread.sleep(2000);
        if (thread.isAlive()) {
            throw new RuntimeException("The clean sessions thread is still alive?!?");
        }

        r = conn.hgetAll("cart:" + token);
        System.out.println("Our shopping cart now contains:");
        for (Map.Entry<String, String> entry : r.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        assert r.size() == 0;
    }

    public void testCacheRows(Jedis conn)
            throws InterruptedException {
        System.out.println("\n----- testCacheRows -----");
        System.out.println("First, let's schedule caching of itemX every 5 seconds");
        scheduleRowCache(conn, "itemX", 5);
        System.out.println("Our schedule looks like:");
        Set<Tuple> s = conn.zrangeWithScores("schedule:", 0, -1);
        for (Tuple tuple : s) {
            System.out.println("  " + tuple.getElement() + ", " + tuple.getScore());
        }
        assert s.size() != 0;

        System.out.println("We'll start a caching thread that will cache the data...");

        CacheRowsThread thread = new CacheRowsThread();
        thread.start();

        Thread.sleep(1000);
        System.out.println("Our cached data looks like:");
        String r = conn.get("inv:itemX");
        System.out.println(r);
        assert r != null;
        System.out.println();

        System.out.println("We'll check again in 5 seconds...");
        Thread.sleep(5000);
        System.out.println("Notice that the data has changed...");
        String r2 = conn.get("inv:itemX");
        System.out.println(r2);
        System.out.println();
        assert r2 != null;
        assert !r.equals(r2);

        System.out.println("Let's force un-caching");
        scheduleRowCache(conn, "itemX", -1);
        Thread.sleep(1000);
        r = conn.get("inv:itemX");
        System.out.println("The cache was cleared? " + (r == null));
        assert r == null;

        thread.quit();
        Thread.sleep(2000);
        if (thread.isAlive()) {
            throw new RuntimeException("The database caching thread is still alive?!?");
        }
    }

    public void testCacheRequest(Jedis conn) {
        System.out.println("\n----- testCacheRequest -----");
        String token = UUID.randomUUID().toString();//随机生成token

        Callback callback = request -> "content for " + request;

        updateToken(conn, token, "username", "itemX");//关联token和user 添加最后一次登录的时间 添加浏览的商品记录
        String url = "http://test.com/?item=itemX";
        System.out.println("We are going to cache a simple request against " + url);
        String result = cacheRequest(conn, url, callback);
        System.out.println("We got initial content:\n" + result);
        System.out.println();

        assert result != null;

        System.out.println("To test that we've cached the request, we'll pass a bad callback");
        String result2 = cacheRequest(conn, url, null);
        System.out.println("We ended up getting the same response!\n" + result2);

        assert result.equals(result2);

        assert !canCache(conn, "http://test.com/");
        assert !canCache(conn, "http://test.com/?item=itemX&_=1234536");
    }

    public String checkToken(Jedis conn, String token) {
        return conn.hget("login:", token);//获取散列中键的值
    }

    public void updateToken(Jedis conn, String token, String user, String item) {
        long timestamp = System.currentTimeMillis() / 1000;//获取10位时间戳
        conn.hset("login:", token, user);//关联散列中的token和用户
        conn.zadd("recent:", timestamp, token);//在有序集合中 记录token出现的最后记录
        if (item != null) {
            conn.zadd("viewed:" + token, timestamp, item);//记录用户浏览的商品 按时间
            conn.zremrangeByRank("viewed:" + token, 0, -26);//保证有序集合中的记录 只有25条
            conn.zincrby("viewed:", -1, item);//记录商品浏览的次数 当viewed:不存在时 创建这个集合
        }
    }

    public void addToCart(Jedis conn, String session, String item, int count) {
        if (count <= 0) {//当商品数量小于等于0的时候 删除商品在购物车中
            conn.hdel("cart:" + session, item);
        } else {//否则 添加商品到购物车中
            conn.hset("cart:" + session, item, String.valueOf(count));
        }
    }

    public void scheduleRowCache(Jedis conn, String rowId, int delay) {
        conn.zadd("delay:", delay, rowId);//行id 和 延迟时间 添加到有序集合中
        conn.zadd("schedule:", System.currentTimeMillis() / 1000, rowId);//行id 和 何时执行缓存的时间戳
    }

    public String cacheRequest(Jedis conn, String request, Callback callback) {
        if (!canCache(conn, request)) {//判断是否是否能缓存
            return callback != null ? callback.call(request) : null;
        }

        String pageKey = "cache:" + hashRequest(request);//拼接缓存的key
        String content = conn.get(pageKey);//通过key 环球缓存的字符串

        if (content == null && callback != null) {
            content = callback.call(request);
            conn.setex(pageKey, 300, content);//给这个集合设置内容 和 延迟时间
        }

        return content;
    }

    public boolean canCache(Jedis conn, String request) {
        try {
            URL url = new URL(request);
            HashMap<String, String> params = new HashMap<String, String>();
            if (url.getQuery() != null) {
                for (String param : url.getQuery().split("&")) {
                    String[] pair = param.split("=", 2);
                    params.put(pair[0], pair.length == 2 ? pair[1] : null);
                }
            }

            String itemId = extractItemId(params);
            if (itemId == null || isDynamic(params)) {
                return false;
            }
            Long rank = conn.zrank("viewed:", itemId);//查看该商品的浏览数 排行
            return rank != null && rank < 10000;//限制 只缓存前排名前1000的商品
        } catch (MalformedURLException mue) {
            return false;
        }
    }

    public boolean isDynamic(Map<String, String> params) {
        return params.containsKey("_");
    }

    public String extractItemId(Map<String, String> params) {
        return params.get("item");
    }

    public String hashRequest(String request) {
        return String.valueOf(request.hashCode());//将int类型 转化为String
    }

    public interface Callback {
        String call(String request);
    }

    public class CleanSessionsThread
            extends Thread {
        private Jedis conn;
        private int limit;
        private boolean quit;

        public CleanSessionsThread(int limit) {
            this.conn = new Jedis("localhost");
            this.conn.select(15);
            this.limit = limit;
        }

        public void quit() {
            quit = true;
        }

        public void run() {
            while (!quit) {
                long size = conn.zcard("recent:");//计算结婚recent:的元素个数
                if (size <= limit) {//当集合个数小于限制个数时
                    try {
                        sleep(1000);//主线程睡眠1秒
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    continue;
                }

                long endIndex = Math.min(size - limit, 100);//找到最小的数,
                Set<String> tokenSet = conn.zrange("recent:", 0, endIndex - 1);//获取集合从头到endindex的集合
                String[] tokens = tokenSet.toArray(new String[tokenSet.size()]);//将set装换为数组

                ArrayList<String> sessionKeys = new ArrayList<String>();//
                for (String token : tokens) {//遍历旧的token
                    sessionKeys.add("viewed:" + token);//拼接用户浏览商品集合的key
                }

                conn.del(sessionKeys.toArray(new String[sessionKeys.size()]));//浏览商品集合
                conn.hdel("login:", tokens);//删除散列中的给定键
                conn.zrem("recent:", tokens);//删除有序集合的给定成员
            }
        }
    }

    public class CleanFullSessionsThread
            extends Thread {
        private Jedis conn;//jedis链接
        private int limit;//会话限制的个数
        private boolean quit;//是否限制 开关

        public CleanFullSessionsThread(int limit) {
            this.conn = new Jedis("localhost");
            this.conn.select(15);
            this.limit = limit;
        }

        public void quit() {
            quit = true;
        }

        public void run() {
            while (!quit) {
                long size = conn.zcard("recent:");//获取recent:集合中的元素个数
                if (size <= limit) {//元素个数小于限制个数时
                    try {
                        sleep(1000);//当前线程睡眠一秒
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    continue;
                }

                long endIndex = Math.min(size - limit, 100);//获取需要移除的令牌个数
                Set<String> sessionSet = conn.zrange("recent:", 0, endIndex - 1);//获取需要期初的令牌
                String[] sessions = sessionSet.toArray(new String[sessionSet.size()]);//将set装换为数组

                ArrayList<String> sessionKeys = new ArrayList<String>();
                for (String sess : sessions) {//组装删除几个需要的数据结构
                    sessionKeys.add("viewed:" + sess);
                    sessionKeys.add("cart:" + sess);
                }

                conn.del(sessionKeys.toArray(new String[sessionKeys.size()]));//删除浏览商品集合 删除购物车集合
                conn.hdel("login:", sessions);//删除给定token的散列键值对
                conn.zrem("recent:", sessions);//删除有序集合中给定成员
            }
        }
    }

    public class CacheRowsThread
            extends Thread {
        private Jedis conn;
        private boolean quit;

        public CacheRowsThread() {
            this.conn = new Jedis("localhost");
            this.conn.select(15);
        }

        public void quit() {
            quit = true;
        }

        public void run() {
            Gson gson = new Gson();
            while (!quit) {
                Set<Tuple> range = conn.zrangeWithScores("schedule:", 0, 0);//获取下一个需要缓存的数据行的元组(包含0个或者1个元素)
                Tuple next = range.size() > 0 ? range.iterator().next() : null;
                long now = System.currentTimeMillis() / 1000;//获取当前时间的时间戳
                if (next == null || next.getScore() > now) {//如果时间没到 主线程就睡眠.5秒
                    try {
                        sleep(50);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    continue;
                }

                String rowId = next.getElement();//提取集合中的成员
                double delay = conn.zscore("delay:", rowId);//通过成员 获取分值
                if (delay <= 0) {//如果延迟时间小于等于0
                    conn.zrem("delay:", rowId);//删除这个元素 在延迟集合中
                    conn.zrem("schedule:", rowId);//删除这个元素 在执行缓存的集合中
                    conn.del("inv:" + rowId);
                    continue;
                }

                Inventory row = Inventory.get(rowId);//获取需要缓存数据
                conn.zadd("schedule:", now + delay, rowId);//设置下一次缓存的时间
                conn.set("inv:" + rowId, gson.toJson(row));//缓存数据 json格式
            }
        }
    }

    public static class Inventory {
        private String id;
        private String data;
        private long time;

        private Inventory(String id) {
            this.id = id;
            this.data = "data to cache...";
            this.time = System.currentTimeMillis() / 1000;
        }

        public static Inventory get(String id) {
            return new Inventory(id);
        }
    }
}
