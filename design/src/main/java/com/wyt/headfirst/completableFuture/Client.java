package com.wyt.headfirst.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/17.
 */
public class Client {

    private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100));

    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
         /*long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long incocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("执行时间:" + incocationTime + " msecs");
        try {
            Double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("retrievalTime:" + retrievalTime + " msecs");*/
        //System.out.println(findPrices("myPhones27s"));
        long start = System.nanoTime();
        CompletableFuture[] futures = findProcesStream("myPhones27s")
                //Java 8的CompletableFuture通过thenAccept方法  他接收CompletableFuture执行完毕的返回值作为参数.
                .map(f -> f.thenAccept(
                        s -> System.out.println(s + " (done in " +
                                ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                .toArray(CompletableFuture[]::new);
        //allOf工厂方法接收一个由CompletableFuture构成的数组,数组中所有的CompletableFuture对象执行完毕后,它返回一个
        //CompletableFuture<Void>对象,这意味着你需要等待最初Stream中所有的CompletableFuture对象执行完毕
        //angOf该方法接收一个CompletableFuture对象构成的数组,返回由第一个执行完毕的CompletableFuture对象的返回值构成的CompletableFuture<Object>
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in  " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
      /*  long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");*/

    }

    /**
     * 商店折扣价格查询器
     *
     * @param product 商品
     * @return
     */
    public static List<String> findprices(String product) {
        return shops
                .parallelStream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    /**
     * 商店折扣价格查询器(CompletableFuture方式)
     *
     * @param product 商品
     * @return
     */
    public static List<String> findPrices(String product) {
        List<CompletableFuture<String>> collect = shops
                .stream()
                //以异步凡是取得每个shop中指定产品的原始价格
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                //Quote对象存在时,对其返回值进行转换
                .map(future -> future.thenApply(Quote::parse))
                //使用另一个异步任务构建期望的Future,申请折扣 thenCompose 将多个future组合 一个一个执行
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());
        return collect
                .stream()
                //等待流中所有的future执行完毕,并提取各自的返回值
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 最佳价格查询器(并行流)
     *
     * @param product 商品
     * @return
     */
    public static List<String> parallelFindprices(String product) {
        return shops
                .parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 最佳价格查询器(异步调用实现)
     *
     * @param product 商品
     * @return
     */
    public static List<String> asyncFindprices(String product) {
        //使用这种方式,你会得到一个List<CompletableFuture<String>>,列表中的每一个CompletableFuture对象在计算完成后都包含商店的String类型的名称.
        //但是,由于你用CompletableFuture实现了asyncFindprices方法要求返回一个List<String>.你需要等待所有的future执行完毕,将其包含的值抽取出来,填充到列表中才能返回
        List<CompletableFuture<String>> priceFuture = shops
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))))
                .collect(Collectors.toList());
        //为了实现这个效果,我门可以向最初的List<CompletableFuture<String>>施加第二个map操作,对list中的每一个future对象执行join操作,一个接一个地等待他们允许结束,join和get方法
        //有相同的含义,不同的在于join不会抛出任何检测到的异常
        return priceFuture
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 最佳价格查询器(异步调用实现,自定义执行器)
     *
     * @param product 商品
     * @return
     */
    public static List<String> asyncFindpricesThread(String product) {
        List<CompletableFuture<String>> priceFuture = shops
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product), executor))
                .collect(Collectors.toList());
        return priceFuture
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 重构findPrices方法 返回一个由Future构成的流
     *
     * @param product 商品
     * @return
     */
    public static Stream<CompletableFuture<String>> findProcesStream(String product) {
        return shops
                .stream()
                //以异步凡是取得每个shop中指定产品的原始价格
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                //Quote对象存在时,对其返回值进行转换
                .map(future -> future.thenApply(Quote::parse))
                //使用另一个异步任务构建期望的Future,申请折扣 thenCompose 将多个future组合 一个一个执行
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }


}
