package com.universe.origin.star.special.sort;

import java.util.ArrayList;
import java.util.List;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[]{4,3,1,6,8,9,4,3,7,2,7};
        radixSort(arr);
    }

    public static Integer[] radixSort(int[]arr){
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max<arr[i]){
                max = arr[i];
            }
        }
        //计算最大数的位数intint
        int times = 0;
        while (max>0){
            max = max/10;
            times++;
        }


        ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++) {
            temp.add(new ArrayList<Integer>());
        }

        //抽取各组数
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < arr.length; j++) {
                int x = arr[j]%(int)Math.pow(10,i+1)/(int)Math.pow(10,i);
                List s = temp.get(x);
                s.add(arr[j]);
            }

            //开始收集排序
            int count = 0;
            for (int j = 0; j < 10; j++) {
                while (temp.get(j).size()>0){
                    ArrayList<Integer> q = temp.get(j);
                    arr[count] = q.get(0);
                    q.remove(0);
                    count++;
                }
            }
        }
        System.out.println("排序完成");

        return null;
    }


}
