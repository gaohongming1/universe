package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

/**
 * 687. 最长同值路径
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * <p>
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * <p>
 * 5
 * / \
 * 4   5
 * / \   \
 * 1   1   5
 * 输出:
 * <p>
 * 2
 * 示例 2:
 * <p>
 * 输入:
 * <p>
 * 1
 * / \
 * 4   4
 * / \   \
 * 4   4   4
 * 输出:
 * <p>
 * 2
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 * <p>
 * 通过次数29,393提交次数68,766
 */
public class MaxSameValPath687 {
    public int sum;

    public int longestUnivaluePath(TreeNode root) {
        sum = 0;
        recursion(root);
        return sum;
    }

    public int recursion(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = recursion(node.left);
        int right = recursion(node.right);
        int arrLeft = 0;
        int arrRight = 0;

        if (node.left != null && node.val == node.left.val) {
            arrLeft += left + 1;
        }
        if (node.right != null && node.val == node.right.val) {
            arrRight += right + 1;
        }
        sum = Math.max(sum, arrLeft + arrRight);
        return Math.max(arrLeft, arrRight);
    }
}
