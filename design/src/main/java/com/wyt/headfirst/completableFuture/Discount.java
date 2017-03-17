package com.wyt.headfirst.completableFuture;

/**
 * 折扣服务api
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/17.
 */
public class Discount {
    public enum Code {
        NONE(0), SILVER(0), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " 商品原价: " + quote.getPrice() + " 折扣后价格: " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        delay();
        return price * (100 - code.percentage) / 100;
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
