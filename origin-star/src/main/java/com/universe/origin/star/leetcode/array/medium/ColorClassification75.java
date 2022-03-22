package com.universe.origin.star.leetcode.array.medium;

/**
 * 75. 颜色分类
 * https://leetcode-cn.com/problems/sort-colors/
 */
public class ColorClassification75 {

    /**
     * 使用双指针
     * p1 用来交换0元素
     * p2 用来交换1元素
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int p1 = 0;
        int p2 = 0;
        for (int i = 0; i < nums.length; i++) {
            // 与p1 交换位置
            if (nums[i] == 1) {
                swap(nums, i, p2);
                p2++;
            } else if (nums[i] == 0) {
                swap(nums, i, p1);
                //这时候p1 位置上的可能是1  也就是可能把1交换出去了  需要再次将当前位置和p2交换 再把1交换回来
                if (p1 < p2) {
                    swap(nums,i,p2);
                }
                // 交换一个0需要两个指标都向前+1 因为相当于把1队列向整体移动了一个格子
                p1++;
                p2++;
            }
        }
    }

    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
