package com.wyt.headfirst.thinking.abstractfactory.tablefactory;

import com.wyt.headfirst.thinking.abstractfactory.factory.Item;
import com.wyt.headfirst.thinking.abstractfactory.factory.Page;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/29.
 */
public class TablePage extends Page {

    public TablePage(String title, String author) {
        super(title, author);
    }

    @Override
    public String makeHTML() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<html><head><title>").append(title).append("</title></head>\n");
        buffer.append("<body\n>");
        buffer.append("<h1>").append(title).append("</h1>\n");
        buffer.append("<table width='80%' border='3'>\n");
        for (Item item : content) {
            buffer.append("<tr>").append(item.makeHTML()).append("</tr>");
        }
        buffer.append("</table>\n");
        buffer.append("<hr><address>").append(author).append("</address>");
        buffer.append("</body></html>\n");
        return buffer.toString();
    }
}
