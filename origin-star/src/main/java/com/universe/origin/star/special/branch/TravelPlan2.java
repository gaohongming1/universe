package com.universe.origin.star.special.branch;

import com.sun.java.util.jar.pack.ConstantPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 分支限界法解决旅行问题
 */
public class TravelPlan2 {
    public static void main(String[] args) {
        int[][] adjacencyMartix = new int[][]{
                {-1, 3, -1, 8, 9},
                {3, -1, 3, 10, 5},
                {-1, 3, -1, 4, 3},
                {8, 10, 4, -1, 20},
                {9, 5, 3, 20, -1}};
        Scanner scanner = new Scanner(System.in);
        int originNode = scanner.nextInt();

    }



    public static void calc(int[][] adjacencyMartix,int originNodeIndex){

        // 构造第一个节点的选择
        int length = adjacencyMartix.length;
        int[] currentStatus = new int[length];
        // 构造第一个节点的孩子
        List<Integer> firstNodeChilds = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (adjacencyMartix[originNodeIndex][i] != -1){
                firstNodeChilds
            }
        }
        Node node = new Node(originNodeIndex,currentStatus,)
    }

    class Node{
        //当前节点的索引
        private Integer index;
        //当前各个节点的状态
        private int[] currentStatus;
        // 当前节点的各个子节点，分别代表走哪条路
        private List<Integer> childs;
        // 当前节点对应的最优解
        private int currentBestValue;

        public Node(Integer index, int[] currentStatus, List<Integer> childs, int currentBestValue) {
            this.index = index;
            this.currentStatus = currentStatus;
            this.childs = childs;
            this.currentBestValue = currentBestValue;
        }
    }
}
