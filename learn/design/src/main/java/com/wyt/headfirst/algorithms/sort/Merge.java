package com.wyt.headfirst.algorithms.sort;

/**
 * @author Darcy
 * Created By Darcy on 2017/8/24 上午11:43
 */
public class Merge<T> extends BaseSort<T> {
    private Comparable<T>[] aux;


    private void merge(Comparable<T>[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    @Override
    public void sort(Comparable<T>[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);

    }

    private void sort(Comparable<T>[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void main(String[] args) {
        String[] a = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        BaseSort<String> sort = new Merge<>();
        sort.sort(a);
        sort.show(a);
    }
}
