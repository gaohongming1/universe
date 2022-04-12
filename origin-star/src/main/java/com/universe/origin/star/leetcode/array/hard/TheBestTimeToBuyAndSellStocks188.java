package com.universe.origin.star.leetcode.array.hard;

/**
 * 买卖股票的最佳时机限制给定交易次数
 */
public class TheBestTimeToBuyAndSellStocks188 {

    /**
     * 给定交易次数k
     * 当k大于等于 length/2 的时候也就相当于是k无穷大
     * 对应的有四种情况
     * dp[i][k][0] = dp[i-1][k][0]  ,dp[i-1][k][1]+prices[i]
     * dp[i][k][1] = dp[i-1][k][1]  ,dp[i-1][k-1][0]-prices[i]
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int length = prices.length;
        if (k >= length / 2) {
            return maxProfit(prices);
        }
        int[][][] dp = new int[length][k + 1][2];


        // 初始化
        for (int i = 1; i <= k; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }

        for (int i = 1; i < length; i++) {
            for (int j = k; j > 0; j--) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }

        return dp[length - 1][k][0];
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
}
