package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
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
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 */
public class PathSum113 {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(-2);
        TreeNode treeNode3 = new TreeNode(-3);
        TreeNode treeNode4 = new TreeNode(1);
        TreeNode treeNode5 = new TreeNode(3);
        TreeNode treeNode6 = new TreeNode(-2);
        TreeNode treeNode7 = new TreeNode(-1);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode4.left = treeNode7;
        PathSum113 pathSum113 = new PathSum113();
        pathSum113.pathSum(treeNode1,2);

    }

    /**
     * dfs 加上剪枝函数
     * 或者广度优先搜索
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null){
            return new ArrayList<>();
        }
        int sum = root.val;
        List<List<Integer>> allResult = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(root.val);
        dfs(root,sum,targetSum,currentPath,allResult);
        return allResult;
    }


    public void dfs(TreeNode root, int sum, int targetSum, List<Integer> currentPath, List<List<Integer>> allResult) {

        // 判断是否到根节点
        if (root.right == null && root.left == null){
            if (sum == targetSum){
                List<Integer> re = new ArrayList<>(currentPath);
                allResult.add(re);
            }
            return;
        }

        // 左子树
        if (root.left != null) {
            sum += root.left.val;
            currentPath.add(currentPath.size(),root.left.val);
            dfs(root.left,sum,targetSum,currentPath,allResult);
            sum -= root.left.val;
            currentPath.remove(currentPath.size()-1);
        }

        if (root.right != null){
            sum +=root.right.val;
            currentPath.add(currentPath.size(),root.right.val);
            dfs(root.right,sum,targetSum,currentPath,allResult);
            sum -= root.right.val;
            currentPath.remove(currentPath.size()-1);
        }
    }
}
