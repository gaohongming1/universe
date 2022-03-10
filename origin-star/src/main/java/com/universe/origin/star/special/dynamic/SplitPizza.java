package com.universe.origin.star.special.dynamic;

import java.util.Arrays;

/**
 * 凸边形最优三角分割问题
 * 划分出的三角形的权值之和是最小的
 * 首先凸多边形的任意三个点之间一定能组成一个三角形
 * todo 待解决
 */
public class SplitPizza {
    public static void main(String[] args) {

    }


    /**
     * 最优凸边型分割问题
     * 0下标不使用 从1开始
     * 假设求N个顶点的凸边型最优解，那么假设存在一个位置 将凸多边形分割成两半，达到最优  也就是  f(n) = f(1,k-1) + f(k+1,n) + w(k-1,k,k+1)
     * 那么问题规模可以杯不断额缩小，求三个顶点的最优解、四个顶点的最优解、五个顶点的最优解，由底向上进行推拿
     * 状态转移公式：dp[i][j] = min{dp[i][k] + d[k+1][j] + w(i-1,k,j  )}  k 表示问题的规模，从3开始到n规模结束
     */
    public void bestSplit(int[][] polygon) {
        if (polygon.length <= 3) {
            return;
        }
        int len = polygon.length + 1;

        int[][] dp = new int[len][len];
        // 问题规模从1 个三角形  2个三角形依次上升 最多可以组成n-2 个三角形
        for (int k = 1; k < len - 2; k++) {
            //循环求解  i i+1 i+2 ...j 的最优三角形
            for (int i = 1; i <= k; i++) {
                // j就是问题的右边界  3  4  5 6
                int j = i + k + 2 - 1;

                //剩下的也就是求解  顶点集合 （i->j） 组成的凸边型的最优解
                int min = Integer.MAX_VALUE;
                int index = 0;
                for (int l = i; l < j; l++) {
                    int current = dp[i][l] + dp[l + 1][j] + sum(i, l, j,polygon);
                    if (current < min) {
                        index = l;
                        min = current;
                    }
                }
                dp[i][j] = min;
            }
        }

        // 输出
        System.out.println("dp求解数组");
        for (int i = 1; i <= len; i++) {
            System.out.println(Arrays.toString(dp[i]));
            System.out.println("   ");
        }


    }

    public static int sum(int i, int j, int k, int[][] polygon) {
        return polygon[i - 1][j - 1] + polygon[j - 1][k - 1] + polygon[k - 1][i - 1];
    }
}
