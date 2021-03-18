package com.universe.origin.star.leetcode.graph.base;

/**
 * 匈牙利算法
 *
 */
public class Hungary {
    // 分别代表左边元素的数量和右边元素的数量
    int M,N;

    // 记录最大的元素数量
    int MAX;

    // 记录当前右侧元素被访问的情况
    boolean[] visited = new boolean[MAX];

    // 记录当前右侧元素对应的左侧元素
    int[] p = new int[N];

    int[][] matrix = new int[MAX][MAX];

    public void calc(){
        int count = 0;
        for (int i = 0; i < M; i++) {
            if (match(i)) {
                count+=1;
            }
        }
        System.out.println("最大边数量" + count);

    }


    /**
     *
     * @param i
     * @return
     */
    public boolean match(int i) {
        for (int j = 0; j < N; j++) {
            // 如果当前有边并且未被访问过
            if (matrix[i][i] != -1 && !visited[j]) {
                visited[j] = true;
                // 如果当前右侧元素还未和左侧元素相连接  或者相连接的左侧元素能找到除了当前右侧元素其他的连接点
                if (p[j] == 0 || match(p[j])) {
                    return true;
                }
            }
        }
        return false;

    }
}
