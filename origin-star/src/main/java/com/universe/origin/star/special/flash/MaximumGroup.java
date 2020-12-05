package com.universe.origin.star.special.flash;

import java.util.Arrays;

/**
 * 最大团问题
 * 护卫队两两不是仇人
 * 也就是求无向图的最大完全子图
 * 这个类似背包问题，考虑X节点的加入符不符合加入条件，负责条件则加入，然后看加入后剩余节点加入的最大价值
 */
public class MaximumGroup {
    public static void main(String[] args) {
        //邻接矩阵存储图 0代表相连否则不相连
        int[][] adjacencyMatrix = new int[][]{
                {0, 1, 1, 1, 1},
                {1, 0, 1, 0, 0},
                {1, 1, 0, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0}};
        //保存当前的节点加入状态
        int[] currentStatus = new int[adjacencyMatrix.length];
        //保存最好结果的节点加入状态
        int[] bestStatus = new int[adjacencyMatrix.length];
        //代表最好结果的人数
        int bestValue = maxCompletelyGroup(adjacencyMatrix, 0, currentStatus, bestStatus, 0, 0);
        System.out.println(bestValue);
        System.out.println(Arrays.toString(bestStatus));


    }

    public static Integer maxCompletelyGroup(int[][] adjacencyMatrix, int i, int[] currentStatus, int[] bestStatus, int bestVale, int currentValue) {

        /**
         * 递归的终点，最优解一定是到达叶子节点的
         */
        if (i > adjacencyMatrix.length - 1) {
            //记录最优解
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                bestStatus[j] = currentStatus[j];
            }
            bestVale = currentValue;
            return bestVale;
        }


        /**
         * 节点判断阶段，如果满足加入条件则加入，递归下个节点，拿到最优值后进行回溯，选择不加入这个节点
         */
        if (isAccordWithJoin(adjacencyMatrix, i, currentStatus)) {
            //代表可以加入
            currentStatus[i] = 1;
            //当前值加一
            currentValue += 1;

            //向下递归节点
            bestVale = maxCompletelyGroup(adjacencyMatrix, i + 1, currentStatus, bestStatus, bestVale, currentValue);

            //将当前节点回溯走下面的判断
            currentValue -= 1;
        }

        /**
         * 减枝函数 进行粗略判断下面的能否加入
         * 假设剩下的节点都能加入，则判断和当前最优值的大小，否则则没必要向下扩展
         */
        if ((currentValue + adjacencyMatrix.length - i - 1) > bestVale) {
            //表示当前节点走不加入的道路，递归计算不加入情况下剩余节点和已加入节点的值
            currentStatus[i] = 0;
            bestVale = maxCompletelyGroup(adjacencyMatrix, i + 1, currentStatus, bestStatus, bestVale, currentValue);
        }


        return bestVale;
    }

    /**
     * 限界条件
     * 判断当前节点是否能够加入
     * 循环截止到当前顶点，如果有不相连的则不能加入
     *
     * @param adjancencyMatrix
     * @param i
     * @param currentStatus
     * @return
     */
    public static boolean isAccordWithJoin(int[][] adjancencyMatrix, int i, int[] currentStatus) {
        for (int j = 0; j < i; j++) {
            //判断当前节点如果在子图中则判断是否和i有边，没有false
            if (currentStatus[j] == 1) {
                if (adjancencyMatrix[j][i] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
