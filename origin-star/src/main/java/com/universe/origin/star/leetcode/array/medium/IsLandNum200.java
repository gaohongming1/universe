package com.universe.origin.star.leetcode.array.medium;

public class IsLandNum200 {


    /**
     * 1 先找到所有的陆地并进行标记
     * 2 从陆地列表进行回溯  上下左右四个方向，碰到水则返回  走完这个陆地连接的所有的点则将岛屿值+1
     *
     * @param grid
     * @return
     */
    int x;
    int y;
    int[][]visited;

    public int numIslands(char[][] grid) {
        x = grid.length;
        y = grid[0].length;
        visited = new int[x][y];
        int isLand = 0;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (grid[i][j] == '0' || visited[i][j] == 1) {
                    continue;
                }
                dfs(grid, i, j);
                isLand++;
            }
        }
        return isLand;
    }

    public void dfs(char[][] grid, int x, int y) {
        if (x < 0 || x > this.x - 1 || y < 0 || y > this.y - 1) {
            return;
        }
        if (grid[x][y] == '0' || visited[x][y] == 1) {
            return;
        }
        visited[x][y] = 1;

        // 上下左右进行访问
        int upx = x - 1;
        int lefty = y - 1;
        int righty = y + 1;
        int downx = x + 1;
        dfs(grid, upx, y);
        dfs(grid, downx, y);
        dfs(grid, x, lefty);
        dfs(grid, x, righty);
    }
}
