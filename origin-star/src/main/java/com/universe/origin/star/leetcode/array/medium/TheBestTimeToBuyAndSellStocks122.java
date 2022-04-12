package com.universe.origin.star.leetcode.array.medium;

/**
 * 买卖股票的最佳时机当天可以卖
 */
public class TheBestTimeToBuyAndSellStocks122 {

    /**
     * 不同于121 题这个可以在当天买入和当天卖出 并且当天买入可以当天卖出 也就是一天两次操作机会
     * 我们将dp长度扩大为原来的两倍 即2n次操作
     * 这里添加k作为交易次数  以买入信号作为k值添加操作
     * dp[i][0]  代表在第i次不操作持有股票收益
     * 对应情况：
     * 前一次操作就没有  dp[i-1][0]   前一次操作有 dp[i-1][1]+price[i]
     * <p>
     * dp[i][1]  代表在第i次操作持有股票收益
     * 对应情况：
     * 前一次操作就有这次不动  dp[i-1][1]   前一次操作没有这次买 dp[i-1][0]-price[i]
     */
    public int maxProfit(int[] prices) {
        int n = prices.length * 2;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i/2]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i/2]);
        }
        return dp[n - 1][0];
    }
}
