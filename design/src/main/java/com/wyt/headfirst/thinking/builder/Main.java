package com.wyt.headfirst.thinking.builder;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/28.
 */
public class Main {
    public static void main(String[] args) {
        String flag = "html";
        if ("plain".equals(flag)) {
            TextBuilder textBuilder = new TextBuilder();
            Director director = new Director(textBuilder);
            director.construct();
            String result = textBuilder.getResult();
            System.out.println(result);
        } else if ("html".equals(flag)) {
            HTMLBuilder htmlBuilder = new HTMLBuilder();
            Director director = new Director(htmlBuilder);
            director.construct();
            String result = htmlBuilder.getResult();
            System.out.println(result + " 文件编写完成.");
        } else {
            usage();
            System.exit(0);
        }

    }

    public static void usage() {
        System.out.println("Usage: java Main plain    编写纯文本文档 ");
        System.out.println("Usage: java Main html    编写HTML文档 ");
    }
}
