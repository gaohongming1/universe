package com.universe.origin.star.leetcode.heap.medium;

import java.util.*;

/**
 * @author gaohongming
 * @version 1.0.0
 * @ClassName TheCloseOriginK937.java
 * @Description TODO
 * @createTime 2021年01月05日 21:28:00
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * <p>
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * <p>
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 示例 2：
 * <p>
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
public class TheCloseOriginK937 {
    public static void main(String[] args) {

    }


    public int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.len-o1.len;
            }
        });

        int len1 = Math.abs(points[0][0]) * Math.abs(points[0][0]) + Math.abs(points[0][1]) * Math.abs(points[0][1]);
        Node node = new Node(0,points[0],len1);
        priorityQueue.add(node);

        /**
         * 维护大小为K的大顶堆  当当前判断元素大于等于大顶堆的时候则继续  否则则将大顶堆堆顶元素和结果集移除 将当前元素加入 遍历结束结果集就是最终结果
         */
        for (int i = 1; i < n; i++) {
            // 计算长度
            int len = Math.abs(points[i][0]) * Math.abs(points[i][0]) + Math.abs(points[i][1]) * Math.abs(points[i][1]);
            Node temp = new Node(i,points[i],len);

            if (priorityQueue.size() < K){
                priorityQueue.add(temp);
                continue;
            }

            //当前点距离比大顶堆顶点大则直接舍去
            if (priorityQueue.element().len <= len){
                continue;
            }else {
                //小顶堆大于当前的
                priorityQueue.poll();
                priorityQueue.add(temp);

            }
        }

        int[][]result = new int[priorityQueue.size()][2];
        int i = 0;
        while (priorityQueue.size()!=0){
            result[i++] = priorityQueue.poll().arr;
        }
        return result;
    }

    class Node{
        private int index;
        private int[] arr;
        private int len;

        public Node(int index, int[] arr, int len) {
            this.index = index;
            this.arr = arr;
            this.len = len;
        }
    }


}
