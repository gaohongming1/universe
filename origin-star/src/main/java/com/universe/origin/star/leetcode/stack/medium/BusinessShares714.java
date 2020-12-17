package com.universe.origin.star.leetcode.stack.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 买卖股票的最佳时机含手续费
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * <p>
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * <p>
 * 返回获得利润的最大值。
 * <p>
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * 示例 1:
 * <p>
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * 注意:
 * <p>
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 * <p>
 * 可以用dfs的套路，每天的买、卖、过形成解空间，在解空间之上求解最优解目前没有想到剪枝函数，导致运行时间超时 -。-
 * 优化思想是从减枝方向，如果X天买入股票，那么剩下天数的最优值无论怎么样都已经确定了，使用hash表进行记录避免重复计算
 */
public class BusinessShares714 {
    public static void main(String[] args) {
        int[] price = new int[]{1, 3, 2, 8, 4, 9};
        BusinessShares714 businessShares714 = new BusinessShares714();
        int max = businessShares714.maxProfit(price,2);
        System.out.println(max);


    }

    public int maxProfit(int[] prices, int fee) {
        int[] currentValueStatus = new int[prices.length];
        Map<String, Integer> bestValueMap = new HashMap<>();
        int x = calc(bestValueMap, 0, 0, currentValueStatus, false, fee, prices, 0, -1);
        return x;
    }


    /**
     * 贪心算法求解
     *
     * @param prices
     * @param fee
     * @return
     */
    public int greedy(int[] prices, int fee) {
        int n = prices.length;
        //代表可买的最低解
        int buy = prices[0] + fee;
        //来记录最优解的累加
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            //代表当前可获得更低的价钱进行买入则更新最低的买入价格
            if ((prices[i] + fee) < buy) {
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                profit = profit + prices[i] - buy;
                //这个类似假设中间不进行卖出 也就是如果碰到价钱更高的一天再进行卖出，进行反悔操作。 下次高点减本次获得点 再加上本次之前获得的利润就是相当于总利润
                buy = prices[i];
            }
        }
        return profit;
    }


    /**
     * 动态规划方法
     * 第X天是从第X-1天过来的，那么第X天的最优结果子集 第X-1天一定是最优结果依次类推
     * 当天的账户状态分为两种情况   当天持有股票和不持有股票
     *
     * @return
     */
    public int dpCalc(int[] price, int fee) {
        //构造dp数组 0 代表
        int[][] dp = new int[price.length][2];
        //初始化第一天交易后的结果
        //代表当天结束后不持有账户结果
        dp[0][0] = 0;
        //代表当天结束后持有的结果
        dp[0][1] = -price[0];

        //循环dp表
        for (int i = 1; i < dp.length; i++) {
            //表示第i天不持有情况   可以从昨天不持有、昨天持有今天卖出来决定
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + price[i] - fee);
            //表示今天持有  可以从昨天不持有、昨天持有转移
            dp[i][1] = Math.max(dp[i - 1][0] - price[i], dp[i - 1][1]);
        }
        //最终不持有肯定账户收益最大
        return dp[price.length - 1][0];

    }


    /**
     * 这个解法相当于逆向推导的，倒数第一天卖和买或者不动 就决定的倒数第二天的收益，那么倒数第二天的持仓状态的最优值其实就固定了，
     * 可以使用缓存记录，如果继续碰到则从缓存之中直接获取就行了。比如算倒数第三天的时候直接从换从获取倒数第二天的值
     *
     * @param prices
     * @param index
     * @param status
     * @param fee
     * @return
     */
    private int dfs(int[] prices, int index, int status, int fee) {
        if (index == prices.length) {
            return 0;
        }
        //定义三个变量，分别记录[不动]、[买]、[卖]
        int a = 0, b = 0, c = 0;
        //当前节点不动情况，不动就继续向下递归status不变                   当前节点不动情况财产 = 当前节点的后面节点的最优值
        a = dfs(prices, index + 1, status, fee);
        //status=1代表是卖出 卖出则是资产加上当前节点的价格减去手续费       当前节点卖出情况财产 = 当前节点后面的节点的最优值 + 当前节点的价值 - 手续费
        if (status == 1) {
            //递归处理卖的情况，卖的时候会有一个手续费 status代表下个节点可买入的情况
            b = dfs(prices, index + 1, 0, fee) + prices[index] - fee;
        } else {
            //代表当前节点买的情况 后续节点的处理 status代表可卖出的情况    当前节点买入情况 = 当前节点后面节点的最优值 - 当前节点价值
            c = dfs(prices, index + 1, 1, fee) - prices[index];
        }
        //最终结果就是三个变量中的最大值 综上所述，会存在递归计算当前节点最优值的情况，可以使用map将当前节点的最优值存储的缓存减少计算
        return Math.max(Math.max(a, b), c);
    }

    /**
     * 最优解数组记录买卖流程  0代表啥都不干  1代表买 2代表卖
     * holdStatus代表当前股票的持有情况
     * index 代表是第几天
     * 使用map记录截止当前节点的最优值  算法为 bestValue - currrentValue 就是后续节点的最优值
     */
    public int calc(Map<String, Integer> map, int bestValue, int currentValue, int[] currentValueStatus, boolean holdStatus, int fee, int[] prices, int index, int lastBuyIndex) {

        String key = index+ String.valueOf(holdStatus);
        if (map.containsKey(key)){
            return map.get(key);
        }
        if (index > prices.length - 1) {
            //替换最优解
            if (currentValue > bestValue) {
                System.out.println(Arrays.toString(currentValueStatus) + currentValue);
                bestValue = currentValue;
            }
            return bestValue;
        }
        //根据index求解此节点的解空间
        int[] feasibleSolution = feasibleSolution(holdStatus);
        //循环可行解求解最优值
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 0; i < feasibleSolution.length; i++) {
            //什么都不操作直接向下递归
            if (feasibleSolution[i] == 0) {
                currentValueStatus[index] = 0;
                bestValue = calc(map, bestValue, currentValue, currentValueStatus, holdStatus, fee, prices, index + 1, lastBuyIndex);
                //剩余节点产生最大价值 = 最优值 - 当前节点前的金额
                a = bestValue - currentValue;
                continue;
            }

            //买股票买完改变持有状态就行，影响下个节点可行解的范围
            if (feasibleSolution[i] == 1) {
                currentValueStatus[index] = 1;
                //当前账户扣减买入金额
                b = currentValue;
                currentValue = currentValue - prices[index];
                bestValue = calc(map, bestValue, currentValue, currentValueStatus, true, fee, prices, index + 1, index);
                //剩余节点产生的最大价值 = 最优值 - 当前节点买入金额 - 当前节点前的卖出金额
                b = bestValue - prices[index] - b;
                continue;
            }

            //卖股票  求解卖完之后当前的利益需要找到上一次买入的时间也就是从currentValueStatus逆向找到上一次的买入点
            if (feasibleSolution[i] == 2) {
                currentValueStatus[index] = 2;
                //账户金额加上卖出金额减去费用
                c = currentValue;
                currentValue = currentValue + prices[index] - fee;
                bestValue = calc(map, bestValue, currentValue, currentValueStatus, false, fee, prices, index + 1, -1);
                //剩余节点产生的最大价值 = 最优值 - 当前节点卖出金额 - 当前节点卖出前的金额
                c = bestValue - c + prices[index];
                continue;
            }
        }

        // 代表当前节点在 status情况下 剩余节点的最大值
        int lastBestValue = Math.max(a,Math.max(b,c));
        map.put(key,lastBestValue);
        return bestValue;
    }


    //根据股票的持有情况构造可行解
    private int[] feasibleSolution(boolean holdStatus) {
        //持有情况下股票可卖否则可买
        if (holdStatus) {
            return new int[]{0, 2};
        }
        return new int[]{0, 1};
    }

}
