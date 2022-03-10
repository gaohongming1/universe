package com.universe.origin.star.special.divide;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] array = new int[]{60, 17, 39, 15, 8, 34, 30, 45, 5, 52, 25};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    public void sort(int[] array, int low, int high) {
        if (array.length == 1 || low >= high) {
            return;
        }
        int middle = (low + high) / 2;
        sort(array, low, middle);
        sort(array, middle + 1, high);
        merge(low, high, middle, array);
    }


    public void merge(int low, int high, int middle, int[] array) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = middle + 1;
        int k = 0;
        //递减排序
        while (i <= middle && j <= high) {
            if (array[i] > array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        // 合并剩余部分
        while (i <= middle) {
            temp[k++] = array[i++];
        }
        while (j <= high) {
            temp[k++] = array[j++];
        }

        // copy
        for (int x = 0; x < temp.length; x++) {
            array[x + low] = temp[x];
        }
    }
}
