package com.universe.origin.star.leetcode.array.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 416. 分割等和子集
 */
public class SplitEqualSmSubSet416 {
    public boolean canPartition(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        // 先把元素累加 再除以2
        int sum = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (max > target) {
            return false;
        }

        // 剩下的问题就变成了 从nums 数组中找到和为target的解


        /**
         * 采用动态规划的方式 不停的提高目标值
         * 每一行一个元素   i 代表第i个数字   j代表目标值，随着目标值的增加   (i,j) 代表 目标值是j的情况下  能否选择i 来达到解 这时候如果 nums[i] <j   则代表可以选取   如果nums[i] <j 代表不能选取  因为一旦选择就会大于j
         * 对应的状态转移共识
         * 当 nums[i] >j  不能选取   问题被转化为i的前一个元素  在j位置能够达到解
         *    dp[i][j] = dp[i-1][j]
         * 当nums[i] <=j   可以选取也可以不选取
         *    dp[i][j] = dp[i-1][j-nums[i]]  选取的情况
         *    dp[i][j] = dp[i-1][j]          不选取情况
         *
         * 初始dp[i][0] 都是true
         * dp[0][nums[0]] true
         */
        boolean[][] dp = new boolean[nums.length][target + 1];
        dp[0][nums[0]] = true;  // 这里也就是第0行除了 (0,nums[0]) 位置为true  其他都是false
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (nums[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                }
            }
        }
        return dp[nums.length - 1][target];


    }

}
