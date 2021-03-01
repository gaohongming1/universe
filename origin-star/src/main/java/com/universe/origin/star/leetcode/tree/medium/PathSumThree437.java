package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.*;

/**
 * 437. 路径总和 III
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * <p>
 * 找出路径和等于给定数值的路径总数。
 * <p>
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 * <p>
 * 示例：
 * <p>
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * <p>
 * 10
 * /  \
 * 5   -3
 * / \    \
 * 3   2   11
 * / \   \
 * 3  -2   1
 * <p>
 * 返回 3。和等于 8 的路径有:
 * <p>
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 */
public class PathSumThree437 {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(6);
        TreeNode treeNode4 = new TreeNode(2);
        TreeNode treeNode5 = new TreeNode(7);
        TreeNode treeNode6 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(1);
        TreeNode treeNode8 = new TreeNode(0);
        TreeNode treeNode9 = new TreeNode(8);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode7;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;
        treeNode4.left = treeNode5;
        treeNode4.right = treeNode6;
        treeNode7.left = treeNode8;
        treeNode7.right = treeNode9;
        PathSumThree437 pathSumThree437 = new PathSumThree437();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(treeNode1.val);
        System.out.println("test");

    }

    /**
     * 使用前缀和  加上dfs 加上回溯的思路
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null){
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return dfs(root, map, sum,0);

    }
    public Integer dfs(TreeNode root, Map<Integer, Integer> map, Integer k, Integer currentSum) {

        //计算前缀和
        currentSum += root.val;
        int count = 0;

        if (map.containsKey(currentSum - k)) {
            count += map.get(currentSum - k);
        }
        map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);

        if (root.left != null) {
            count += dfs(root.left, map, k, currentSum);
        }

        if (root.right != null) {
            count += dfs(root.right, map, k, currentSum);
        }

        //将当前节点回溯
        map.put(currentSum, map.get(currentSum) - 1);
        return count;
    }


}
