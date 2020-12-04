package com.universe.origin.star.special.flash;

import java.nio.charset.CoderResult;
import java.util.Arrays;
import java.util.Scanner;

/**
 * TravelPlan简介
 * 旅行计划
 * N个城市，城市之间的距离使用临接矩阵存储，用最短的成本走完所有的城市 找到最优解
 * 基本思路使用数组记录城市的到访状态 每次在未访问的节点中找未到访的节点，然后走下面的通路
 *
 * @date 2020-12-04 15:48
 */
public class TravelPlan {
    public static void main(String[] args) {
        int[][] adjacencyMartix = new int[][]{
                {-1, 3, -1, 8, 9},
                {3, -1, 3, 10, 5},
                {-1, 3, -1, 4, 3},
                {8, 10, 4, -1, 20},
                {9, 5, 3, 20, -1}};
        Scanner scanner = new Scanner(System.in);
        int originNode = scanner.nextInt();
        int[] currentValueStatus = new int[]{-1, -1, -1, -1, -1};
        // 设置源点已经被访问过
        currentValueStatus[originNode - 1] = 1;
        int[] bestValueStatus = new int[adjacencyMartix.length];
        System.out.println(calc(adjacencyMartix, originNode - 1, currentValueStatus, bestValueStatus, Integer.MAX_VALUE, 0, 1, originNode - 1));
        System.out.println(Arrays.toString(bestValueStatus));
    }


    /**
     * 进行计算
     *
     * @param adjacencyMatrix 记录城市间关系
     * @param lastCityIndex   记录上个城市的索引 也是起始城市节点
     * @param currentValue    此路径城市到访状态维护
     * @param bestValueStatus 最好结果城市最好路径记录
     * @param bestValue       最好的结果
     * @param currentValue    当前结果值
     * @param loopIndex       第几次路径选择
     * @param originNode      起始节点因为最后要回到起始节点所以需要记录下
     *
     */
    public static Integer calc(int[][] adjacencyMatrix, int lastCityIndex, int[] currentValueStatus, int[] bestValueStatus, int bestValue, int currentValue, int loopIndex, int originNode) {

        /**
         * 收集 到达链路的终点
         */
        if (loopIndex > currentValueStatus.length - 1) {
            //最后一个城市和起始点有边
            if (currentValue + adjacencyMatrix[lastCityIndex][originNode] < bestValue && adjacencyMatrix[lastCityIndex][originNode] != -1) {
                // 记录最优的解 再加上回到原点的值
                bestValue = currentValue + adjacencyMatrix[lastCityIndex][originNode];

                for (int j = 0; j < currentValueStatus.length; j++) {
                    bestValueStatus[j] = currentValueStatus[j];
                }
            }
            return bestValue;
        } else {

            //搜索 这里如果用交换的算法可以较少遍历 也就是将状态维护为到访区间和未到访区间 KISS原则怎么容易看怎么来
            // 这里由于起源节点已经被设置为访问过，所以不会再访问
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                // 起始节点最后到达叶子处理
                if (j == originNode) {
                    continue;
                }

                // 上一节点和当前节点是通路并且 到达当前节点后的值还是小于最优值才可以继续  当前节点没有被访问过
                if ((adjacencyMatrix[lastCityIndex][j] != -1 && adjacencyMatrix[lastCityIndex][j] + currentValue < bestValue && currentValueStatus[j] == -1)) {

                    // 标记为已经到访 -.- loop代表访问的第几个节点
                    loopIndex += 1;
                    currentValueStatus[j] = loopIndex;
                    // 值累加 前一个节点到当前节点的距离
                    currentValue += adjacencyMatrix[lastCityIndex][j];
                    // 递归向下走 j节点变成前一个几点
                    bestValue = calc(adjacencyMatrix, j, currentValueStatus, bestValueStatus, bestValue, currentValue, loopIndex, originNode);

                    //回溯当前节点累加的值
                    currentValueStatus[j] = -1;
                    loopIndex -= 1;
                    currentValue -= adjacencyMatrix[lastCityIndex][j];
                }
            }
            return bestValue;
        }
    }


}
