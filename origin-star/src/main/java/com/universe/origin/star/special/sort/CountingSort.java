package com.universe.origin.star.special.sort;

import java.util.ArrayList;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = new int[]{4,3,1,6,8,9,4,3,7,2,7};
        countingSort(arr);

    }
    public static int[] countingSort(int[] arr){
        int max = arr[0];
        int min=arr[0];
        for (int i = 0; i <arr.length ; i++) {

            if (arr[i]>max){
                max = arr[i];
            }

            if (arr[i]<min){
                min = arr[i];
            }
        }

        //找到区间
        int length = max - min;

        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        for (int i = 0; i <= length; i++) {
            temp.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] - min;
            ArrayList<Integer> queue = temp.get(index);
            queue.add(arr[i]);
        }
        System.out.println("排序完成");
        return null;
    }
}
