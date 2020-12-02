package com.universe.origin.star.special.flash;

import java.util.Arrays;

//回溯法
public class flashBack {

    private static int MAX_WEIGHT = 10;

    public static void main(String[] args) {
        int[] weight = new int[]{2,5,4,2};
        int[] value = new int[]{6,3,5,4};
        int[] bestItemStatus = new int[value.length];
        int[] itemStatus = new int[value.length];
        System.out.println(backTrack(0,0,bestItemStatus,itemStatus,value,weight,0,0));
        System.out.println(Arrays.toString(bestItemStatus));
    }


    /**
     *
     * @param i 表示当前节点层级
     * @param bestResult 最优结果
     * @param bestItemStatus 最优结果状态
     * @param itemStatus 各个节点是否加车
     * @param value 价值列表
     * @param weight 重量列表
     * @param currentValue 当前值
     * @param currentWeight 当前重量
     */
    public static int backTrack(int i,int bestResult,int[] bestItemStatus,int[] itemStatus,int[] value,int[] weight ,int currentValue,int currentWeight){

        //已经达到叶子节点 走到这里代表最优解可以被替换
        if (i>value.length-1){
            for (int j = 0; j < value.length; j++) {
                bestItemStatus[j] = itemStatus[j];
            }
            bestResult = currentValue;
            return bestResult;
        }

        //搜索
        if (currentWeight + weight[i] <= MAX_WEIGHT){
            itemStatus[i] = 1;
            currentValue += value[i];
            currentWeight += weight[i];

            bestResult = backTrack(i+1,bestResult,bestItemStatus,itemStatus,value,weight,currentValue,currentWeight);

            // 回溯
            currentValue -=value[i];
            currentWeight-=weight[i];
        }

        if (bound(i+1,currentValue,value)>bestResult){
            itemStatus[i] = 0;
            bestResult = backTrack(i+1,bestResult,bestItemStatus,itemStatus,value,weight,currentValue,currentWeight);
        }
        return bestResult;
    }

    /**
     * 计算剩余价值
     * @param i
     * @param currentValue
     * @param value
     * @return
     */
    public static int bound(int i, int currentValue,int[] value){
        for (int j = i; j <value.length ; j++) {
            currentValue += value[j];
        }
        return currentValue;
    }


}
