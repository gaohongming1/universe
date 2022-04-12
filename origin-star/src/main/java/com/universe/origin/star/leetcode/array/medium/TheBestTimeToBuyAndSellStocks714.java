package com.universe.origin.star.leetcode.array.medium;

/**
 * 买卖股票的最佳时机 含手续费
 */
public class TheBestTimeToBuyAndSellStocks714 {

    /**
     * 和普通的股票问题一样 不过在买入的时候扣减调手续费
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }
        return dp[n - 1][0];
    }
}
