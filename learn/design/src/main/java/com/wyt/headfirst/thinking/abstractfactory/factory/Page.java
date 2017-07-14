package com.wyt.headfirst.thinking.abstractfactory.factory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/28.
 */
public abstract class Page {
    protected String title;
    protected String author;
    protected List<Item> content = new ArrayList<>();

    public Page(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void add(Item item) {
        content.add(item);
    }

    public void output() {
        try {
            String filename = "E:\\Idea\\" + title + ".html";
            FileWriter writer = new FileWriter(filename);
            writer.write(makeHTML());
            writer.close();
            System.out.println(filename + " 编写完成. ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract String makeHTML();
}
