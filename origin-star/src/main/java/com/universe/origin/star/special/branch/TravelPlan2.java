package com.universe.origin.star.special.branch;


import java.util.*;

/**
 * 分支限界法解决旅行问题
 */
public class TravelPlan2 {
    public static void main(String[] args) {
        int[][] adjacencyMatrix = new int[][]{
                {-1, 3, -1, 8, 9},
                {3, -1, 3, 10, 5},
                {-1, 3, -1, 4, 3},
                {8, 10, 4, -1, 20},
                {9, 5, 3, 20, -1}};
        Scanner scanner = new Scanner(System.in);
        int originNode = scanner.nextInt();
        TravelPlan2 travelPlan = new TravelPlan2();
        int[] bestValueStatus = new int[adjacencyMatrix.length];
        travelPlan.calc(adjacencyMatrix, originNode - 1, bestValueStatus);
        System.out.println(Arrays.toString(bestValueStatus));
    }


    public void calc(int[][] adjacencyMatrix, int originNodeIndex, int[] bestValueStatus) {

        // 构造第一个节点的选择
        int length = adjacencyMatrix.length;
        int[] currentStatus = new int[length];
        currentStatus[originNodeIndex] = 1;
        int bestValue = Integer.MAX_VALUE;

        Node firstNode = new Node(originNodeIndex, currentStatus, 0, 1);
        Queue<Node> queue = new LinkedList<>();
        queue.add(firstNode);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            //走到终点并且最优值可以被替换
            if (node.visitIndex == length && node.currentBestValue + adjacencyMatrix[node.index][originNodeIndex] < bestValue) {
                bestValue = node.currentBestValue + adjacencyMatrix[node.index][originNodeIndex];
                for (int i = 0; i < length; i++) {
                    bestValueStatus[i] = node.currentStatus[i];
                }
            }

            // 遍历矩阵找到能走的节点选择，然后加入队列中
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                //如果这条路可以走则完善父节点信息同时加入到队列中
                if (isOk(adjacencyMatrix, node.currentStatus, node.index, i)) {
                    //构造子节点加入到队列中
                    int[] childCurrentStatus = Arrays.copyOf(node.currentStatus, length);
                    childCurrentStatus[i] = node.visitIndex + 1;
                    int childCurrentBestValue = node.currentBestValue + adjacencyMatrix[node.index][i];
                    Node childNode = new Node(i, childCurrentStatus, childCurrentBestValue, node.visitIndex + 1);
                    queue.add(childNode);
                }
            }
        }
        System.out.println(bestValue);
    }


    /**
     * 判断当前节点是否
     *
     * @param currentStatus
     * @param index
     * @return
     */
    public static boolean isOk(int[][] adjacencyMatrix, int[] currentStatus, int index, int targetIndex) {
        if (adjacencyMatrix[index][targetIndex] != -1 && currentStatus[targetIndex] == 0) {
            return true;
        }
        return false;
    }

    class Node {
        //当前节点的索引
        private Integer index;
        //当前各个节点的访问顺序
        private int[] currentStatus;
        // 当前节点对应的最优解
        private int currentBestValue;

        private int visitIndex;

        public Node(Integer index, int[] currentStatus, int currentBestValue, int visitIndex) {
            this.index = index;
            this.currentStatus = currentStatus;
            this.currentBestValue = currentBestValue;
            this.visitIndex = visitIndex;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public int[] getCurrentStatus() {
            return currentStatus;
        }

        public void setCurrentStatus(int[] currentStatus) {
            this.currentStatus = currentStatus;
        }

        public int getCurrentBestValue() {
            return currentBestValue;
        }

        public void setCurrentBestValue(int currentBestValue) {
            this.currentBestValue = currentBestValue;
        }
    }
}
