package com.wyt.headfirst.completableFuture;

import org.json4s.FullTypeHints;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 商店实体
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/17.
 */
public class Shop {
    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static Random random = new Random();

    //对应折扣服务 返回的价格格式
    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
                random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    //只返回商品价格
    public double getPriceDouble(String product) {
        return calculatePrice(product);
    }

    /**
     * 同步计算商品价格的方法
     *
     * @param product 商品名称
     * @return 价格
     */
    private double calculatePrice(String product) {
        randomDelay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 异步计算商品的价格.
     *
     * @param product 商品名称
     * @return 价格
     */
    public Future<Double> getPriceAsync(String product) {
       /* CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                //否则就抛出异常,完成这次future操作
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;*/

        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    /**
     * 模拟不同的商店 延迟不一样的情况
     */
    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 模拟计算,查询数据库等耗时
     */
    public static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
