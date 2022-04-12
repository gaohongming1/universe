package com.universe.origin.star.leetcode.array.medium;

/**
 * 221. 最大正方形
 * https://leetcode-cn.com/problems/maximal-square/
 * todo 666 关于正方形的dp问题
 */
public class TheLargestSquare221 {

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}
        };
        TheLargestSquare221 theLargestSquare221 = new TheLargestSquare221();
        theLargestSquare221.maximalSquare(matrix);
    }

    public int maximalSquare(char[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                max = Math.max(max, square(matrix, i, j));
            }
        }
        return max;
    }

    public int square(char[][] matrix, int x, int y) {
        int sx = 0;
        for (int i = x; i < matrix.length; i++) {
            if (matrix[i][y] == '1') {
                sx++;
            } else {
                break;
            }
        }

        int sy = 0;
        for (int i = y; i < matrix[0].length; i++) {
            if (matrix[x][i] == '1') {
                sy++;
            } else {
                break;
            }
        }

        int len = Math.min(sy, sx);
        // 环圈遍历 最多len圈
        int loop = 0;
        boolean status = true;
        for (int i = 0; i < len; i++) {
            int xlimit = x + i;
            int ylimit = y + i;
            // 判断回路是否存在0  否则直接返回
            for (int j = 0; j <= i; j++) {
                if (matrix[x + j][y + i] == '0' || matrix[x + i][y + j] == '0') {
                    status = false;
                    break;
                }
            }
            if (status) {
                loop++;
            } else {
                break;
            }
        }

        // 计算面积
        return loop * loop;
    }


    /**
     * 采用动态规划方式
     * 假设 (i,j) 为某个正方形的右下角  则dp[i][j] = min(左上，上，左)+1
     * 具体证明方法看题解
     */
    public int dp(char[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        // 初始化dp
        // 完善dp数组
        int temp = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                temp = 1;
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                temp = 1;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                    continue;
                }
                int current = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                dp[i][j] = current;
                if (temp < current) {
                    temp = current;
                }
            }
        }
        return temp * temp;
    }
}
