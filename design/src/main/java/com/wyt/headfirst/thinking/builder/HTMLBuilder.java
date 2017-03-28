package com.wyt.headfirst.thinking.builder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 模板方法模式实现类
 * @author Darcy
 *         Created by Administrator on 2017/3/28.
 */
public class HTMLBuilder extends Builder {
    private String filename;
    private PrintWriter writer;

    /**
     * 编写标题
     *
     * @param title 文章标题
     */
    @Override
    public void makeTitle(String title) {
        filename ="E:\\Idea\\"+ title + ".html";
        try {
            writer = new PrintWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println("<html><head><title>" + title + "</title></head><body>");
        writer.println("<h1>" + title + "</h1>");
    }

    /**
     * 编写字符串
     *
     * @param str 字符串
     */
    @Override
    public void makeString(String str) {
        writer.println("<p>" + str + "</p>");
    }

    /**
     * 编写条目
     *
     * @param items 条目
     */
    @Override
    public void makeItems(String[] items) {
        writer.println("<ul>");
        for (int i = 0; i < items.length; i++) {
            writer.println("<li>" + items[i] + "</li>");
        }
        writer.println("</ul>");
    }

    /**
     * 完成文档编写的方法
     */
    @Override
    public void close() {
        writer.println("</body></html>");
        writer.close();
    }

    public String getResult() {
        return filename;
    }
}
