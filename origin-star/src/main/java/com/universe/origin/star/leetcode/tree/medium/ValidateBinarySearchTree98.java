package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 * / \
 * 3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class ValidateBinarySearchTree98 {
    public static void main(String[] args) {

    }


    /**
     * 二叉搜索树的中序遍历是递增序列
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        recursion(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1)>=list.get(i)){
                return false;
            }
        }
        return true;

    }

    public void recursion(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        recursion(root.left, list);
        list.add(root.val);
        recursion(root.right, list);
    }


    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }

        int val = node.val;
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }

        if (!helper(node.right, val, upper)) {
            return false;
        }
        if (!helper(node.left, lower, val)) {
            return false;
        }
        return true;
    }


    /**
     * 没有考虑和父节点的上一级作比较  部分case通不过
     *
     * @param root
     * @return
     */
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean leftStatus = true;
        if (root.left != null) {
            if (root.left.val < root.val) {
                leftStatus = isValidBST1(root.left);
            } else {
                leftStatus = false;
            }
        }

        boolean rightStatus = true;

        if (root.right != null) {
            if (root.right.val > root.val) {
                rightStatus = isValidBST1(root.right);
            } else {
                rightStatus = false;
            }
        }
        return leftStatus && rightStatus;
    }

}
