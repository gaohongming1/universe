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
        int[] s = Arrays.copyOf(weight, weight.length);
        s[2] = 100;
        System.out.println(Arrays.toString(weight));
        System.out.println(Arrays.toString(s));


    }

    /**
     * bfs 搜索
     *
     * @param weight
     * @param value
     */
    public void calc(int[] weight, int[] value, int maxWeight) {

        //定义当前最优解
        int bestValue = 0;

        // 第一个节点剩余物品价值
        int surplusValue = 0;
        int surplusWeight = 0;
        int length = weight.length;
        // 节点的状态 初始节点都是未加入状态
        int[] currentValueStatus = new int[length];
        // 计算剩余物品的价值
        for (int i = 0; i < weight.length; i++) {
            surplusValue += value[i];
            surplusWeight += weight[i];
        }
        Node firstNode = new Node(0, surplusValue, surplusWeight, currentValueStatus, 0);
        //第一个几点入队
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(firstNode);
        // 深度遍历出队节点将子节点入队
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int index = currentNode.index;

            // 判断当前节点能否加入 查看剩余容量是否大于当前节点
            if (currentNode.surplusValue <= weight[index]) {
                // 加入则计算需要构造的新的Node
                int nowCurrentValue = currentNode.currentValue + weight[index];
                int nowSurplusValue = currentNode.surplusValue - value[index];
                int nowSurplusWeight = currentNode.surplusWight - weight[index];
                int[] nowCurrentValueStatus = Arrays.copyOf(currentNode.currentValueStatus, length);
                Node addNode = new Node(nowCurrentValue, nowSurplusValue, nowSurplusWeight, nowCurrentValueStatus, index + 1);
                //写个节点入队
                queue.add(addNode);
            }
            // 右孩子入队 判断剩余物品价值是否大于当前最好的
            // 不管当前的节点入不入队，前置的节点加上剩余物品的总价值大于等于当前最优值才能继续走下去
            /**
             * 如果当前节点不加入则 已经加入的和剩余物品的总价值应该大于当前的最优值才能走下去
             */
            int valueWithoutCurrentNode = currentNode.currentValue + currentNode.surplusValue - value[index];
            if (valueWithoutCurrentNode >= bestValue){

            }





        }


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



