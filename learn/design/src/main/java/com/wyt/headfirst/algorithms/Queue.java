package com.wyt.headfirst.algorithms;

import java.util.Iterator;

/**
 * 基于链表实现队列
 *
 * @author Darcy
 * Created By Darcy on 2017/8/22 下午1:56
 */
public class Queue<T> implements Iterable<T> {
    private Node first;//队列开头
    private Node last;//队列末尾
    private int N;//队列长度

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

    public void enqueue(T t) {
        Node oldlast = last;
        last = new Node();
        last.t = t;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public T dequeue() {
        T t = first.t;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {
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
