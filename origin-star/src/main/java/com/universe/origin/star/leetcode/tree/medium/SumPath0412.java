package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 04.12. 求和路径
 * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。
 * 注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * 返回:
 * <p>
 * 3
 * 解释：和为 22 的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]
 * 提示：
 * <p>
 * 节点总数 <= 10000
 */
public class SumPath0412 {
    public int ans = 0;



    public static void main(String[] args) {

    }

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        dfs(map, root, sum, 0);
        return ans;
    }


    /**
     * map 记录当前路径存在的前缀和
     * @param map
     * @param node
     * @param sum
     */
    public void dfs(Map<Integer, Integer> map, TreeNode node, int sum,int currentSum){
        if (node == null) {
            return;
        }
        currentSum += node.val;
        if (map.containsKey(currentSum - sum)){
            ans += map.get(currentSum - sum);
        }

        map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);

        dfs(map, node.left, sum, currentSum);
        dfs(map, node.right, sum, currentSum);

        //当前节点回溯
        map.put(currentSum, map.get(currentSum) - 1);
    }
}
