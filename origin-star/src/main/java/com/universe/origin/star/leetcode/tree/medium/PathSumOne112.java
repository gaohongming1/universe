package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

/**
 * 112. 路径总和
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，
 * 这条路径上所有节点值相加等于目标和 targetSum 。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 */
public class PathSumOne112 {
    public static void main(String[] args) {

    }


    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root==null && targetSum==0){
            return false;
        }

        if (root==null && targetSum!=0){
            return false;
        }

        return dfs(root,targetSum,root.val);
    }

    public boolean dfs(TreeNode root, int targetSum, int currentSum) {

        if (root.left == null && root.right==null){
            if (targetSum == currentSum){
                return true;
            }else {
                return false;
            }
        }

        Boolean leftStatus = false;
        if (root.left != null) {
            currentSum += root.left.val;
            leftStatus = dfs(root.left, targetSum, currentSum);
            currentSum -= root.left.val;
        }

        Boolean rightStatus = false;
        if (root.right !=null){
            currentSum += root.right.val;
            rightStatus = dfs(root.right,targetSum,currentSum);
            currentSum -= root.right.val;
        }

        return leftStatus|| rightStatus;
    }
}
