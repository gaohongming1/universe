package com.universe.origin.star.leetcode.heap.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 *
 * 示例：
 *
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * 提示：
 *
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 *
 *
 */
public class TheMinKth1714 {
    public static void main(String[] args) {
        TheMinKth1714 theMinKth1714 = new TheMinKth1714();
        int[] arr = new int[]{1,2,3};
        theMinKth1714.smallestK(arr,0);
    }

    /**
     * 快排方式
     * @param arr
     * @param k
     * @return
     */
    public int[] smallestK2(int[] arr, int k) {

    }



    public int[] smallestK(int[] arr, int k) {
        if (k==0){
            return new int[]{};
        }
        //大根堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < arr.length; i++) {
            if (priorityQueue.size()<k){
                priorityQueue.add(arr[i]);
                continue;
            }

            if (arr[i] < priorityQueue.element()){
                priorityQueue.poll();
                priorityQueue.add(arr[i]);
            }
        }
        int[] a = new int[k];
        int index = 0;
        while (!priorityQueue.isEmpty()){
            a[index++] = priorityQueue.poll();

        }
        return a;
    }
}
