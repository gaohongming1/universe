package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 * <p>
 * <p>
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
public class BinaryTreeSumToKOffer34 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null){
            return new ArrayList<>();
        }

        List<List<Integer>> allResult = new ArrayList<>();
        List<Integer> currentResult = new ArrayList<>();
        currentResult.add(root.val);
        dfs(root,root.val,sum,allResult,currentResult);
        return allResult;
    }

    public void dfs(TreeNode root, int currentSum, int sum, List<List<Integer>> allResult, List<Integer> currentResult) {
        if (root.left == null && root.right == null) {
            if (currentSum == sum) {
                // 记录路径
                allResult.add(new ArrayList<>(currentResult));
            }
        }

        if (root.left != null) {
            currentSum += root.left.val;
            currentResult.add(root.left.val);
            dfs(root.left, currentSum, sum, allResult, currentResult);
            currentSum -= root.left.val;
            Integer index = currentResult.remove(currentResult.size()-1);
        }

        if (root.right != null) {
            currentSum += root.right.val;
            currentResult.add(root.right.val);
            dfs(root.right, currentSum, sum, allResult, currentResult);
            currentSum -= root.right.val;
            Integer index = currentResult.remove(currentResult.size()-1);
        }
    }
}
