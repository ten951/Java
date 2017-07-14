package com.wyt.headfirst.thinking.abstractfactory.factory;

/**
 * 抽象地表示HTML的超链接类
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/28.
 */
public abstract class Link extends Item {
    protected String url;//超链接所表示的地址

    public Link(String caption, String url) {
        super(caption);
        this.url = url;
    }
}
