package com.wyt.headfirst.algorithms;

import com.sun.istack.internal.NotNull;

import java.util.Iterator;

/**
 * 栈的实现(链表)
 * @author Darcy
 * Created By Darcy on 2017/8/22 下午1:38
 */
public class Stack<T> implements Iterable<T> {
    private Node first;
    private int N;

    private class Node {
        T t;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void push(T t) {
        Node oldfirst = first;
        first = new Node();
        first.t = t;
        first.next = oldfirst;
        N++;
    }

    public T pop() {
        T t = first.t;
        first = first.next;
        N--;
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T t = current.t;
            current = current.next;
            return t;
        }
    }
}
