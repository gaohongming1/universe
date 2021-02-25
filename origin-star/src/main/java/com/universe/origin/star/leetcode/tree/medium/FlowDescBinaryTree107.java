package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 107. 二叉树的层序遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层序遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class FlowDescBinaryTree107 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null){
            return new ArrayList<>();
        }

        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        List<List<Integer>> result = new ArrayList<>();
        while (!deque.isEmpty()){
            Deque<TreeNode> nextFlow = new LinkedList<>();
            List<Integer> flowResult = new ArrayList<>();
            while (!deque.isEmpty()){
                TreeNode node = deque.pollFirst();
                flowResult.add(node.val);
                if (node.left!=null){
                    nextFlow.addLast(node.left);
                }

                if (node.right!=null){
                    nextFlow.addLast(node.right);
                }
            }
            deque = nextFlow;
            result.add(0,flowResult);

        }
        return result;
    }
}






















