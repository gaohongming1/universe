package com.universe.origin.star.leetcode.array.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * <p>
 * 问总共有多少条不同的路径？
 * 示例 1：
 * <p>
 * <p>
 * 输入：m = 3, n = 7
 * 输出：28
 * 示例 2：
 * <p>
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 3：
 * <p>
 * 输入：m = 7, n = 3
 * 输出：28
 * 示例 4：
 * <p>
 * 输入：m = 3, n = 3
 * 输出：6
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 */
public class DifferentRoute62 {
    public static void main(String[] args) {
        DifferentRoute62 differentRoute62 = new DifferentRoute62();
        System.out.println(differentRoute62.uniquePaths(23,12));
    }


    /**
     * 到达节点x的路径都是其左边的节点路径加上上边节点的路径之和
     * 动态规划的思想
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        //初始化第一行
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        //初始化第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <m ; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];

    }


    /**
     * 采用DFS
     * BFS要记录每个路径节点是否访问
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (m==1 && n==1){
            return 1;
        }
        int[][] visited = new int[m][n];
        visited[0][0] = 1;
        return calc(getCanVisitChar(m,n,0,0,visited),m,n,visited,0);
    }

        /**
         * dfs 搜索会超时
         * 这里可以使用记忆化方式，记录每个节点到达终点的路径数，下次碰到的时候不用重新计算，直接取就行了
         * @param canVisit
         * @param m
         * @param n
         * @param visited
         * @param countValue
         * @return
         */
    public int calc(List<Integer[]> canVisit, int m, int n, int[][] visited, int countValue) {
        //遍历可到访的点
        for (int i = 0; i < canVisit.size(); i++) {
            Integer[] current = canVisit.get(i);
            if (current[0]==m-1 && current[1] ==n-1){
                countValue+=1;
                continue;
            }
            visited[current[0]][current[1]] = 1;
            countValue = calc(getCanVisitChar(m, n, current[0], current[1], visited), m, n, visited, countValue);
            visited[current[0]][current[1]] = 0;
        }
        return countValue;
    }


    // 拿到可访问的区间 每次只能向下或者向右移动
    private List<Integer[]> getCanVisitChar(int m, int n, int y, int x, int[][] visited) {
        List<Integer[]> list = new ArrayList<>();
        //下
        if (y + 1 < m && visited[y + 1][x] == 0) {
            list.add(new Integer[]{y + 1, x});
        }
        //右
        if (x + 1 < n && visited[y][x + 1] == 0) {
            list.add(new Integer[]{y, x + 1});
        }
        return list;
    }
}
