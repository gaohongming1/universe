package com.universe.origin.star.leetcode.array.medium;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */
public class TheBestTimeToBuyAndSellStocks309 {


    /**
     *相比于其他的股票题 这个相当于添加了一个状态
     * 持有 1
     * 不持有 0 进入冷冻期 也就是今天卖股票
     * 不持有不进入冷冻期 2
     *
     * dp[i][0] = dp[i-1][1]+prices[i]   前一天卖出
     * dp[i][1] = dp[i-1][1],dp[i-1][2]-prices[i]    前一天持有或者前一天不处于冷冻期 今天买入
     * dp[i][2] = dp[i-1][2],dp[i-1][0]   前一天啥也不是或者是冷冻期结束
     *
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;

        for (int i = 1; i <n ; i++) {
            dp[i][0] = dp[i - 1][1] + prices[i];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0]);
        }

        return Math.max(dp[n-1][0],dp[n-1][2]);
    }
}
