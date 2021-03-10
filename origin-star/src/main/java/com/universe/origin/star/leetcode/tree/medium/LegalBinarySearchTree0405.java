package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 04.05. 合法二叉搜索树
 * 实现一个函数，检查一棵二叉树是否为二叉搜索树。
 * <p>
 * 示例 1:
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * 输入:
 * 5
 * / \
 * 1   4
 * / \
 * 3   6
 * <p>
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class LegalBinarySearchTree0405 {
    public static void main(String[] args) {

    }


    /**
     * 二叉搜索树的中序遍历是递增序列
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return recursion(root,new ArrayList<>());
    }


    public boolean recursion(TreeNode node, List<Integer> list) {
        if (node == null) {
            return true;
        }

        boolean left = recursion(node.left,list);
        if (!left) {
            return left;
        }
        // 和list的最后一个元素比较，如果是小于最后一个则不对

        if (list.size() != 0) {
            if (list.get(list.size() - 1) >= node.val) {
                return false;
            }
        }
        list.add(node.val);

        boolean right = recursion(node.right,list);
        if (!right) {
            return right;
        }
        return true;
    }

    /**
     * @param node 递归调用只会考虑父子情况  部分情况下不对
     * @return
     */
    public boolean recursion2(TreeNode node) {
        if (node == null) {
            return true;
        }

        boolean left = true;
        if (node.left != null) {
            left = node.val > node.left.val && recursion2(node.left);
        }

        boolean right = true;
        if (node.right != null) {
            right = node.val < node.right.val && recursion2(node.right);
        }
        return left && right;
    }
}
