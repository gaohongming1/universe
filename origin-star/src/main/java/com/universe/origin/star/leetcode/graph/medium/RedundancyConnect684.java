package com.universe.origin.star.leetcode.graph.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 在本问题中, 树指的是一个连通且无环的无向图。
 * <p>
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，
 * 这条附加的边不属于树中已存在的边。
 * *
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
 * <p>
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
 * <p>
 * 示例 1：
 * <p>
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的无向图为:
 * 1
 * / \
 * 2 - 3
 * 示例 2：
 * <p>
 * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 解释: 给定的无向图为:
 * 5 - 1 - 2
 * |   |
 * 4 - 3
 * 注意:
 * <p>
 * 输入的二维数组大小在 3 到 1000。
 * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
 * 更新(2017-09-26):
 * 我们已经重新检查了问题描述及测试用例，明确图是无向 图。对于有向图详见冗余连接II。对于造成任何不便，我们深感歉意
 * <p>
 * 并查集问题
 */
public class RedundancyConnect684 {
    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {1, 2},
                {1, 3},
                {2, 3}
        };
        RedundancyConnect684 redundancyConnect684 = new RedundancyConnect684();

        redundancyConnect684.findRedundantConnection(nums);
    }

    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[edges.length+1];
        for (int i = 1; i <= edges.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int node1 = edge[0];
            int node2 = edge[1];
            if (find(parent,node1)!=find(parent,node2)){
                union(parent,node1,node2);
            }else {
                return edge;
            }
        }
        return new int[0];
    }

    /**
     * node1节点 挂在node2节点的父节点下
     *
     * @param parent
     * @param node1
     * @param node2
     */
    public void union(int[] parent, int node1, int node2) {
        parent[find(parent, node1)] = find(parent, node2);
    }

    /**
     * 查找父节点
     *
     * @param parent
     * @param index
     * @return
     */
    public int find(int[] parent, int index) {
        // 这里同时进行了路径压缩
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }


    /**
     * 对于多的一条边 u v 肯定存在v的数量等于2
     * <p>
     * [[1,3],[3,4],[1,5],[3,5],[2,3]]
     * [[1,4],[3,4],[1,3],[1,2],[4,5]]
     *
     * @param edges
     * @return
     */
    public int[] findRedundantConnection2(int[][] edges) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        Integer v = Integer.MIN_VALUE;
        for (int i = 0; i < edges.length; i++) {

            // 记录u v
            Integer u = edges[i][0];
            Integer sv = edges[i][1];
            count.put(u, count.getOrDefault(u, 0) + 1);
            count.put(sv, count.getOrDefault(sv, 0) + 1);

            if (map.containsKey(edges[i][1]) && v == Integer.MIN_VALUE) {
                v = edges[i][1];
            }
            map.put(edges[i][1], 1);
        }

        /**
         * 倒叙找到可断开的点
         * u的边数量大于2
         */
        for (int i = edges.length - 1; i >= 0; i--) {
            Integer u = edges[i][0];
            Integer sv = edges[i][1];
            if (!sv.equals(v)) {
                continue;
            } else {
                if (count.get(u) < 2) {
                    continue;
                } else {
                    return edges[i];
                }
            }
        }
        return null;
    }
}
