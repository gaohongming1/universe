package com.universe.origin.star.leetcode.queue.hard;

/**
 * 给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。
 * <p>
 * 示例:
 * <p>
 * 输入: matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出: 2
 * 解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * 说明：
 * <p>
 * 矩阵内的矩形区域面积必须大于 0。
 * 如果行数远大于列数，你将如何解答呢？
 * <p>
 * ST算法学习
 */
public class MatrixSumK363 {
    public static void main(String[] args) {
        MatrixSumK363 matrixSumK363 = new MatrixSumK363();
//        int[][] matrix = new int[][]{
//                {5, -4, -3, 4},
//                {-3, -4, 4, 5},
//                {5, 1, 5, -4}
//        };
        int[][] matrix = new int[][]{{2,2,-1}};
        matrixSumK363.maxSumSubmatrix(matrix, 0);

    }

    /**
     * 思路是暴力求解每个方块的值
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] sumMatrix = new int[matrix.length][matrix[0].length];

        // 初始化第一列
        sumMatrix[0][0] = matrix[0][0];
        for (int i = 1; i < row; i++) {
            sumMatrix[i][0] = sumMatrix[i - 1][0] + matrix[i][0];
        }

        for (int i = 1; i < col; i++) {
            sumMatrix[0][i] = sumMatrix[0][i - 1] + matrix[0][i];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                sumMatrix[i][j] = sumMatrix[i][j - 1] + sumMatrix[i - 1][j] - sumMatrix[i - 1][j - 1] + matrix[i][j];
            }
        }


        int best1 = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            //循环列进行计算 在i行位置  0-j列的面积以及值是否满足条件
            for (int j = 0; j < col; j++) {
                // 计算0-j列之间的最大满足条件的面积
                for (int m = 0; m <= i; m++) {
                    for (int l = 0; l <= j; l++) {
                        int sum = -1;
                        if (m-1<0){
                            if (l==0){
                                sum = sumMatrix[i][j];
                            }else {
                                sum = sumMatrix[i][j] - sumMatrix[i][l-1];
                            }
                        }else {
                            if (l==0){
                                sum = sumMatrix[i][j] - sumMatrix[m-1][j];
                            }else {
                                sum = sumMatrix[i][j] - sumMatrix[m-1][j] - sumMatrix[i][l-1] + sumMatrix[m-1][l-1];
                            }

                        }
                        if (sum <= k && sum > best1) {
                            System.out.println("找到一个当前最优解");
                            best1 = sum;
                        }

                    }

                }
            }
        }
        return best1;
    }
}
