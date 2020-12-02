package com.universe.origin.star.special.sort;

import java.util.Arrays;

public class shellSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3,4,5,2,7,8,9,6,3};
        System.out.println(Arrays.toString(sort(arr)));

    }

    public static int[] sort(int[] arr) {

        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j - gap >= 0 && arr[j] > arr[j - gap]){
                    swap(arr,j,j-gap);
                    j-=gap;
                }
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

}
