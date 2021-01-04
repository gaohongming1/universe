package com.universe.origin.star.leetcode.queue.easy;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 */
public class MaxSuborderSum53 {
    public static void main(String[] args) {
        int[] array = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        MaxSuborderSum53 maxSuborderSum53 = new MaxSuborderSum53();
        maxSuborderSum53.maxSubArray(array);

    }

    /**
     * 前缀和加上单调队列
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        if (nums.length ==1){
            return nums[0];
        }
        int n = nums.length;
        int[] numsPre = new int[n];
        numsPre[0] = nums[0];

        int minTemp2 = nums[0];
        for (int i = 1; i < n; i++) {
            numsPre[i] = nums[i] + numsPre[i-1];
            if (minTemp2 < nums[i]){
                minTemp2 = nums[i];
            }
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int maxIndex = 0;
        int minIndex = 0;
        // 找到最小值和最大值
        int minTemp = numsPre[0];
        for (int i = 0; i < n; i++) {

            if (minTemp < numsPre[i]){
                minTemp = numsPre[i];
            }

            if (numsPre[i] < min){
                min = numsPre[i];
                minIndex = i;
            }

            if (numsPre[i] > max){
                max = numsPre[i];
                maxIndex = i;
            }
        }

        int temp2 = maxIndex>minIndex?  max - min:min-max;

        return  Math.max(minTemp2,Math.max(temp2,minTemp));
    }

}
