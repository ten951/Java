package com.wyt.headfirst.thinking.bridge;

/**
 * CountDisplay拓展Display
 * StringDisplayImpl拓展DisplayImpl
 *
 * @author Darcy
 *         Created by Administrator on 2017/4/7.
 */
public class Main {
    public static void main(String[] args) {
        //DisplayImpl impl = new StringDisplayImpl("Hello, China.")
        Display d1 = new Display(new StringDisplayImpl("Hello, China."));
        Display d2 = new CountDisplay(new StringDisplayImpl("Hello, World."));
        CountDisplay d3 = new CountDisplay(new StringDisplayImpl("Hello, Universe."));
        d1.display();
        d2.display();
        d3.display();
        d3.multiDisplay(5);
    }
}
