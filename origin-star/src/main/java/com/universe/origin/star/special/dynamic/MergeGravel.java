package com.universe.origin.star.special.dynamic;

public class MergeGravel {
    public static void main(String[] args) {
        int[] gravel = new int[]{5,8,6,9,2,3};
        mergeGravel(gravel);

    }

    /**
     * 石子合并
     * 要求每次合并必须合并相邻的
     * 使用数组gravel[] 来记录每个位置石子的数量  使用min[][] 来记录最小的合并数量 使用max[][]来记录最大的合并数量 sum[] 记录前i堆石子的总数量
     * 递归式  fn = min(f(0,k) + f(k+1,n) + sum[k+1] - sum[k-1])
     * 结果由两个最优解组成，最优解再合并为一个最优解
     * @param gravel
     */
    public static void mergeGravel(int[] gravel){
        // 初始化各个数组
        int[][]min = new int[gravel.length+1][gravel.length+1];
        int[][]max = new int[gravel.length+1][gravel.length+1];
        int[] sum = new int[gravel.length+1];

        sum[0] = 0;
        for (int i = 1; i <= gravel.length; i++) {
            min[i][i] = 0;
            max[i][i] = 0;
            sum[i] = sum[i-1] + gravel[i-1];
        }

        //循环dp表完善min和max
        //从合并两个石子堆开始
        for (int i = 2; i <= gravel.length; i++) {
            // 合并的石子堆数量是i，那么最后一个序列的第一位是 长度-合并石子堆数+1
            for (int j = 1; j <= gravel.length-i+1 ; j++) {
                //在找到这次序列的末尾石子堆位置
                int end = j+i-1;
                //初始化最大值和最小值
                max[j][end] = -1;
                min[j][end] = Integer.MAX_VALUE;
                //无论怎么样最终还是会将i 到j 堆石子合并到一块，这个操作代价是
                int stageSum = sum[end] - sum[j-1];
                //在序列中循环每一个k
                for (int k = j; k < end; k++) {
                    // 比较min
                    max[j][end] = Math.max(max[j][end],max[j][k]+max[k+1][end] +stageSum);
                    min[j][end] = Math.min(min[j][end],min[j][k]+min[k+1][end] +stageSum);
                }
            }

        }


    }
}
