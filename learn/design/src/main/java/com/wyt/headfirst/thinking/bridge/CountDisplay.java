package com.wyt.headfirst.thinking.bridge;

/**
 * 在显示的基础上拓展了"只显示规定的次数"
 *
 * @author Darcy
 *         Created by Administrator on 2017/4/7.
 */
public class CountDisplay extends Display {
    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }

    public void multiDisplay(int times) {
        open();
        for (int i = 0; i < times; i++) {
            print();
        }
        close();
    }
}
