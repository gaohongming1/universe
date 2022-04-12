package com.universe.origin.star.leetcode.array.hard;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 85. 最大矩形
 */
public class MaximumRectangle85 {

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        MaximumRectangle85 maximumRectangle85 = new MaximumRectangle85();
        maximumRectangle85.maximalRectangle(matrix);
    }


    /**
     * 分析
     * 将每一行的元素   i  记录以i为起点的连续为1 的最大高度
     * 循环求解每一行
     * 问题被转化为84题
     */

    public int maximalRectangle(char[][] matrix) {
        int ans = 0;
        int w = matrix[0].length;
        int[][] height = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == '1') {
                height[0][i] = 1;
            } else {
                height[0][i] = 0;
            }
        }
        // 计算高度
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    height[i][j] = height[i - 1][j] + 1;
                } else {
                    height[i][j] = 0;
                }
            }
        }

        // 针对每一行采用84题的算法  先从最后一行开始
        for (int i = matrix.length - 1; i >= 0; i--) {

            // 使用单调栈找到这一行元素的左右边界
            int[] left = new int[w];
            int[] right = new int[w];
            Deque<Integer> deque = new LinkedList<>();
            Arrays.fill(right, w);

            for (int j = 0; j < w; j++) {
                while (!deque.isEmpty() && height[i][deque.peek()] >= height[i][j]) {
                    int index = deque.pop();
                    right[index] = j;
                }
                left[j] = deque.isEmpty() ? -1 : deque.peek();
                deque.push(j);
            }

            deque.clear();

            // 求解当前行所能组成的最大值
            for (int k = 0; k < w; k++) {
                if (height[i][k] == 0) {
                    continue;
                }
                int lrange = left[k];
                int rRange = right[k];
                ans = Math.max(ans, (rRange - lrange - 1) * height[i][k]);
            }
        }
        return ans;
    }


    /**
     * 先求出每个位置从上到下的累加值
     * 然后求解钙元素左右两边大于这个元素的总和  就可以计算出面积  相比于上面的解法主要是可以使用剪枝函数
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle2(char[][] matrix) {
        int ans = 0;
        int w = matrix[0].length;
        int[][] height = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == '1') {
                height[0][i] = 1;
            } else {
                height[0][i] = 0;
            }
        }
        // 计算高度
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    height[i][j] = height[i - 1][j] + 1;
                } else {
                    height[i][j] = 0;
                }
            }
        }

        // 针对每一行采用84题的算法  先从最后一行开始
        for (int i = matrix.length - 1; i >= 0; i--) {

            for (int j = 0; j < w; j++) {
                // 主要是能够使用这个剪枝函数
                if (w * height[i][j] <= ans) {
                    continue;
                }

                // 找到j元素左右两边大于j元素的值
                int jw = 1;
                int lindex = j;
                int rindex = j;
                while (lindex - 1 >= 0 && height[i][lindex - 1] >= height[i][j]) {
                    jw++;
                    lindex--;
                }

                while (rindex + 1 <= w - 1 && height[i][rindex + 1] >= height[i][j]) {
                    rindex++;
                    jw++;
                }
                ans = Math.max(ans, jw * height[i][j]);

            }
        }
        return ans;
    }


}
