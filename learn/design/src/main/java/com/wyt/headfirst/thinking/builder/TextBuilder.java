package com.wyt.headfirst.thinking.builder;

/**
 * 模板方法模式实现类
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/28.
 */
public class TextBuilder extends Builder {
    private StringBuffer buffer = new StringBuffer();

    /**
     * 编写标题
     *
     * @param title 文章标题
     */
    @Override
    public void makeTitle(String title) {
        buffer.append("===========================\n");
        buffer.append(" [ ").append(title).append(" ]\n");
        buffer.append("\n");

    }

    /**
     * 编写字符串
     *
     * @param str 字符串
     */
    @Override
    public void makeString(String str) {
        buffer.append(" # ").append(str).append("\n");
        buffer.append("\n");

    }

    /**
     * 编写条目
     *
     * @param items 条目
     */
    @Override
    public void makeItems(String[] items) {
        for (int i = 0; i < items.length; i++) {
            buffer.append(" ~").append(items[i]).append("\n");
        }
        buffer.append("\n");
    }

    /**
     * 完成文档编写的方法
     */
    @Override
    public void close() {
        buffer.append("===========================\n");
    }

    public String getResult() {
        return buffer.toString();
    }
}
