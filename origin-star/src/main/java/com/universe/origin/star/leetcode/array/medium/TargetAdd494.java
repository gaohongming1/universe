package com.universe.origin.star.leetcode.array.medium;

/**
 * 目标和
 * 这个题回溯法随便坐
 * 动态规划方式
 */
public class TargetAdd494 {


    public int findTargetSumWays(int[] nums, int target) {



        /**
         * 首先定义sum  是数组所有元素的累计
         * neg 是所有选择-号的元素的累计
         * 则（sum - neg） 是所有选择+ 号元素的累计
         * 则target = （sum-neg）-neg
         * 换算下得   neg = (sum-target0/2
         * 已知所有的都为正整数  所有如果neg不是整数 则直接返回false
         */

        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max += nums[i];
        }
        if ((max - target)<0 ||(max - target) % 2 != 0) {
            return 0;
        }

        /**
         * 接下来问题被转换为  在数组中找到 n个数字 他们的和是 neg
         */
        int neg = (max - target) / 2;
        // 这个状态转移矩阵代表  在这些数字中能否找到y轴的解
        int[][] dp = new int[nums.length + 1][neg + 1];
        dp[0][0] = 1;

        /**
         * 对应的状态转移方程
         * dp[i][j]  代表 1-i  这些数字中  能够找到和为j的解的个数
         * 当num[i] >j  的时候，num[i] 不能够被选取    dp[i][j] = dp[i-1][j]
         * 当num[i] <=j 的时候  num[i] 可以被选取 也可以不被选取   dp[i][j] = dp[i-1][j-nums[j]] + dp[i-1][j]
         */

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][i];
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][neg];

    }
}
