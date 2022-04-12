package com.universe.origin.star.leetcode.array.medium;

import java.util.*;

/**
 * 322 零钱兑换
 */
public class SmallMoneyCount322 {

    int res = Integer.MAX_VALUE;


    public static void main(String[] args) {
        int[] arr = new int[]{186, 419, 83, 408};
        SmallMoneyCount322 smallMoneyCount322 = new SmallMoneyCount322();
        smallMoneyCount322.coinChange(arr, 6249);
    }


    /**
     * 动态规划方式
     * 假设f(i) 代表组成金额i所需的最少硬币量  c() 代表某个金币的面值
     * 假设在求出f(i) 之前 我们已经求出了f(0) f(i-1) 的答案
     * 那么f(i) = min[ f(i-cj) (0<j<num.length)   ] +1
     * 换句人话意思就是  组成金额 x 所需硬币的最优解 f(x)    就是 f(x-(枚举当前所有硬币)) 的最小值加1
     */

    public int dp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }


    /**
     * dfs +dp  原理和上面的dfs一样
     */
    public int dfsdp(int[] coins, int[] dp, int amount) {
        if (amount < 0) {
            return -1;
        }

        if (amount == 0) {
            return 0;
        }

        // 作为缓存
        if (dp[amount - 1] != 0) {
            return dp[amount - 1];
        }

        //对应上面的状态转移公式 尝试每个元素的最小值
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int temp = dfsdp(coins, dp, amount - coins[i]);
            if (temp >= 0 && temp < min) {
                min = res + 1;
            }
            dp[amount] = Math.min(temp + 1, dp[amount]);
        }
        dp[amount - 1] = min == Integer.MAX_VALUE ? -1 : min;
        return dp[amount - 1];
    }

    public int coinChange2(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    /**
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        // 找到小于amount的数字
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] < amount) {
                nums.add(coins[i]);
                continue;
            }
            if (coins[i] == amount) {
                return 1;
            }
        }
        if (nums.isEmpty()) {
            return -1;
        }

        nums.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        // 因为结果是找到一种可能的解就行 所有bfs相比于dfs更有优势 可以快速找到一种解然后返回

        Deque<List<Integer>> listDeque = new LinkedList<>();
        Deque<Integer> count = new LinkedList<>();
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> list = new ArrayList<>();
            list.add(nums.get(i));
            listDeque.offer(list);
            count.offer(nums.get(i));
        }
        int min = Integer.MAX_VALUE;
        Set<Integer> cache = new HashSet<>();

        while (!listDeque.isEmpty()) {
            Integer now = count.pop();
            List<Integer> nowList = listDeque.pop();
            // 找到nowList的可行解  构建新的可行解加入到队列中
            if (nowList.size() + 1 >= min) {
                continue;
            }
            List<Integer> next = find(now, amount, nums);
            if (!next.isEmpty()) {

                //否则代表累计来到了多种可选的路径
                Deque<List<Integer>> nextDeque = new LinkedList<>();
                Deque<Integer> nextCount = new LinkedList<>();

                // 递增的 得到的
                for (int i = 0; i < next.size(); i++) {
                    List<Integer> newNext = new ArrayList<>(nowList);
                    int temp = now;

                    temp = temp + next.get(i);
                    newNext.add(next.get(i));

                    if (temp == amount) {
                        min = Math.min(min, newNext.size());
                        break;
                    }

                    nextCount.offerLast(temp);
                    nextDeque.offerLast(newNext);

                    //进行剪枝
//                    if (cache.contains(temp)) {
//                        continue;
//                    } else {
//                        //与目标值如果差距达到最大的值  则只加入最大值
//                        if (amount - temp >= nums.get(0) * 2) {
//                            break;
//                        }
//                        cache.add(temp);
//                        listDeque.offerFirst(newNext);
//                        count.offerFirst(temp);
//                    }
                }
                while (!nextDeque.isEmpty()) {
                    listDeque.offerFirst(nextDeque.pollLast());
                    count.offerFirst(nextCount.pollLast());
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    /**
     * 找到当前可以走的路径
     */
    private List<Integer> find(Integer now, int amount, List<Integer> nums) {

        List<Integer> next = new ArrayList<>();
        if ((amount - now) >= nums.get(0)) {
            next.addAll(nums);
            return next;
        }
        // next 是递减的
        for (int i = 0; i < nums.size(); i++) {
            if ((amount - now) >= nums.get(i)) {
                next.add(nums.get(i));
            }
        }
        return next;
    }


    public void dfs(int count, List<Integer> nextList, int index, int amount, List<Integer> nums, List<Integer> currentRes) {
        if (index + 1 > res) {
            return;
        }

        for (int i = 0; i < nextList.size(); i++) {
            currentRes.add(nextList.get(i));
            if (count + nextList.get(i) == amount) {
                res = Math.min(index + 1, res);
                return;
            }
            List<Integer> next = find(count + nextList.get(i), amount, nums);
            dfs(count + nextList.get(i), next, index + 1, amount, nums, currentRes);
            currentRes.remove(currentRes.size() - 1);
        }
    }
}
