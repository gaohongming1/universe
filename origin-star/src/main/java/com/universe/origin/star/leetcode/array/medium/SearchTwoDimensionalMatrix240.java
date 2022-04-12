package com.universe.origin.star.leetcode.array.medium;

/**
 * 240. 搜索二维矩阵 II
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 */
public class SearchTwoDimensionalMatrix240 {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{-1, 3}};
        SearchTwoDimensionalMatrix240 searchTwoDimensionalMatrix240 = new SearchTwoDimensionalMatrix240();
        searchTwoDimensionalMatrix240.dfs(arr, 3, 0, 0, new int[arr.length][arr[0].length]);
    }

    /**
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列
     * 使用dfs 找到之后返回
     * 还可以进行优化进行二分查找
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix[0][0] > target) {
            return false;
        }
        if (matrix[0][0] == target) {
            return true;
        }
        int[][] visited = new int[matrix.length][matrix[0].length];
        return dfs(matrix, target, 0, 0, visited);
    }

    public boolean dfs(int[][] matrix, int target, int x, int y, int[][] visited) {
        if (x < 0 || x > matrix.length - 1 || y < 0 || y > matrix[0].length - 1) {
            return false;
        }
        visited[x][y] = 1;
        if (matrix[x][y] == target) {
            return true;
        }
        // 因为是递增有序的 所有遍历只会向下和向右进行 也就是只有 matrix[x][y]<target 的情况才会继续dfs
        if (matrix[x][y] > target) {
            return false;
        }
        boolean rightStatus = false;
        if (y + 1 <= matrix[0].length - 1 && visited[x][y + 1] == 0) {
            rightStatus = dfs(matrix, target, x, y + 1, visited);
        }

        boolean downStatus = false;
        if (!rightStatus && x + 1 <= matrix.length - 1 && visited[x + 1][y] == 0) {
            downStatus = dfs(matrix, target, x + 1, y, visited);
        }

        return rightStatus || downStatus;
    }
}
