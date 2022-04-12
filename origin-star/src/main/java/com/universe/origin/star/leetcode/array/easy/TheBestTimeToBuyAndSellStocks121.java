package com.universe.origin.star.leetcode.array.easy;

/**
 * 121. 买卖股票的最佳时机
 */
public class TheBestTimeToBuyAndSellStocks121 {

    /**
     * 卖出股票前必须持有股票 其他无限制
     * 使用i 代表第i天
     * 0  1 代表第i天后持有股票的情况
     * 状态转移方程
     * dp[i][0] 代表第i天没有持有股票的收益情况
     * 对应的情况是要么是前一天就没有股票   要么是前一天有股票但是今天卖了
     * dp[i][1] 代表第i天持有股票的收益情况
     * 对应的情况是前一天没有股票今天买了    或者前一天就有股票今天不动
     * dp[i][0] = max(dp[i-1][0],dp[i-1][1]+price[i] )
     * dp[i][1] = max(dp[i-1][1],dp[i-1][0]-price[i])
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[prices.length - 1][0];
    }
}
