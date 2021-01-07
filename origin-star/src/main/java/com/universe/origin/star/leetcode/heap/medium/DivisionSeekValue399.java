package com.universe.origin.star.leetcode.heap.medium;

import java.util.*;

/**
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 *
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 *
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 *
 *  
 *
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 * 示例 2：
 *
 * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * 输出：[3.75000,0.40000,5.00000,0.20000]
 * 示例 3：
 *
 * 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * 输出：[0.50000,2.00000,-1.00000,-1.00000]
 *
 */
public class DivisionSeekValue399 {
    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        List<String> group1 = new ArrayList<>();
        group1.add("a");
        group1.add("b");
        equations.add(group1);
        List<String> group2 = new ArrayList<>();
        group2.add("b");
        group2.add("c");
        equations.add(group2);
        List<String> group3 = new ArrayList<>();
        group3.add("bc");
        group3.add("cd");
        equations.add(group3);
        double[] values = new double[]{1.5,2.5,5.0};
        DivisionSeekValue399 divisionSeekValue399 = new DivisionSeekValue399();
        divisionSeekValue399.calcEquation(equations,values,null);
    }


        /**
         *
         * @param equations
         * @param values
         * @param queries
         * @return
         */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 先将表达式分组  值：kv
        List<Map<Double,List<String>>> group = new ArrayList<>();
        List<Set<String>> groupIndex = new ArrayList<>();

        int n = equations.size();
        for (int i = 0; i <n ; i++) {
            List<String> current = equations.get(i);
            int groupSize = groupIndex.size();
            boolean findStatus = false;
            //循环分组判定当前表达式是哪个分组的
            for (int j = 0; j < groupSize; j++) {
                Set<String> kvSet = groupIndex.get(j);
                //判断是否有关联的kv
                if (kvSet.contains(current.get(0)) || kvSet.contains(current.get(1))){
                    kvSet.add(current.get(0));
                    kvSet.add(current.get(1));
                    // 记录 分组位置j：分组
                    group.get(j).put(values[i],current);
                    findStatus = true;
                    break;
                }
            }
            // 如果没有找到分组则新启一个分组
            if (!findStatus){
                Map<Double,List<String>> currentGroup = new HashMap<>();
                Set<String> currentKV = new HashSet<>();
                currentGroup.put(values[i],current);
                currentKV.add(current.get(0));
                currentKV.add(current.get(1));
                group.add(currentGroup);
                groupIndex.add(currentKV);
            }
        }
        System.out.println("分组隔离");
        //构造价值列表
        List<Map<String,Double>> kvMap = new ArrayList<>();
        for (int i = 0; i < groupIndex.size(); i++) {
            Map<Double,List<String>> currentGroup = group.get(i);
            Map<String,Double> kvValue = new HashMap<>();
            kvMap.add(kvValue);
            //处理这组的价值映射
            for (Map.Entry<Double,List<String>> entry:currentGroup.entrySet()){
                String lStr = entry.getValue().get(0);
                String rStr = entry.getValue().get(0);
                if (kvValue.containsKey(lStr)){
                    // 以lStr进行扩充

                    continue;
                }
                if (kvMap.contains(rStr)){

                }
            }

        }
        return null;
    }
}
