package com.universe.origin.star.leetcode.array.medium;

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，
 * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 现有矩阵 matrix 如下：
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * <p>
 * 给定 target = 20，返回 false。
 * [[1,2,3,4,5],
 * [6,7,8,9,10],
 * [11,12,13,14,15],
 * [16,17,18,19,20],
 * [21,22,23,24,25]]
 * 5
 */
public class SearchInTwoDimensionalArrayoffer04 {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 13},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25},
        };
        int[][] arr2 = new int[][]{{}};
        SearchInTwoDimensionalArrayoffer04 searchInTwoDimensionalArrayoffer04 = new SearchInTwoDimensionalArrayoffer04();
        searchInTwoDimensionalArrayoffer04.findNumberIn2DArray(arr, 5);
    }


    /**
     * 思路
     * 先向左边查找，如果当前元素的下个元素大于target则向下移动。
     * 如果下个元素的
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        /**
         * 从第一行的最后一个元素开始查找
         * 如果当前元素大于target 则向左移动
         * 如果当前元素小于target则向下移动
         */
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int x = matrix[0].length - 1;
        int y = 0;
        while (x >= 0 && y <= matrix.length - 1) {
            // 判断当前元素
            if (matrix[y][x] == target){
                return true;
            }
            if (matrix[y][x] > target){
                // 横坐标减1
                x -=1;
            }else {
                //纵坐标加1
                y +=1;
            }
        }
        return false;

    }
}
