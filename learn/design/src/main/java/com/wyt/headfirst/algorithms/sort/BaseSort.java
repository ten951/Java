package com.wyt.headfirst.algorithms.sort;

/**
 * @author Darcy
 * Created By Darcy on 2017/8/22 下午6:28
 */
public abstract class BaseSort<T> {

    public abstract void sort(Comparable<T>[] a);

    public boolean less(Comparable<T> v, Comparable<T> w) {
        return v.compareTo((T) w) < 0;
    }

    public void exch(Comparable<T>[] a, int i, int j) {
        Comparable<T> t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public void show(Comparable<T>[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i].toString());
        }
    }

    public boolean isSorted(Comparable<T>[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

}
