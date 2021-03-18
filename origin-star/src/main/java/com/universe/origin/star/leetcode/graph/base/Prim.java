package com.universe.origin.star.leetcode.graph.base;

import java.util.Arrays;

/**
 * 在一给定的无向图G = (V, E) 中，(u, v) 代表连接顶点 u 与顶点 v 的边（即），而 w(u, v) 代表此边的权重，若存在 T 为 E 的子集（即）且为无循环图，使得
 * 的 w(T) 最小，则此 T 为 G 的最小生成树。
 * <p>
 * 最小生成树其实是最小权重生成树的简称
 * <p>
 * 生成树和最小生成树有许多重要的应用。
 * <p>
 * 例如：要在n个城市之间铺设光缆，主要目标是要使这 n 个城市的任意两个之间都可以通信，
 * 但铺设光缆的费用很高，且各个城市之间铺设光缆的费用不同，因此另一个目标是要使铺设光缆的总费用最低。这就需要找到带权的最小生成树。
 * <p>
 * 该算法为求出图的最小生成树，首先我们需要两个数组来完成这个任务。
 * 一个数组用来保存当前节点的状态，另一个数组则用来保存到达该节点的最短距离。
 * 假设我们要求的这个图保存在一个二位数组里，保存节点状态的数组命名为vset，保存最短距离的数组命名为lowcast。
 */
public class Prim {
    public static void main(String[] args) {
        int[][] g = {
                {0,       23,     -1,     -1,    -1,     28,     36},
                {23,       0,      20,      -1,    -1,     -1,     1},
                {-1,       20,      0,      15,    -1,     -1,     4},
                {-1,       -1,      15,      0,    3,     -1,     9},
                {-1,      -1,     -1,     3,     0,     17,     16},
                {28,      -1,     -1,      -1,    17,     0,     25},
                {36,      1,     4,      9,    16,     25,     0}
        };

        Prim prim = new Prim();
        prim.prims(g,0);

    }

    /**
     * @param matrix
     * 每次找到当前的点可到达的最小的距离  然后到当前点后再更新可到达点的最短距离
     *
     * 不能到的点位-1
     */
    public void prims(int[][] matrix, int u) {
        int n = matrix.length;

        int[] closest = new int[n];
        int[] lowcast = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        visited[u] = true;

        for (int i = 0; i < n; i++) {
            if (matrix[u][i] ==-1){
                lowcast[i] = Integer.MAX_VALUE;
            }else {
                lowcast[i] = matrix[u][i];
            }
            closest[i] = u;
        }

        for (int i = 0; i < n; i++) {
            // 找到未访问的最小的
            int min = Integer.MAX_VALUE;
            int t = u;
            for (int j = 0; j < n; j++) {
                // 未访问 可访问
                if (!visited[j] && lowcast[j] < min) {
                    min = lowcast[j];
                    t = j;
                }
            }
            System.out.println(t);
            visited[t] = true;

            //根据j更新lowcast
            for (int j = 0; j < n; j++) {
                // 更新未访问路径  可访问的
                if (!visited[j] && matrix[t][j]!=-1 && matrix[t][j] < lowcast[j]){
                    lowcast[j] = matrix[t][j];
                    closest[j] = t;
                }
            }
        }
        System.out.println("最小花费"+ Arrays.toString(lowcast));
    }
}
