package com.universe.origin.star.special.flash;


import java.util.Arrays;

/**
 * BestMachiningOrder简介
 * 最优加工策略
 * N个零件
 * 在两台机器上加工x1  x2 x1加工完才能放到x2机器上加工
 * 求最优加工策略
 */
public class BestMachiningOrder {
    public static void main(String[] args) {
        // 使用两个数组代表零件在两台机器上的加工时间
        int[] workTime3 = new int[]{5, 1, 8, 5, 3, 4};
        int[] workTime4 = new int[]{7, 2, 2, 4, 7, 4};
        int[] partStatus2 = new int[]{0, 1, 2, 3, 4, 5};
        int[] bestValueStatus2 = new int[]{0, 1, 2, 3, 4, 5};
        System.out.println(calc(workTime3, workTime4, 0, 0, 0, Integer.MAX_VALUE, partStatus2, bestValueStatus2));
        System.out.println(Arrays.toString(bestValueStatus2));

    }

    /**
     * 进行回溯计算
     *
     * @param workTime1  工作时间1
     * @param workTime2  工作时间2
     * @param i          当前的节点索引
     * @param f1         机器1所需的时间
     * @param f2         机器2所需的时间
     * @param bestValue  当前值的最优解
     * @param partStatus 记录当前零件的选择状态 划分为两个区间，中间点为i 前面的是已经加工的零件的下表存储，后面是还没有进行加工的零件的下标
     */
    public static int calc(int[] workTime1, int[] workTime2, int i, int f1, int f2, int bestValue, int[] partStatus, int[] bestValueStatus) {

        //进行终点的收集 到达叶子节点代表找到了新的最优值 partStatus记录的加工的顺序
        //这里可以进行变种求解所有的加工结果，将剪枝函数去掉，在这里记录结果即可
        if (i > workTime1.length - 1) {
            bestValue = f2;
            for (int j = 0; j < workTime1.length; j++) {
                bestValueStatus[j] = partStatus[j];
            }
            return bestValue;
        }

        //从i开始搜索，还有length-i个零件还未加工 在未加工区间进行搜寻
        for (int j = i; j < workTime1.length; j++) {

            //f1 所需的时间直接累加
            f1 = f1 + workTime1[partStatus[j]];
            //回溯回来的时候使用
            int temp = f2;
            //机器2所需的时间应该从时间线最大值开始计算 也就是等到机器2开始加工零件 partStatus[j]  那么必须等待机器1加工完  或者机器2将上一个零件加工完
            f2 = Math.max(f1 + workTime2[partStatus[j]], f2 + workTime2[partStatus[j]]);

            //减枝函数判断 f2 小于当前的最优值才继续进行递归否则结束，回溯进行下条路的判断
            if (f2 <= bestValue) {
                // 交换j 与i 在partStatus当中的状态,将j交换到已加工区间，这里还有其他的方式，比如数组+标记方式
                swap(partStatus, j, i);
                bestValue = calc(workTime1, workTime2, i + 1, f1, f2, bestValue, partStatus, bestValueStatus);
                //回溯结束 节点状态回退
                swap(partStatus, j, i);
            }
            //将时间回溯
            f1 = f1 - workTime1[partStatus[j]];
            f2 = temp;
        }
        return bestValue;
    }

    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }


}
