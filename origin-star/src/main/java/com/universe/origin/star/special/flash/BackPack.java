package com.universe.origin.star.special.flash;

import java.util.Arrays;

/**
 * 01 背包问题
 */
public class BackPack {

    //最大重量
    private static int MAX_WEIGHT = 10;

    public static void main(String[] args) {
        int[] weight = new int[]{2, 5, 4, 2};
        int[] value = new int[]{6, 3, 5, 4};
        //最优情况下的加车情况
        int[] bestItemStatus = new int[value.length];
        //当前路径的加车情况
        int[] itemStatus = new int[value.length];
        System.out.println(backTrack(0, 0, bestItemStatus, itemStatus, value, weight, 0, 0));
        System.out.println(Arrays.toString(bestItemStatus));

    }


    /**
     * @param i              表示当前节点层级
     * @param bestResult     最优结果
     * @param bestItemStatus 最优结果状态
     * @param itemStatus     各个节点是否加车
     * @param value          价值列表
     * @param weight         重量列表
     * @param currentValue   当前值
     * @param currentWeight  当前重量
     */
    public static int backTrack(int i, int bestResult, int[] bestItemStatus, int[] itemStatus, int[] value, int[] weight, int currentValue, int currentWeight) {

        /**
         * 到达这里代表可能产生最优解，因为减枝叶函数会判断没有可能达到最优解的，路径不会继续走下去
         * 当吧最后一个节点加车之后，递归过来的i是等于长度的，所以是i> 数组下标
         */
        if (i > value.length - 1) {
            //返回最优解
            for (int j = 0; j < value.length; j++) {
                bestItemStatus[j] = itemStatus[j];
            }
            bestResult = currentValue;
            return bestResult;
        }
        /**
         * 这里是限界条件如果符合加车则加车，否则走下面的回溯然后走不加车，相当于两个选择
         */
        if (currentWeight + weight[i] <= MAX_WEIGHT) {
            //当前物品走加车状态
            itemStatus[i] = 1;
            currentValue += value[i];
            currentWeight += weight[i];

            //进入下一节点的判断，计算当前值加上下一节点之后的所有的可能值的最优解 如果能走到叶子节点，会将最优结果记录下来
            bestResult = backTrack(i + 1, bestResult, bestItemStatus, itemStatus, value, weight, currentValue, currentWeight);

            // 当前节点回溯，代表当前节点不再加入车，进行下面的判断，剩余物品除去当前节点能否得到最优解
            currentValue -= value[i];
            currentWeight -= weight[i];
        }

        /*
         * 判断如果当前节点不加入车，把剩下节点加车，总价值是否大于最优价值，如果大于则可能会获取到最优解，则继续扩展右子树
         * 这里是一个终结点，判断如果不把当前物品加入车，剩余的物品满足组成最优解的条件，如果实际组成的解没有当前的解好，会被这里剪枝
         * 可以优化剪枝函数，提升算法效率
         */
        if (bound(i + 1, currentValue, value) > bestResult) {
            itemStatus[i] = 0;
            bestResult = backTrack(i + 1, bestResult, bestItemStatus, itemStatus, value, weight, currentValue, currentWeight);
        }
        return bestResult;
    }

    /**
     * 计算剩余物品价值
     *
     * @param i
     * @param currentValue
     * @param value
     * @return
     */
    public static int bound(int i, int currentValue, int[] value) {
        for (int j = i; j < value.length; j++) {
            currentValue += value[j];
        }
        return currentValue;
    }

}
