package com.universe.origin.star.leetcode.graph.base;

import java.util.*;

/**
 * Dijkstra 算法，又叫迪科斯彻算法（Dijkstra），
 * 算法解决的是有向图中单个源点到其他顶点的最短路径问题。
 */
public class Dijkstra {
    public static void main(String[] args) {
        int[][] adjMatrix = {
                {0,2,3,6,0,0},
                {2,0,0,0,4,6},
                {3,0,0,2,0,0},
                {6,0,2,0,1,3},
                {0,4,0,1,0,0},
                {0,6,0,3,0,0}
        };
        Dijkstra dijkstra = new Dijkstra();
        System.out.println(dijkstra.Dijkstra(adjMatrix, 0).toString());
    }


    /**
     * @param matrix
     * @param u
     * @return
     */
    public List<Integer> Dijkstra(int[][] matrix, int u) {
        List<Integer> result = new ArrayList<>();
        result.add(u);

        boolean[] visited = new boolean[matrix.length];
        Arrays.fill(visited, false);
        visited[u] = true;
        // 初始化最短距离数组
        int[] dist = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[u][i] != 0) {
                dist[i] = matrix[u][i];
            } else {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        // 开始迭代
        for (int i = 0; i < matrix.length; i++) {
            int min = Integer.MAX_VALUE;
            int node = u;
            for (int j = 0; j < dist.length; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    node = j;
                }
            }

            result.add(node);
            visited[node] = true;

            // 根据node更新dist
            for (int j = 0; j < dist.length; j++) {
                if (!visited[j]&&matrix[node][j] != 0 && dist[j] > dist[node] + matrix[node][j]) {
                    dist[j] = dist[node] + matrix[node][j];
                }
            }
        }
        System.out.println("最短路径数组");
        System.out.println(Arrays.toString(dist));
        return result;
    }

}
