package com.universe.origin.star.array.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 63. 不同路径 II
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 示例 1：
 * <p>
 * <p>
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * 示例 2：
 * <p>
 * <p>
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] 为 0 或 1
 */
public class DifferentRouteTwo63 {
    public static void main(String[] args) {
        DifferentRouteTwo63 differentRouteTwo63 = new DifferentRouteTwo63();
        int[][] matrix = new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        System.out.println(differentRouteTwo63.uniquePathsWithObstacles(matrix));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return calc(obstacleGrid.length , obstacleGrid[0].length, new HashMap<String, Integer>(), 0, 0, obstacleGrid);
    }

    /**
     * dfs + 记忆搜索
     * ca[0] = [1,0]
     * ca[1] = [0,1]
     */
    public int calc(int m, int n, Map<String, Integer> history, int x, int y, int[][] obstacleGrid) {
        //如果当前点在记录中有值则直接返回
        if (history.containsKey(y + "_" + x)) {
            return history.get(y + "_" + x);
        }

        // 向下访问
        int yd = y + 1;
        int value1 = 0;
        if (yd < m && obstacleGrid[yd][x] != 1) {
            if (yd == m-1 && x == n-1) {
                value1 = 1;
            } else {
                value1 = calc(m, n, history, x, yd, obstacleGrid);
                // 记录下来当前点到终点的线路
                history.put(yd + "_" + x, value1);
            }
        }

        //向右访问
        int xr = x + 1;
        int value2 = 0;
        if (xr < n && obstacleGrid[y][xr] != 1) {
            if (y == m-1 && xr == n-1) {
                value2 = 1;
            } else {
                value2 = calc(m, n, history, xr, y, obstacleGrid);
                // 记录下来当前点到终点的线路
                history.put(y + "_" + xr, value1);
            }
        }
        return value1 + value2;

    }
}
