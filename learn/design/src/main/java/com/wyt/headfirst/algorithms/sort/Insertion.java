package com.wyt.headfirst.algorithms.sort;

/**
 * 适合部分有序的数组或者小规模数组
 * 插入排序 索引之前的都是有序的,然后比较大小,向前移
 * @author Darcy
 * Created By Darcy on 2017/8/23 下午2:13
 */
public class Insertion<T> extends BaseSort<T> {
    @Override
    public void sort(Comparable<T>[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {5, 2, 9, 1, 6};
        BaseSort<Integer> sort = new Insertion();
        sort.sort(a);
        sort.show(a);
    }
}
