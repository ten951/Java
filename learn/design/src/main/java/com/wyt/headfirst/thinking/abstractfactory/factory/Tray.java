package com.wyt.headfirst.thinking.abstractfactory.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * 含有多个Link类和Tray类的容器
 * @author Darcy
 *         Created by Administrator on 2017/3/28.
 */
public abstract class Tray extends Item {
    protected List<Item> trays = new ArrayList<>();

    public Tray(String caption) {
        super(caption);
    }

    /**
     * 将link类和Tray类集合在一起
     * @param item 实体
     */
    public  void add(Item item) {
        trays.add(item);
    }
}
