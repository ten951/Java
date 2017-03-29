package com.wyt.headfirst.thinking.abstractfactory.tablefactory;

import com.wyt.headfirst.thinking.abstractfactory.factory.Link;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/29.
 */
public class TableLink extends Link {
    public TableLink(String caption, String url) {
        super(caption, url);
    }

    @Override
    public String makeHTML() {
        return "<td><a href=\"" + url + "\">" + caption + "</a></td>\n";
    }
}
