package com.wyt.headfirst.thinking.prototype;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/24.
 */
public interface Product extends Cloneable {
    void use(String s);
    Product createClone();

}
