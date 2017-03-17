package com.wyt.headfirst.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

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
        long start = System.nanoTime();
        System.out.println(findprices("myPhones27s"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

    }

    /**
     * 最佳价格查询器
     *
     * @param product 商品
     * @return
     */
    public static List<String> findprices(String product) {
        return shops
                .stream()
                .map(shop ->  shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
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

}
