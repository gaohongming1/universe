package com.universe.origin.star.special.divide;

import java.util.Arrays;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array = new int[]{60, 17, 39, 15, 8, 34, 30, 45, 5, 52, 25};
        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.binarySearch(17, array));
        Arrays.sort(array);
        System.out.println(binarySearch.recursionBinarySearch(17, array, 0, 16));
    }


    // 二分循环方式
    public int binarySearch(int x, int[] array) {
        if (array.length == 0) {
            return -1;
        }

        if (array.length == 1) {
            return array[0] == x ? 1 : -1;
        }

        int low = 0;
        int high = array.length - 1;
        Arrays.sort(array);
        while (low <= high) {
            // 中间由low + (high - low)/2 得到
            int middle = (high + low) / 2;
            if (array[middle] == x) {
                return middle;
            }

            //当到达一方的边界的时候 会触发middle = 边界值，循环被打破
            if (array[middle] > x) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    public int recursionBinarySearch(int x, int[] array, int low, int high) {
        if (array.length == 0 || low > high) {
            return -1;
        }

        if (array.length == 1) {
            return array[0] == x ? 1 : -1;
        }

        //进行递归
        int middle = (low + high) / 2;
        if (array[middle] == x) {
            return middle;
        }

        if (array[middle] > x) {
            return recursionBinarySearch(x, array, low, middle - 1);
        } else {
            return recursionBinarySearch(x, array, middle + 1, high);
        }

    }

}
