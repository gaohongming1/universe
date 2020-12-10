package com.universe.origin.star.special.branch;

import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 分支限界法解决01背包问题
 * 使用队列进行
 * 进行bfs搜索
 */
public class BackPack02 {
    public static void main(String[] args) {
        int[] weight = new int[]{2, 5, 4, 2};
        int[] value = new int[]{6, 3, 5, 4};
        int[] bestValueStatus = new int[weight.length];
        BackPack02 backPack02 = new BackPack02();

        System.out.println(backPack02.calc(weight,value,10,bestValueStatus));
        System.out.println(Arrays.toString(bestValueStatus));
    }

    /**
     * bfs 搜索
     *
     * @param weight
     * @param value
     */
    public Integer calc(int[] weight, int[] value, int maxWeight, int[] bestValueStatus) {
        //定义当前最优解
        int bestValue = 0;

        // 第一个节点剩余物品价值
        int surplusValue = 0;
        int surplusWeight = maxWeight;
        int length = weight.length;
        // 节点的状态 初始节点都是未加入状态
        int[] currentValueStatus = new int[length];
        // 计算剩余物品的价值
        for (int i = 0; i < weight.length; i++) {
            surplusValue += value[i];
        }
        Node firstNode = new Node(0, surplusValue, surplusWeight, currentValueStatus, 0);
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(firstNode);

        // 深度遍历出队节点将子节点入队
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int index = currentNode.index;

            /**
             * 判断这条路是不是走到终点了，要么是没有容量了要么是走到叶子节点了
             * 然后替换最优解。拿到结果之后代表当前路径已经走完，不再生成左右节点
             */
            if (index > (weight.length - 1) || currentNode.surplusWight == 0) {
                if (currentNode.currentValue >= bestValue) {
                    bestValue = currentNode.currentValue;
                    //注意不要用这个，这个是新开辟了地址
                    //bestValueStatus = Arrays.copyOf(currentNode.currentValueStatus, length);

                    for (int i = 0; i < length; i++) {
                        bestValueStatus[i] = currentNode.currentValueStatus[i];
                    }
                }
                continue;
            }

            /**
             * 相当于剪枝函数，避免生成过多的节点
             */
            if ((currentNode.currentValue + currentNode.surplusValue) <bestValue ){
                continue;
            }

            /**
             *判断当前节点能否加入 查看剩余容量是否大于当前节点,大于则代表当前节点可以加入
             */
            if (currentNode.surplusWight >= weight[index]) {
                int nowCurrentValue = currentNode.currentValue + value[index];
                int nowSurplusValue = currentNode.surplusValue - value[index];
                int nowSurplusWeight = currentNode.surplusWight - weight[index];
                int[] nowCurrentValueStatus = Arrays.copyOf(currentNode.currentValueStatus, length);
                nowCurrentValueStatus[index] = 1;
                Node addNode = new Node(nowCurrentValue, nowSurplusValue, nowSurplusWeight, nowCurrentValueStatus, index + 1);
                queue.add(addNode);
            }

            /**
             * 如果当前节点不加入则 已经加入的和剩余物品的总价值应该大于当前的最优值才能走下去
             */
            int valueWithoutCurrentNode = currentNode.currentValue + currentNode.surplusValue - value[index];
            if (valueWithoutCurrentNode >= bestValue) {
                int nowCurrentValue = currentNode.currentValue;
                int nowSurplusValue = currentNode.surplusValue - value[index];
                int nowSurplusWeight = currentNode.surplusWight;
                int[] nowCurrentValueStatus = Arrays.copyOf(currentNode.currentValueStatus, length);
                nowCurrentValueStatus[index] = 0;
                Node addNode = new Node(nowCurrentValue, nowSurplusValue, nowSurplusWeight, nowCurrentValueStatus, index + 1);
                queue.add(addNode);
            }
        }
        return bestValue;

    }

    class Node {
        /**
         * 此节点对应的当前价值
         */
        private Integer currentValue;
        /**
         * 剩余的物品的价值
         */
        private Integer surplusValue;
        /**
         * 剩余的容量
         */
        private Integer surplusWight;
        /**
         * 当前节点对应的解空间
         */
        private int[] currentValueStatus;

        /**
         * 当前节点的索引
         */
        private int index;

        public Node(Integer currentValue, Integer surplusValue, Integer surplusWight, int[] currentValueStatus, int index) {
            this.currentValue = currentValue;
            this.surplusValue = surplusValue;
            this.surplusWight = surplusWight;
            this.currentValueStatus = currentValueStatus;
            this.index = index;
        }


        public Integer getCurrentValue() {
            return currentValue;
        }

        public void setCurrentValue(Integer currentValue) {
            this.currentValue = currentValue;
        }

        public Integer getSurplusValue() {
            return surplusValue;
        }

        public void setSurplusValue(Integer surplusValue) {
            this.surplusValue = surplusValue;
        }

        public Integer getSurplusWight() {
            return surplusWight;
        }

        public void setSurplusWight(Integer surplusWight) {
            this.surplusWight = surplusWight;
        }

        public int[] getCurrentValueStatus() {
            return currentValueStatus;
        }

        public void setCurrentValueStatus(int[] currentValueStatus) {
            this.currentValueStatus = currentValueStatus;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}



