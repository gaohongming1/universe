package com.universe.origin.star.special.dynamic;

public class YachtHire {
    public static void main(String[] args) {
        int[][] r = new int[][]{
                {0, 2, 6, 9, 15, 20},
                {0, 0, 3, 5, 11, 18},
                {0, 0, 0, 3, 6, 12},
                {0, 0, 0, 0, 5, 8},
                {0, 0, 0, 0, 0, 6},
                {0, 0, 0, 0, 0, 0},
        };
        yachtHire(r);

    }

    /**
     * 游艇租聘
     * 初始化最小距离数组和源数组
     *
     * @param r
     * @return
     */
    public static void yachtHire(int[][] r) {

        // 初始化距离数组和源数组
        int[][] dist = new int[r.length][r.length];
        int[][] min = new int[r.length][r.length];

        for (int i = 0; i < r.length; i++) {
            for (int j = i + 1; j < r.length; j++) {
                dist[i][j] = r[i][j];
                min[i][j] = 0;
            }
        }

        //进行距离求解，先求解子问题停靠三个站点
        for (int d = 2; d < r.length; d++) {
            for (int i = 0; i < r.length - d; i++) {
                //记录本次迭代i对应的终点
                int end = i + d;
                for (int j = i + 1; j < end; j++) {
                    //求解 当j为子问题分界点时候，i到end的最小值
                    // 计算 i到j  j到end的最优解和 i直接到end的最优解判断大小
                    int temp = dist[i][j] + dist[j][end];
                    if (temp < dist[i][end]) {
                        dist[i][end] = temp;
                        min[i][end] = j;
                    }
                }
            }
        }
        print(0, r.length - 1, min);
    }

    public static void print(int i, int end, int[][] min) {
        if (min[i][end] == 0) {
            System.out.println(end);
            return;
        }

        print(i, min[i][end], min);
        print(min[i][end], end, min);
    }
}
