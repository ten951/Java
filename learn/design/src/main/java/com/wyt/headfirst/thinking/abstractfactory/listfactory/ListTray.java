package com.wyt.headfirst.thinking.abstractfactory.listfactory;

import com.wyt.headfirst.lambda.Track;
import com.wyt.headfirst.thinking.abstractfactory.factory.Item;
import com.wyt.headfirst.thinking.abstractfactory.factory.Tray;

import java.util.Iterator;

/**
 * ListTray和Item  就是典型的模板方法模式
 * ListTray是Tray的装饰器,
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/28.
 */
public class ListTray extends Tray {

    public ListTray(String caption) {
        super(caption);
    }

    @Override
    public String makeHTML() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<li>\n");
        buffer.append(caption).append("\n");
        buffer.append("<ul>\n");
        for (Item item : trays) {
            buffer.append(item.makeHTML());
        }
        buffer.append("</ul>\n");
        buffer.append("</li>\n");
        return buffer.toString();
    }
}
