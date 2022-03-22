package com.universe.origin.star.leetcode.array.medium;

/**
 *
 */
public class MinimumPathSum64 {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        MinimumPathSum64 minimumPathSum64 = new MinimumPathSum64();
        minimumPathSum64.minPathSum(arr);
    }

    public int minPathSum(int[][] grid) {

        int[][] dp = new int[grid.length][grid[0].length];
        // 初始化dp表
        int xCount = 0;
        int yCount = 0;
        for (int i = 0; i < dp.length; i++) {
            xCount += grid[i][0];
            dp[i][0] = xCount;
        }

        for (int i = 0; i < dp[0].length; i++) {
            yCount += grid[0][i];
            dp[0][i] = yCount;
        }

        for (int i = 1; i <dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // 从上边和左边的值中选择最小的
                int up = dp[i - 1][j];
                int left = dp[i][j - 1];
                int min = Math.min(up, left);
                dp[i][j] = min + grid[i ][j];
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }
}
