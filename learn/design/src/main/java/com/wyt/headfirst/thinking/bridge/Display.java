package com.wyt.headfirst.thinking.bridge;

/**
 * 负责显示一些东西,该类位于类的功能层次结构的最上层
 * impl是实现了具体功能的实例 显示和实现分离,这样我们可以继承DisplayImpl 去实现各种各样的实现
 *
 * open print close这3个方法是Display类提供的接口,他们表示显示的步骤
 * @author Darcy
 *         Created by Administrator on 2017/4/7.
 */
public class Display {
    private DisplayImpl impl;

    public Display(DisplayImpl impl) {
        this.impl = impl;
    }

    public void open() {
        impl.rawOpen();
    }

    public void print() {
        impl.rawPrint();
    }

    public void close() {
        impl.rawClose();
    }

    public final void display() {
        open();
        print();
        close();
    }
}
