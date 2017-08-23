package com.wyt.headfirst.algorithms.sort;

/**
 * 插入排序
 * @author Darcy
 * Created By Darcy on 2017/8/22 下午6:38
 */
public class Selection extends BaseSort<Integer> {
    @Override
    public void sort(Comparable<Integer>[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] a = {5, 2, 9, 1, 6};
        BaseSort<Integer> sort = new Selection();
        sort.sort(a);
        sort.show(a);
    }
}
