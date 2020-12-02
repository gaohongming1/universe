package com.universe.origin.star.special.dynamic;

public class OneZeroPackage {
    public static void main(String[] args) {
        int[] w = new int[] {2,5,4,2,3};
        int[] value = new int[]{6,3,5,4,6};
        oneZeroPackage(w,value,10);

    }

    /**
     * 01背包问题（无法切割）
     * 使用动态规划，核心思想是子问题最优解向上递推出总问题最优解
     * 递归式
     * c[i][j] 代表i个物品放在容量为j的背包中最大的价值，矩阵最后的解就是最终解
     * c[i][j]  = c[i-1][j]  j<wi     代表当第i个物品大于当前的总价值j那么肯定不放入背包 背包内价值取截止前一个物品放入背包的价值
     * c[i][i] = max(c[i-1][j] ， c[i-1][j- wi] + vi) j>=wi 存在放入和不放入两种情况，如果放入，那么就要在背包中预留出wi 的空间 也就是 j-wi
     * 计算的是前i-1 个物品
     * @param w 重量数组
     * @param value 值数组
     * @param capacity 容量
     */
    public static void oneZeroPackage(int[] w,int[] value,int capacity){
        // 初始化dp表
        int[][]c = new int[w.length+1][capacity+1];
        for (int i = 0; i < w.length+1; i++) {
            c[i][0] = 0;
        }
        for (int i = 0; i < capacity + 1; i++) {
            c[0][i] = 0;
        }

        //推导dp表
        for (int i = 1; i <=w.length ; i++) {
            for (int j = 1; j <=capacity; j++) {

                //计算c[i][j]
                // 当前物品重量大于背包总价值 则不放入这个物品，取截止到上一个物品放入背包的价值
                if (w[i-1]>j){
                    c[i][j] = c[i-1][j];
                }else {
                    c[i][j] = Math.max(c[i-1][j],c[i-1][j-w[i-1]] + value[i-1]);
                }
            }
        }

        //输出最优解，从最后一个开始判断
        int j = capacity;
        for (int i = w.length; i >0 ; i--) {
            if (c[i][j]>c[i-1][j]){
                //代表第i个物品放入背包
                System.out.println("第"+i+"个物品");
                //背包减去第i个物品的容量w
                j = j - w[i-1];
            }
        }
        System.out.println("完成");
    }
}
