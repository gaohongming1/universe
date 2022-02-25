package com.universe.origin.star.special.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * prim 算法
 */
public class Prim {
    public static void main(String[] args) {
        int[][] G = {
                {-1, 23, -1, -1, -1, 28, 36},
                {23, -1, 20, -1, -1, -1, 1},
                {-1, 20, -1, 15, -1, -1, 4},
                {-1, -1, 15, -1, 3, -1, 9},
                {-1, -1, -1, 3, -1, 17, 16},
                {28, -1, -1, -1, 17, -1, 25},
                {36, 1, 4, 9, 16, 25, -1},
        };
        prim2(G, 0);
        // 0162345

    }


    public static void prim(int[][] matrix, int point) {
        List<Integer> visited = new ArrayList<>();
        List<Integer> unVisited = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            if (i == point) {
                visited.add(point);
            } else {
                unVisited.add(i);
            }
        }
        //循环已经访问的节点 找到已访问的节点到未访问节点的最短路径
        while (visited.size() < matrix.length) {
            int minDist = Integer.MAX_VALUE;
            int minUnVisitedPoint = -1;

            //双重循环找到两个集合之间的最短路径
            for (int i = 0; i < visited.size(); i++) {
                for (int j = 0; j < unVisited.size(); j++) {
                    if (matrix[visited.get(i)][unVisited.get(j)] < minDist && matrix[visited.get(i)][unVisited.get(j)] != -1) {
                        minDist = matrix[visited.get(i)][unVisited.get(j)];

                        minUnVisitedPoint = j;
                    }
                }
            }
            visited.add(unVisited.get(minUnVisitedPoint));
            unVisited.remove(minUnVisitedPoint);
        }
        System.out.println(visited);
    }

    /**
     * 临接矩阵中如果不想临，则为-1
     *
     * @param am
     * @param point 点
     */
    public static void prim2(int[][] am, int point) {
        //循环临接矩阵，初始化最小值数组和临近节点数组
        int[] close = new int[am.length];
        int[] dist = new int[am.length];

        //初始化已访问数组和未访问数组
        int[] visited = new int[am.length];
        visited[point] = point;
        int[] unVisited = new int[am.length ];

        for (int i = 0; i < am.length; i++) {
            //初始化最近的点都是point
            close[i] = point;
            dist[i] = am[point][i];
            //初始化未访问数组，访问过的作为-1
            if (i != point) {
                unVisited[i] = point;
                visited[point] = -1;
            } else {
                unVisited[point] = -1;
                visited[point] = 1;
            }
        }

        //循环未访问节点每次找到当前的最小的边
        int visitedNum = 1;
        int minDist = Integer.MAX_VALUE;

        while (visitedNum < point + 1) {
            //找到当前最小的
            int visitedPointTemp = point;
            for (int i = 0; i < am.length; i++) {
                if (dist[i] != -1 && dist[i] < minDist) {
                    minDist = dist[i];
                    visitedPointTemp = i;
                }
            }

            //将最小的加入已访问集合
            visited[visitedPointTemp] = 1;
            unVisited[visitedPointTemp] = -1;

            //更新最小距离数组
            for (int i = 0; i < am.length; i++) {
                //更新未访问过的节点
                if (unVisited[i] != -1) {
                    for (int j = 0; j < am.length; j++) {
                        //判断已访问节点和当前需要更新节点的距离
                        if (visited[j] == 1) {
                            if (dist[i] == -1 && am[i][j] < dist[i]) {
                                dist[i] = am[i][j];
                                close[i] = j;
                            }
                        }
                    }
                }
            }

        }

    }



}
