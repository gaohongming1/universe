package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

/**
 *1315. 祖父节点值为偶数的节点和
 * 给你一棵二叉树，请你返回满足以下条件的所有节点的值之和：
 *
 * 该节点的祖父节点的值为偶数。（一个节点的祖父节点是指该节点的父节点的父节点。）
 * 如果不存在祖父节点值为偶数的节点，那么返回 0 。
 *
 *
 *
 * 示例：
 *
 *
 *
 * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * 输出：18
 * 解释：图中红色节点的祖父节点的值为偶数，蓝色节点为这些红色节点的祖父节点。
 *
 *
 * 提示：
 *
 * 树中节点的数目在 1 到 10^4 之间。
 * 每个节点的值在 1 到 100 之间。
 */
public class ParentSumIsEven1315 {
    public Integer sum =0;

    public int sumEvenGrandparent(TreeNode root) {
        if (root == null) {
            return 0;
        }
        recursion(root);
        return sum;

    }


    /**
     *
     *
     */
    public void recursion(TreeNode node) {

        if (node == null){
            return;
        }

        boolean isEven = node.val % 2 == 0;
        TreeNode left = node.left;
        TreeNode right = node.right;

        if (isEven){
            if (node.left!=null){
                if (left.left!=null){
                    sum += left.left.val;
                }
                if (left.right != null) {
                    sum += left.right.val;
                }
            }
            if (node.right!=null){
                if (right.left!=null){
                    sum += right.left.val;
                }
                if (right.right != null) {
                    sum += right.right.val;
                }
            }
        }
        recursion(left);
        recursion(right);
    }

}
