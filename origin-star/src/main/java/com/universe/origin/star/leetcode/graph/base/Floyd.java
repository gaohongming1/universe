package com.universe.origin.star.leetcode.graph.base;

/**
 * 虽然我们可以直接对每个顶点通过迪杰斯特拉算法求得所有的顶点到所有顶点的时间复杂度，时间复杂度为O(n*3),但是弗洛伊德算法更加简洁优雅
 */
public class Floyd {


    /**
     * Integer.max代表无法连通
     * @param matrix
     */
    public void floyd(int[][] matrix){
        int[][] dist = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            // i是中间点  j是起点
            for (int j = 0; j < matrix.length; j++) {
                // k是终点 更新终点
                for (int k = 0; k < matrix.length; k++) {
                    if (matrix[j][k] > matrix[i][j] + matrix[j][k]){
                        matrix[j][k] = matrix[i][j] + matrix[j][k];
                        dist[i][k] = j;
                    }
                }
            }
        }
    }
}
