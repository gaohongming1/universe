package com.universe.origin.star.leetcode.heap.medium;

import java.util.PriorityQueue;

/**
 * @author gaohongming
 * @version 1.0.0
 * @ClassName TheKthInArray215.java
 * @Description TODO
 * @createTime 2021年01月05日 22:45:00
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4

 */
public class TheKthInArray215 {
    public static void main(String[] args) {

    }


    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (priorityQueue.size() < k){
                priorityQueue.add(nums[i]);
                continue;
            }

            if (priorityQueue.element() >= nums[i]){
                continue;
            }else {
                priorityQueue.poll();
                priorityQueue.add(nums[i]);
            }
        }

        return priorityQueue.element();


    }
}
