package com.universe.origin.star.special.dynamic;

/**
 * 最优二叉搜索树
 * todo 待解决
 */
public class OptimalBinarySearchTree {
    public static void main(String[] args) {

    }


    /**
     * 入参分别是查找成功概率和查找失败概率
     * 另 x轴代表是实节点的查找概率  y轴代表是虚节点的查找
     * 则 i j  位置则代表  前i个虚节点和前j个实节点的最优二叉搜索树   还有一种情况是没有实节点只有虚节点 也就是（1，0） 也就是只有一个虚节点的概率   所以x周长度=虚节点长度
     */
    public void bestBuild(double[] success, int[] fail) {
        int len = fail.length + 1;
        //代表搜索概率之和
        int[][] dp = new int[len][len];
        //代表搜索成本
        int[][]res = new int[len][len];
        int[][] root = new int[len][len];

        // 初始化矩阵   只有一个虚节点的概率
        for (int i = 1; i < len; i++) {
            dp[i][i] = fail[i];
        }

        // 问题规模从1个实节点依次添加  也就是x轴从 2开始
        for (int k = 1; k < success.length; k++) {
            for (int i = 1; i < len; i++) {
                // 问题的右边界
                int j = i + k;
                // (i,j) 代表 虚节点i 到实节点j 这些点的最优二叉搜索树 实节点j的搜索概率是 success[j-2]（因为success的下标从0开始的）对应虚节点j的搜索概率是fail[j-1] 在虚节点的右侧
                double min = dp[i][j - 1] + success[j-1-1] + fail[j-1];

                for (int index = i; index < j; index++) {
                    double current = dp[i][index] + dp[index + 1][j] + dp[i][j];
                }

            }
        }

    }
}
