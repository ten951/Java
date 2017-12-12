package com.wyt.headfirst.algorithms.sort;

/**
 * @author Darcy
 * Created By Darcy on 2017/8/23 下午2:55
 */
public class Shell<T> extends BaseSort<T> {
    @Override
    public void sort(Comparable<T>[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h &&
                        less(a[j], a[j - h]);
                     j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Integer[] a = {5, 36, 99, 1, 6, 8, 6, 3, 4, 13, 44, 46, 33, 22, 35, 53};
        BaseSort<Integer> sort = new Shell();
        sort.sort(a);
        sort.show(a);
    }
}
