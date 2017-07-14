package com.wyt.headfirst.thinking.abstractfactory.listfactory;

import com.wyt.headfirst.thinking.abstractfactory.factory.Factory;
import com.wyt.headfirst.thinking.abstractfactory.factory.Link;
import com.wyt.headfirst.thinking.abstractfactory.factory.Page;
import com.wyt.headfirst.thinking.abstractfactory.factory.Tray;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/28.
 */
public class ListFactory extends Factory {
    @Override
    public Link createLink(String caption, String url) {
        return new ListLink(caption, url);
    }

    @Override
    public Tray createTray(String caption) {
        return new ListTray(caption);
    }

    @Override
    public Page createPage(String title, String author) {
        return new ListPage(title, author);
    }
}
