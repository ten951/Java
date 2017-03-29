package com.wyt.headfirst.thinking.abstractfactory.listfactory;

import com.wyt.headfirst.thinking.abstractfactory.factory.Item;
import com.wyt.headfirst.thinking.abstractfactory.factory.Page;

/**
 * ListPage和Page 是典型的模板方法模式
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/28.
 */
public class ListPage extends Page {
    public ListPage(String title, String author) {
        super(title, author);
    }

    @Override
    public String makeHTML() {
        StringBuilder builder = new StringBuilder();
        builder.append("<html><head><title>").append(title).append("</title></head>\n");
        builder.append("<body>\n");
        builder.append("<h1>").append(title).append("</h1>\n");
        builder.append("<ul>\n");
        for (Item item : content) {
            builder.append(item.makeHTML());
        }
        builder.append("</ul>\n");
        builder.append("<hr><address>").append(author).append("</address>");
        builder.append("</body></html>\n");
        return builder.toString();
    }
}
