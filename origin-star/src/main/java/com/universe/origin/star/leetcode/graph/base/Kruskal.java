package com.universe.origin.star.leetcode.graph.base;


import java.util.*;

/**
 * 克鲁斯卡尔算法
 */
public class Kruskal {
    public static void main(String[] args) {
        Kruskal kruskal = new Kruskal();
        int[][] g = {
                {0, 23, -1, -1, -1, 28, 36},
                {23, 0, 20, -1, -1, -1, 1},
                {-1, 20, 0, 15, -1, -1, 4},
                {-1, -1, 15, 0, 3, -1, 9},
                {-1, -1, -1, 3, 0, 17, 16},
                {28, -1, -1, -1, 17, 0, 25},
                {36, 1, 4, 9, 16, 25, 0}
        };
        kruskal.kruskal(g);
    }


    /**
     * 先对边进行排序
     * 循环边集
     *
     * @param matrix
     */
    public void kruskal(int[][] matrix) {
        // 记录点个边的关系
        List<Tubes> graph = new ArrayList<>();
        //记录点的标记
        int[] sign = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            sign[i] = i;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != -1 && matrix[i][j]!=0) {
                    Tubes tubes = new Tubes(i, j, matrix[i][j]);
                    graph.add(tubes);
                }
            }
        }
        //排序
        graph.sort(new Comparator<Tubes>() {
            @Override
            public int compare(Tubes o1, Tubes o2) {
                return o1.weight - o2.weight;
            }
        });

        // 记录结果集
        List<Tubes> result = new ArrayList<>();

        for (int i = 0; result.size() < matrix.length-1; i++) {
            Tubes tubes = graph.get(i);

            // 判断两个点的标志是否一致
            if (sign[tubes.u] != sign[tubes.v]) {
                Integer changeSign = sign[tubes.v];
                Integer targetSin = sign[tubes.u];

                result.add(tubes);
                for (int j = 0; j < sign.length; j++) {
                    if (sign[j] == changeSign) {
                        sign[j] = targetSin;
                    }
                }
            }
        }
        System.out.println(result.toString());

    }

    class Tubes {
        int u;
        int v;
        int weight;

        public Tubes(int u, int v, Integer weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Tubes{" +
                    "u=" + u +
                    ", v=" + v +
                    ", weight=" + weight +
                    '}';
        }
    }
}
