package com.universe.origin.star.special.sort;

import java.util.Arrays;

public class sort {

    /**
     * 冒泡排序
     * 循环找到最小（大）的元素交换位置
     *
     * @param arr
     * @return 原地排序
     * 稳定的排序算法
     * 最好情况0（n）
     * 最坏情况o(n*n)
     */
    public static Integer[] bubbleSort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] > arr[j - 1]) {
                    Integer temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 插入排序，循环未排序期间。将值与已排序期间进行比较，已排序期间向后移动
     * 是稳定的算法 遍历已经排序的区域将元素插入
     *
     * @param arr
     * @return
     */
    public static Integer[] insertSort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Integer value = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (value > arr[j]) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
                arr[j] = value;
            }
        }
        return arr;
    }

    /**
     * 同样分为排序期间和未排序期间
     * 是不稳定的算法，当两个相同元素时候选择其中一个优先插入
     * 在未排序期间选择最大（小的元素插入已经排序的末尾）
     *
     * @param arr
     * @return
     */
    public static Integer[] selectSort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {

            Integer temp = arr[i];
            Integer max = i;
            for (Integer j = i + 1; j < arr.length; j++) {
                if (arr[j] > temp) {
                    temp = arr[j];
                    max = j;
                }
            }
            Integer temp2 = arr[max];
            arr[max] = arr[i];
            arr[i] = temp2;
        }
        return arr;
    }

    /**
     * 合并排序
     * 分区递归 逐步有序对应合并后的整体有序   排序部分
     *
     * @param arr
     * @return
     */
    public static Integer[] mergeSort(Integer[] arr) {
        merge(0, arr.length - 1, arr);
        return arr;

    }

    public static Integer[] sort(Integer[] arr, int low, int height) {
        int mid = (low + height) / 2;
        if (low < height) {
            sort(arr, low, mid);
            sort(arr, mid + 1, height);
            merge(arr, low, mid, height);
        }
        return arr;
    }

    public static void merge(Integer[] arr, Integer low, Integer mid, Integer height) {
        int[] temp = new int[height - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= height) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        //插入左边剩余的
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        //插入右边剩余的
        while (j <= height) {
            temp[k++] = arr[j++];
        }

        //临时数组copy到原数组
        for (int x = 0; x < temp.length; x++) {
            arr[x + low] = temp[x];
        }
    }

    public static void merge(Integer p, Integer r, Integer[] arr) {
        if (p == r) {
            return;
        }
        Integer q = (p + r) / 2;
        merge(p, q, arr);
        merge(q + 1, r, arr);

        //合并两个部分
        Integer[] temp = new Integer[r - p + 1];
        int z = p;
        int k = q + 1;
        int temps = 0;
        for (int i = 0; i < r - p + 1; i++) {
            temps = i;
            if (arr[z] > arr[k]) {
                temp[i] = arr[z];
                if (z == q) {
                    break;
                }
                z++;
            } else {
                temp[i] = arr[k];

                if (k == r) {
                    break;
                }
                k++;
            }
        }

        //剩下的赋值
        if (k != r) {
            Integer length = r - k + 1;
            for (int i = 1; i <= length; i++) {
                temp[temps + i] = arr[k];
                k++;
            }
        } else {
            Integer length = q - z + 1;
            for (int i = 1; i <= length; i++) {
                temp[temps + i] = arr[z];
                z++;
            }
        }

        //temp 赋值到arr
        for (int i = 0; i < temp.length; i++) {
            arr[p] = temp[i];
            p++;
        }
    }

    public static Integer[] quickSort(Integer[] arr) {
        quickSortPartition(arr, 0, arr.length - 1);
        return arr;
    }

    /**
     * 分区
     *
     * @param arr
     * @param low
     * @param height
     */
    public static void quickSortPartition(Integer[] arr, Integer low, Integer height) {
        if (low >= height) {
            return;
        }
        Integer i = low;
        Integer j = height;
        Integer temp = arr[low];
        while (i < j) {
            //向左寻找大于temp的从起始点相反的方向查找
            while (arr[j] <= temp && j > i) {
                j--;
            }
            //向右寻找小于temp 的元素
            while (arr[i] >= temp && i < j) {
                i++;
            }
            if (i < j) {
                Integer temp2 = arr[i];
                arr[i] = arr[j];
                arr[j] = temp2;
            }
        }
        //交换最后一个元素
        arr[low] = arr[i];
        arr[i] = temp;
        quickSortPartition(arr, low, i - 1);
        quickSortPartition(arr, i + 1, height);
    }

    public static void main(String[] args) {
        System.out.println(1 / 2);
        Integer[] arr = new Integer[]{5, 2, 3, 3, 5, 1, 4, 58, 7, 4, 6, 1};
        arr = quickSort(arr);
        System.out.println(Arrays.asList(arr));

    }
}
