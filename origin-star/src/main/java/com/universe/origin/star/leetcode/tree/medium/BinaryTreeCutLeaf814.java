package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

/**
 * 814. 二叉树剪枝
 * 给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。
 * <p>
 * 返回移除了所有不包含 1 的子树的原二叉树。
 * <p>
 * ( 节点 X 的子树为 X 本身，以及所有 X 的后代。)
 * <p>
 * 示例1:
 * 输入: [1,null,0,0,1]
 * 输出: [1,null,0,null,1]
 * <p>
 * 解释:
 * 只有红色节点满足条件“所有不包含 1 的子树”。
 * 右图为返回的答案。
 * <p>
 * <p>
 * 示例2:
 * 输入: [1,0,1,0,0,0,1]
 * 输出: [1,null,1,null,1]
 * <p>
 * <p>
 * <p>
 * 示例3:
 * 输入: [1,1,0,1,1,0,1,0]
 * 输出: [1,1,0,1,1,null,1]
 * <p>
 * <p>
 * <p>
 * 说明:
 * <p>
 * 给定的二叉树最多有 100 个节点。
 * 每个节点的值只会为 0 或 1 。
 */
public class BinaryTreeCutLeaf814 {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }


        boolean status = recursion(root);
        if (!status) {
            return null;
        }
        return root;

    }

    public Boolean recursion(TreeNode node) {
        if (node == null) {
            return false;

        }

        if (node.left == null && node.right == null) {
            if (node.val == 0) {
                return false;
            } else {
                return true;
            }
        }

        Boolean left = true;
        boolean right = true;

        left = recursion(node.left);
        // 断开左节点
        if (!left) {
            node.left = null;
        }
        right = recursion(node.right);
        if (!right) {
            node.right = null;
        }


        if (!left && !right && node.val == 0) {
            return false;
        } else {
            return true;
        }

    }
}
