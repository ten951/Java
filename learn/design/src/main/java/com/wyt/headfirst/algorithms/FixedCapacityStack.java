package com.wyt.headfirst.algorithms;

/**
 * 抽象数据对象,固定大小的栈(会出现溢出)
 *
 * @param <T>
 * @author Darcy
 */
public class FixedCapacityStack<T> {

    private T[] a;
    private int N;

    public FixedCapacityStack(int cap) {
        a = (T[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(T item) {
        a[N++] = item;
    }

    public T pop() {
        return a[--N];
    }


}
