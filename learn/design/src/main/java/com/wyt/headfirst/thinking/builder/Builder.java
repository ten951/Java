package com.wyt.headfirst.thinking.builder;

/**
 * 需要builder编写出文档具有以下格式
 * 含有一个标题
 * 含有几个字符串
 * 含有条目项目
 * <p>
 * 编写文档方法的抽象类
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/28.
 */
public abstract class Builder {
    /**
     * 编写标题
     *
     * @param title 文章标题
     */
    public abstract void makeTitle(String title);

    /**
     * 编写字符串
     *
     * @param str 字符串
     */
    public abstract void makeString(String str);

    /**
     * 编写条目
     *
     * @param items 条目
     */
    public abstract void makeItems(String[] items);

    /**
     * 完成文档编写的方法
     */
    public abstract void close();


}
