package com.wyt.headfirst.lambda;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/3.
 */
@FunctionalInterface
public interface IntHandler {

    void run();

    default void handle(int i) {

    }
    default void handle1(int i) {

    }

}
