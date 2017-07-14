package com.wyt.headfirst.thinking.abstractfactory.factory;

/**
 * Link类和Tray类的父类,这样link类和Tray类就具有替换性
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/28.
 */
public abstract class Item {
    protected String caption;//标题

    public Item(String caption) {
        this.caption = caption;
    }

    public abstract String makeHTML();
}
