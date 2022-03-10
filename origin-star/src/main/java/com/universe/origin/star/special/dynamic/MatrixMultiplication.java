package com.universe.origin.star.special.dynamic;

import java.util.Arrays;

/**
 * 矩阵乘法
 */
public class MatrixMultiplication {


    public static void main(String[] args) {
        MatrixMultiplication matrixMultiplication = new MatrixMultiplication();
        MatrixArray a1 = new MatrixArray(3, 5);
        MatrixArray a2 = new MatrixArray(5, 10);
        MatrixArray a3 = new MatrixArray(10, 8);
        MatrixArray a4 = new MatrixArray(8, 2);
        MatrixArray a5 = new MatrixArray(2, 4);
        MatrixArray[] matrixArrays = new MatrixArray[]{a1, a2, a3, a4, a5};
        matrixMultiplication.optimalCalc(matrixArrays);
    }


    /**
     * 矩阵连乘
     * f(n) 代表n个矩阵连乘的最优解
     * n个矩阵的连乘最优解的子集   假设最后一步在第K个矩阵分割  两边达成最优解   则 f(i,j) = f(i,k) + f(k+1,j) + kx * ky * (k+1)y  k代表需要循环求解更小规模矩阵乘级的最优解
     * 综上所诉  dp[i][j]  = Min{dp[i][k] + dp[k+1][j] + kx * ky * (k+1)y }
     * 注意这类问题不是 有序递增规模问题，而是属于分堆问题
     * 两个矩阵乘法计算公式： （m,n） * (n,k)  只有前一个矩阵 的列等于下一个矩阵的行 才是可乘的 计算的结果是 m*n*kn
     * <p>
     * 思想是先将问题规模建最小化进行求解  也就是两个矩阵最优解
     */
    public void optimalCalc(MatrixArray[] matrixArrays) {
        // 初始化dp数组
        int len = matrixArrays.length;
        int[][] dp = new int[len + 1][len + 1];
        int[][] bestIndex = new int[len + 1][len + 1];
        for (int i = 0; i <= len; i++) {
            dp[i][i] = 0;
        }

        // k代表子问题的规模 依次递增 规模从2开始 规模最大则是len
        for (int k = 2; k <= len; k++) {
            // i 代表子问题的起始元素位置 最大为 len-k
            for (int i = 1; i <= len - k + 1; i++) {
                // 所以子问题的末尾元素位置是子问题规模的大小 当规模是全部的时候 也就是这个问题的解
                int j = i + k - 1;
                int min = Integer.MAX_VALUE;
                int minIndex = 0;

                for (int index = i; index < j; index++) {
                    int temp = dp[i][index] + dp[index + 1][j] + calculationTimes(matrixArrays[i - 1], matrixArrays[index], matrixArrays[j - 1]);
                    if (min > temp) {
                        min = temp;
                        minIndex = index;
                    }
                }

                // 记录子问题最优解 [i,j] 的最优解是min  进行分解的位置是k
                dp[i][j] = min;
                bestIndex[i][j] = minIndex;
            }
        }

        // 输出
        System.out.println("dp求解数组");
        for (int i = 1; i <= len; i++) {
            System.out.println(Arrays.toString(dp[i]));
            System.out.println("   ");
        }

        System.out.println("求解路径");
        for (int i = 1; i <= len; i++) {
            System.out.println(Arrays.toString(bestIndex[i]));
            System.out.println("   ");
        }


    }


    /**
     * a b c a * bc 的运算量计算公式 = a.x * b.x * c.x
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int calculationTimes(MatrixArray a, MatrixArray b, MatrixArray c) {
        return a.x * b.x * c.y;
    }

}

class MatrixArray {
    public int x;
    public int y;

    public MatrixArray(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
