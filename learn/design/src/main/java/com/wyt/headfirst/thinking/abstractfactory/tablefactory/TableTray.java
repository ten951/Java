package com.wyt.headfirst.thinking.abstractfactory.tablefactory;

import com.wyt.headfirst.thinking.abstractfactory.factory.Item;
import com.wyt.headfirst.thinking.abstractfactory.factory.Tray;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/29.
 */
public class TableTray extends Tray {
    public TableTray(String caption) {
        super(caption);
    }

    @Override
    public String makeHTML() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<td>");
        buffer.append("<table width='100%' border='1'><tr>");
        buffer.append("<td bgcolor='#cccccc' align='center' colspan=\"").append(trays.size()).append("\"><b>").append(caption).append("</b></td>");
        buffer.append("</tr>\n");
        buffer.append("<tr>\n");
        for (Item tray : trays) {
            buffer.append(tray.makeHTML());
        }
        buffer.append("</tr></table>");
        buffer.append("</td>");
        return buffer.toString();
    }
}
