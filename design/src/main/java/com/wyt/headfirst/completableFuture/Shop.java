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

    private Random random = new Random();

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    /**
     * 同步计算商品价格的方法
     *
     * @param product 商品名称
     * @return 价格
     */
    private double calculatePrice(String product) {
        delay();
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
