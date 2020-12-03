package com.universe.origin.star.special.flash;

import java.util.Arrays;

/**
 * 地图着色
 * 就是图相关的算法，地图抽象成无向图，找到所有的解
 * 假设三种颜色进行
 * 1 2 3 三种颜色
 */
public class MapColoring {

    public static void main(String[] args) {
        //邻接矩阵存储图 0代表相连否则不相连
        int[][] adjacencyMatrix = new int[][]{
                {0, 1, 1, 1, 0, 0, 0},
                {1, 0, 1, 0, 1, 0, 0},
                {1, 1, 0, 1, 1, 0, 0},
                {1, 0, 1, 0, 1, 0, 1},
                {0, 1, 1, 1, 0, 1, 1},
                {0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 1, 1, 0},
        };
        //记录所有的结果
        int[][] allResult = new int[adjacencyMatrix.length][adjacencyMatrix.length];
        int[] currentValue = new int[adjacencyMatrix.length];
        coloring(adjacencyMatrix,allResult,0,currentValue,3,0);
        for (int i = 0; i < allResult.length; i++) {
            System.out.println(Arrays.toString(allResult[i]));
        }


    }

    /**
     * 地图着色
     *
     * @param adjacencyMatrix 邻接矩阵
     * @param allResult       所有的结果集
     * @param i               当前点
     */
    public static int coloring(int[][] adjacencyMatrix, int[][] allResult, int i, int[] currentValue, int color, int resultIndex) {

        /**
         * 判断当前节点的着色
         * 循环已经着色的点 ,如果相连则判断能否着色当前颜色
         * 循环颜色
         */

        if (i > adjacencyMatrix.length-1) {
            //将当前结果保存到全部结果中
            for (int j = 0; j < currentValue.length; j++) {
                allResult[resultIndex][j] = currentValue[j];
            }
            resultIndex += 1;
            return resultIndex;
        }

        for (int j = 1; j <= color; j++) {
            // 记录当前节点的颜色尝试
            if (isOk(adjacencyMatrix, j, currentValue, i)) {
                // 当前节点染色j
                currentValue[i] = j;
                //向下递归 如果这条路最终能走到叶子节点，则会加入到allResult
                resultIndex = coloring(adjacencyMatrix, allResult, i + 1, currentValue, color, resultIndex);
                //由于这里是for循环尝试其他的道路，所以不用手动回溯，下次currentValue[i] = j;  会将本次循环值覆盖，直到达到叶子节点保存结果
            }
        }
        return resultIndex;
    }


    /**
     * 判断当前节点是否可以进行找色
     * 循环已经作色的点 当相邻再判断颜色是否相等
     *
     * @param adjacencyMatrix 邻接矩阵
     * @param colorIndex 判断的颜色
     * @param currentValue 当前值列表
     * @param i 当前节点
     * @return
     */
    public static boolean isOk(int[][] adjacencyMatrix, int colorIndex, int[] currentValue, int i) {
        for (int j = 0; j < i; j++) {
            if (adjacencyMatrix[j][i] == 1) {
                if (currentValue[j] == colorIndex) {
                    return false;
                }
            }
        }
        return true;
    }


}
