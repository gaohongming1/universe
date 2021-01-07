package com.universe.origin.star.leetcode.heap.medium;

/**
 * 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
 * <p>
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出: 200
 * 解释:
 * 城市航班图如下
 * <p>
 * <p>
 * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
 * 示例 2：
 * <p>
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * 输出: 500
 * 解释:
 * 城市航班图如下
 * <p>
 * <p>
 * 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
 *  
 * <p>
 * 提示：
 * <p>
 * n 范围是 [1, 100]，城市标签从 0 到 n - 1
 * 航班数量范围是 [0, n * (n - 1) / 2]
 * 每个航班的格式 (src, dst, price)
 * 每个航班的价格范围是 [1, 10000]
 * k 范围是 [0, n - 1]
 * 航班没有重复，且不存在自环
 */
public class TheCheapestInKSite787 {
    public static void main(String[] args) {
        TheCheapestInKSite787 theCheapestInKSite787 = new TheCheapestInKSite787();
        int[][] flights = new int[][]{
                {0,1,100},
                {1,2,100},
                {0,2,500}
        };
        theCheapestInKSite787.findCheapestPrice(3,flights,0,2,0);
    }


    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] matrix = new int[n][n];
        // 邻接矩阵存储有向图
        for (int i = 0; i < flights.length; i++) {
            matrix[flights[i][0]][flights[i][1]] = flights[i][2];
        }
        // 初始化起点已经到访
        int[] visitPoint = new int[n];
        visitPoint[src] = 1;
        int bestValue = dfs(matrix, 0, K, visitPoint, 0, src, Integer.MAX_VALUE, dst);
        return bestValue == Integer.MAX_VALUE?-1:bestValue;
    }

    public int dfs(int[][] matrix, int index, int k, int[] visitPoints, int currentValue, int currentNode, int bestValue, int dst) {

        // 如果全部访问或者到达当前点则更新最优解   或者中转点已经超过最大 则返回最优解
        if (currentNode == dst || index > k) {
            //如果到达目标点查看是否更新最优解
            if (currentNode == dst && currentValue < bestValue) {
                bestValue = currentValue;
                return bestValue;
            }
            return bestValue;
        }

        for (int i = 0; i < visitPoints.length; i++) {
            //代表这个城市已经被访问过 以及当前城市无法到达城市i
            if (visitPoints[i] == 1 || matrix[currentNode][i] == 0) {
                continue;
            }

            //剪枝
            if (currentValue + matrix[currentNode][i] > bestValue){
                continue;
            }

            //可以到达城市i
            index = index + 1;
            visitPoints[i] = 1;
            currentValue = currentValue + matrix[currentNode][i];
            bestValue = dfs(matrix, index, k, visitPoints, currentValue, i, bestValue, dst);
            // 回退
            visitPoints[i] = 0;
            currentValue = currentValue - matrix[currentNode][i];
            index = index - 1;

        }
        return bestValue;
    }
}
