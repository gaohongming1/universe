package com.universe.origin.star.special.greedy;

import java.util.*;

/**
 * 贪心算法
 * 将输入存储在临界矩阵中
 * 两个集合已到集合 v  未到集合s
 * 最短距离数组dist  和前驱数组p
 */
public class Dijkstra {

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        int max = Integer.MAX_VALUE;
        int[][] matrix = new int[][]{
                {max, 2, 5, max, max},
                {max, max, 2, 6, max},
                {max, max, max, 7, 1},
                {max, max, 2, max, 4},
                {max, max, max, max, max}
        };
        System.out.println(dijkstra.dijkstra(matrix,0));
    }

    /**
     * 给定节点邻接矩阵返回最短路径
     *
     * @return
     */
    public Map<Integer, Integer> dijkstra(int[][] matrix, int v) {
        Map<Integer, Integer> res = new HashMap<>();
        // 最短距离数组
        int[] dist = new int[matrix.length];
        //前驱数组
        int[] precursor = new int[matrix.length];
        //代表节点是否访问
        boolean[] flag = new boolean[matrix.length];
        Arrays.fill(flag, false);
        flag[v] = true;
        //初始化最短距离数组和前驱节点
        for (int i = 0; i < matrix.length; i++) {
            if (i == v) {
                dist[i] = 0;
            }
            dist[i] = matrix[v][i];
            if (dist[i] != Integer.MAX_VALUE) {
                precursor[i] = v;
            }else {
                precursor[i] = -1;
            }
        }
        int currentNode = v;
        while (res.size() < matrix.length-1) {
            // 找到当前最短距离数组中的最小值 这一步也就是贪心的思想每次找到离起点最小的点
            int min = Integer.MAX_VALUE;
            //这一部分可以进行优化为优先队列方式
            for (int i = 0; i < dist.length; i++) {
                if (dist[i] < min && !flag[i]) {
                    min = dist[i];
                    currentNode = i;
                }
            }

            // 记录current 到下个节点的最小值
            res.put(currentNode, min);
            flag[currentNode] = true;

            // 然后根据节点currentNode更新最短距离数组
            for (int i = 0; i < matrix.length; i++) {
                // 未访问过并且 current 到i 有通路
                if (!flag[i] && matrix[currentNode][i] != Integer.MAX_VALUE) {

                    // 判断起始点到i  和起始点到currentNode  再到i  的距离长短并更新
                    if (dist[i] > matrix[currentNode][i] + dist[currentNode]) {
                        dist[i] = matrix[currentNode][i] + dist[currentNode];
                        precursor[i] = currentNode;
                    }
                }
            }
        }
        System.out.println("pre:"+Arrays.toString(precursor));
        return res;
    }
}
