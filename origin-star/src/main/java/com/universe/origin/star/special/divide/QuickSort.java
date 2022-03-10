package com.universe.origin.star.special.divide;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = new int[]{60, 17, 39, 15, 8, 34, 30, 45, 5, 52, 25, 5, 30, 20, 1, 1, 2, 2, 4, 6, 8, 3};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }


    public void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int middle = partition(array, low, high);
            quickSort(array, low, middle - 1);
            quickSort(array, middle + 1, high);
        }
    }


    /**
     * 先从左边扫描，左右指针相遇时停留的位置是大的子数组的第一个数，所以右指针要左移一位才能到小的子数组最后一个，比基准数小，和基准数交换后才符合快排规则
     * <p>
     * 先从右边扫描，左右指针相遇时停留的位置是小的子数组的最后一个数，左右指针不用移动，直接和基准数交换符合快排规则
     */
    public int partition(int[] array, int low, int high) {
        //选择中间位置进行交换
        int i = low;
        int j = high;
        int index = (low + high) / 2;
        int base = array[index];

        while (i < j) {
            //向右扫描找到一个小于等于base的
            while (i < j && array[j] > base) {
                j--;
            }

            //和i下标的元素进行交换
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }

            //向左扫描找到一个大于base的
            while (i < j && array[i] <= base) {
                i++;
            }

            //和j下标的元素交换
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                j--;
            }
        }

        return i;
    }
}
