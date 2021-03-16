package com.universe.origin.star.leetcode.graph.base;

import java.util.*;

public class GraphBase {
    public static void main(String[] args) {
         int[][] graph = new int[][]{
                 {0, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0}};
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        result.add(0);



        GraphBase graphBase = new GraphBase();
        graphBase.dfs1(graph,0,result,visited);
        System.out.println("bfs1" + result.toString());
        System.out.println("bfs2" + graphBase.dfs2(graph,0));
        System.out.println(graphBase.bfs(graph,0).toString());
        System.out.println("test");

    }


    /**
     * 递归的方式实现dfs
     * 0 代表无连接  1代表有边
     * @param matrix
     * @return
     */
    public void dfs1(int[][] matrix, int node, List<Integer> result, Set<Integer> visited) {
        int[] group = matrix[node];
        for (int i = 0; i < group.length; i++) {
            if (group[i] == 1){
                if (!visited.contains(i)) {
                    visited.add(i);
                    result.add(i);
                    dfs1(matrix, i, result, visited);
                }
            }
        }
    }

    /**
     *  非递归方式实现dfs
     * @param matrix
     * @param node
     */
    public List<Integer> dfs2(int[][] matrix, int node) {
        Deque<Integer> stack = new LinkedList<>();
        stack.addFirst(node);

        Set<Integer> visited = new HashSet<>();
        visited.add(node);

        List<Integer> result = new ArrayList<>();
        result.add(node);

        while (!stack.isEmpty()){
            int[] group = matrix[stack.pollFirst()];
            for (int i = 0; i < group.length; i++) {
                if (group[i] ==1){
                    if (!visited.contains(i)) {
                        result.add(i);
                        visited.add(i);
                        stack.addLast(i);
                    }
                }
            }
        }
        return result;
    }


    /**
     * bfs
     * @param matrix
     * @return
     */
    public List<Integer> bfs(int[][] matrix,int node){
        if (matrix.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(node);
        deque.addFirst(node);
        result.add(node);

        while (!deque.isEmpty()){
            int[] group = matrix[deque.pollFirst()];
            for (int i = 0; i < group.length; i++) {;
                if (group[i] ==1){
                    if (!visited.contains(i)) {
                        visited.add(i);
                        deque.addLast(i);
                        result.add(i);
                    }
                }
            }
        }
        return result;


    }
}
