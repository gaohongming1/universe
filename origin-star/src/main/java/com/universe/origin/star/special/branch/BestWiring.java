package com.universe.origin.star.special.branch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 最优布线问题
 * 二维矩阵设置为各个点能否走
 */
public class BestWiring {

    public static void main(String[] args) {
        int[][] adjacencyMatrix = new int[][]{
                {0, 0, 0, 0, 0, -1},
                {0, 0, -1, 0, 0, 0},
                {0, 0, 0, -1, -1, 0},
                {0, -1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}};

    }

    public Integer calc(int x, int y, int[][] adjacencyMatrix,int targetX, int targetY) {

        int lengthX = adjacencyMatrix.length;
        int lengthY = adjacencyMatrix[0].length;
        //初始化方向Node
        Node[] dir = new Node[4];
        // 初始化上下左右顺序四个方向
        dir[0].x = -1;
        dir[0].y = 0;
        dir[1].x = 1;
        dir[1].y = 0;
        dir[2].x = 0;
        dir[2].y = -1;
        dir[3].x = 0;
        dir[4].y = 1;

        // 当前节点
        Node heir = new Node(x, y);
        Node next = new Node();
        Queue<Node> queue = new LinkedList<>();
        // 开始寻路
        for (; ; ) {
            //分别在四个方向上寻路，找到可走的点则加入队列中
            for (int i = 0; i < 4; i++) {
                next.x = heir.x + dir[i].x;
                next.y = heir.y + dir[i].y;

                //判断next点是否能走，能走则修改点的状态并加入队列
                if (next.x > 0 && next.x < lengthX && next.y > 0
                        && next.y < lengthY && adjacencyMatrix[next.x][next.y] == 0){
                    // 记录是第几个走到这个节点的
                    adjacencyMatrix[next.x][next.y] = adjacencyMatrix[heir.x][heir.y] + 1;
                    Node child = new Node(next.x,next.y);
                    queue.add(child);
                }

                //判断下个点是不是目标点 是的话返回到这个点的长度
                if (next.x == targetX && next.y == targetY){
                    return adjacencyMatrix[next.x][next.y];
                }

                // 队列中没有可走的点了代表无路可走
                if (queue.isEmpty()){
                    return -1;
                }

                //下个节点开始从当前节点的可走节点之中查找
                heir = queue.poll();
            }

        }

    }


    class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node() {
        }
    }


}
