package com.universe.origin.star.leetcode.graph.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1162. 地图分析
 * 你现在手里有一份大小为 N x N 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的。
 * <p>
 * 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
 * <p>
 * 如果网格上只有陆地或者海洋，请返回 -1。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：[[1,0,1],[0,0,0],[1,0,1]]
 * 输出：2
 * 解释：
 * 海洋单元格 (1, 1) 和所有陆地单元格之间的距离都达到最大，最大距离为 2。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：[[1,0,0],[0,0,0],[0,0,0]]
 * 输出：4
 * 解释：
 * 海洋单元格 (2, 2) 和所有陆地单元格之间的距离都达到最大，最大距离为 4。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] 不是 0 就是 1
 */
public class MapAnalyse1162 {
    // 上下左右
    public int[] dx = new int[]{0, 0, -1, 1};
    public int[] dy = new int[]{1, -1, 0, 0};
    private int len;
    private int[][] matrix;


    /**
     * 采用bfs
     *
     * @param grid
     * @return
     */
    public int maxDistance(int[][] grid) {
        this.len = grid.length;
        this.matrix = grid;
        int ans = -1;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j] == 0) {
                    ans = Math.max(ans, findMaxLen(i, j));
                }
            }
        }
        return ans;
    }

    /**
     * bfs找到i j 的最大值
     *
     * @param x
     * @param y
     * @return
     */
    public int findMaxLen(int x, int y) {
        boolean[][] vis = new boolean[len][len];
        // 记录各个点横坐标、纵坐标、以及点位
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, 0});
        vis[x][y] = true;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int dx = node[0] + this.dx[i];
                int dy = node[1] + this.dy[i];
                // 判断是不是在区域内
                if (dx < 0 || dy < 0 || dx >= len || dy >= len) {
                    continue;
                }
                if (!vis[dx][dy]) {
                    queue.offer(new int[]{dx,dy,node[2]+1});
                    vis[dx][dy] = true;
                    // 这里碰到的第一个陆地就是最近的
                    if (matrix[dx][dy] ==1){
                        return node[2]+1;
                    }
                }

            }
        }
        return -1;
    }
}
