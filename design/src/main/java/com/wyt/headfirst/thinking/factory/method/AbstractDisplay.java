package com.wyt.headfirst.thinking.factory.method;

/**
 * 模板方法模式,就是父类抽象方法,子类去实现具体行为.如(open,print,close)
 * 父类编写的算法(display) 可以复用.
 * 父类定义具体的流程,子类负责具体行为
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/21.
 */
public abstract class AbstractDisplay {
    /**
     * 只能继承关系和同一程序包的类调用
     */
    protected abstract void open();
    /**
     * 只能继承关系和同一程序包的类调用
     */
    protected abstract void print();
    /**
     * 只能继承关系和同一程序包的类调用
     */
    protected abstract void close();

    /**
     * final 修饰就是为了子类不能重写这个方法
     */
    public final void display() {
        open();
        for (int i = 0; i < 5; i++) {
            print();
        }
        close();
    }
}
