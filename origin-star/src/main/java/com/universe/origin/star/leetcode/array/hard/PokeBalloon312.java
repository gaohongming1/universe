package com.universe.origin.star.leetcode.array.hard;

import java.util.Arrays;

/**
 * 312. 戳气球
 */
public class PokeBalloon312 {

    public static void main(String[] args) {
        PokeBalloon312 pokeBalloon312 = new PokeBalloon312();
        int[] arr = new int[]{3, 1, 5, 8};
        pokeBalloon312.maxCoins(arr);
    }

    /**
     * 采用记忆化搜索的方式
     * 将数组左右两边补充1
     * 循环区间[i.j]尝试每个值k 得到当前的最大值    fn(i,j) = fn(i,k) + fn(k,j) + w(k)*w(j)*w(k)
     * 采用分支的思想求左右两个区间的值
     * 采用一个二维数组 记录 (i,j)区间的值避免重复计算
     * 本质思想还是动态规划，问题从小规模上升到大规模
     */

    private int[][] dp;
    private int[] val;

    public int maxCoins2(int[] nums) {
        int n = nums.length;
        val = new int[n + 2];
        val[0] = val[val.length - 1] = 1;
        dp = new int[n + 2][n + 2];
        for (int i = 1; i < n + 1; i++) {
            val[i] = nums[i - 1];
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return solve(0, n + 1);
    }

    public int solve(int left, int right) {
        if (left >= right - 1) {
            return 0;
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        for (int i = left + 1; i < right; i++) {
            int sum = val[left] * val[i] * val[right];
            sum += solve(left, i) + solve(i, right);
            dp[left][right] = Math.max(sum, dp[left][right]);
        }
        return dp[left][right];
    }

    /**
     * 采用动态规划的方式
     * 状态转移方程
     * dp[i][j] = max(dp[i][k] + dp[k][j] + fn(k)  (i<k<j))
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        //设置为区间
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }

        // 这里采用倒序主要是因为 j的值是从i向右移动的
        for (int i = n - 1; i >= 0; i--) {
            // j大于i两个值 主要是因为区间的问题
            for (int j = i + 2; j <= n + 1; j++) {
                // k不能等于边界值主要是 如果等于边界值 则不存在区间 也就是中间没有元素
                for (int k = i + 1; k < j; k++) {
                    int sum = val[i] * val[j] * val[k];
                    sum += dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }
        return dp[0][n + 1];
    }
}
