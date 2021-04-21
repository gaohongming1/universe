package com.universe.origin.star.leetcode.graph.medium;

import java.util.*;

/**
 * 399. 除法求值
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * <p>
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * <p>
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 * <p>
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 * 示例 2：
 * <p>
 * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * 输出：[3.75000,0.40000,5.00000,0.20000]
 * 示例 3：
 * <p>
 * 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * 输出：[0.50000,2.00000,-1.00000,-1.00000]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 */
public class DivisionEvaluation399 {
    public static void main(String[] args) {

    }

    /**
     * 并查集思想 查找根节点
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 将字母映射成1234方便操作
        Map<String, Integer> map = new HashMap<>();
        int d = 0;
        Calc calc = new Calc(2 * equations.size());
        double[] result = new double[queries.size()];

        for (int i = 0; i < equations.size(); i++) {
            List<String> curr = equations.get(i);
            if (!map.containsKey(curr.get(0))) {
                map.put(curr.get(0), d);
                d++;
            }
            if (!map.containsKey(curr.get(1))) {
                map.put(curr.get(1), d);
                d++;
            }
            calc.union(map.get(curr.get(0)), map.get(curr.get(1)), values[i]);
        }

        // 开始计算
        for (int i = 0; i < queries.size(); i++) {
            List<String> curr = queries.get(i);
            Integer id1 = map.get(curr.get(0));
            Integer id2 = map.get(curr.get(1));
            if (id1 == null || id2 == null) {
                result[i] = -1.0d;
            } else {
                result[i] = calc.connect(id1, id2);
            }
        }
        return result;
    }

    class Calc {
        private int[] parent;
        private double[] weight;

        public Calc(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        /**
         * 合并
         *
         * @param x
         * @param y
         */
        public void union(int x, int y, double value) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx == rooty) {
                return;
            }
            parent[rootx] = rooty;
            weight[rootx] = weight[y] * value / weight[x];
        }

        public int find(int x) {
            if (x != parent[x]) {
                int origin = parent[x];
                // 路径压缩
                parent[x] = find(parent[x]);
                /**
                 * 举例子 a->b->c   对应  weight[a]  weight[b]
                 * 其中换成  a->c
                 *          b->c  结构则weight[a] = weight[a] * weight[b]
                 */
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        /**
         * 计算x /y
         *
         * @param x
         * @param y
         * @return
         */
        public double connect(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx == rooty) {
                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }
    }


}
