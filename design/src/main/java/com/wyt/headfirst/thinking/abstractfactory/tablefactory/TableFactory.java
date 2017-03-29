package com.wyt.headfirst.thinking.abstractfactory.tablefactory;

import com.wyt.headfirst.thinking.abstractfactory.factory.Factory;
import com.wyt.headfirst.thinking.abstractfactory.factory.Link;
import com.wyt.headfirst.thinking.abstractfactory.factory.Page;
import com.wyt.headfirst.thinking.abstractfactory.factory.Tray;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/29.
 */
public class TableFactory extends Factory {
    @Override
    public Link createLink(String caption, String url) {
        return new TableLink(caption, url);
    }

    @Override
    public Tray createTray(String caption) {
        return new TableTray(caption);
    }

    @Override
    public Page createPage(String title, String author) {
        return new TablePage(title, author);
    }
}
