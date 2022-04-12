package com.universe.origin.star.leetcode.array.hard;

/**
 * 买卖股票的最佳时机限制交易次数
 */
public class TheBestTimeToBuyAndSellStocks123 {

    /**
     * 这个添加交易次数的限制 k<=2
     * 对应的有四种情况
     * 1：第i天交易1次持有股票: 前一天没有今天买交易一次、前一天有今天也有交易一次    dp[i][1][1] = dp[i-1][0][0]-prices[i] 、dp[i-1][1][1]
     * 2:第i天交易1此不持有股票 前一天必须持有股票才能交易、 前一天就没有   dp[i][1][0]= dp[i-1][1][1]+prices[i]  、dp[i-1][1][0]
     * 3：第i天交易2次持有股票  前一天就有、前天没有今天有 dp[i][2][1]= dp[i-1][2][1] 、dp[i-1][1][0] - prices[i]
     * 4:第i天交易2此不持有股票 前一天就没有、前天有今天没有  dp[i][2][0]= dp[i-1][2][0] 、dp[i-1][2][1] + prices[i]
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][3][2];
        // 交易一次初始化
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][2][0] = 0;
        dp[0][2][1] = -prices[0];


        for (int i = 1; i < n; i++) {
            dp[i][1][0] = Math.max(dp[i-1][1][1]+prices[i]  ,dp[i-1][1][0]);
            dp[i][1][1] = Math.max(dp[i-1][0][0]-prices[i] ,dp[i-1][1][1]);
            dp[i][2][0] = Math.max(dp[i-1][2][0] ,dp[i-1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1] ,dp[i - 1][1][0] - prices[i]);
        }


        return dp[n - 1][2][0];
    }
}
