package com.samsung.demo;

/**
 * Created by ji.zhang on 7/22/17.
 */
public class SortClass {
    public static int partition(int[] array, int lo, int hi) {
        int key = array[lo];
        while (lo < hi) {
            while (array[hi] >= key && hi > lo) {
                hi--;
            }
            array[lo] = array[hi];
            
            while (array[lo] <= key && hi > lo) {
                lo++;
            }
            array[hi] = array[lo];
        }
        array[hi] = key;
        return hi;
    }

    public static void qsort(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int index = partition(array, lo, hi);
        qsort(array, lo, index - 1);
        qsort(array, index + 1, hi);
    }

    public static void quickSort(int[] array) {
        qsort(array, 0, array.length - 1);
    }

    public static void main(String[] args) {

    }
}
