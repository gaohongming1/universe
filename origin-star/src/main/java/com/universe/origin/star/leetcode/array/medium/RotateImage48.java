package com.universe.origin.star.leetcode.array.medium;

/**
 * 48. 旋转图像
 */
public class RotateImage48 {

    /**
     * 旋转公式 前一个坐标 ->下一个坐标
     * (x,y) -> (y,n-x)
     * (x,y) ->(y,n-x)
     * (x,y) ->(y,n-x)
     * (x,y) ->(y,n-x)
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        int n = len - 1;
        int flow = len / 2;
        for (int i = 0; i < flow; i++) {
            for (int j = i; j < len - 1 - i; j++) {
                int x1 = matrix[i][j];
                int x2 = matrix[j][n - i];
                int x3 = matrix[n - i][n - j];
                int x4 = matrix[n - j][n - (n - i)];

                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j][n - (n - i)];
                matrix[n - j][n - (n - i)] =  matrix[n - i][n - j];
                matrix[n - i][n - j] = matrix[j][n - i];
                matrix[j][n - i] = temp;
            }
        }
    }
}
