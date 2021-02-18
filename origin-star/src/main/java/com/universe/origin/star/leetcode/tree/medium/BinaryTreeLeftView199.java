package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.*;

/**
 * 199. 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
public class BinaryTreeLeftView199 {
    public static void main(String[] args) {

    }


    /**
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()){
            List<TreeNode> flow = new ArrayList<>();
            Deque<TreeNode> newDeque = new LinkedList<>();

            while (!deque.isEmpty()){
                TreeNode treeNode = deque.pop();
                flow.add(treeNode);
                if (treeNode.left!=null){
                    newDeque.addLast(treeNode.left);
                }
                if (treeNode.right!=null){
                    newDeque.addLast(treeNode.right);
                }
            }

            //找到当前层级的最右侧的节点拿到值
            result.add(flow.get(flow.size()-1).val);
            deque = newDeque;
        }
        return result;
    }


    /**
     * 解法错误，部分情况无法通过
     * @param root
     * @return
     */
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        TreeNode current = root;
        list.add(current.val);

        while (current.left!=null || current.right!=null){
            if (current.right!=null){
                current = current.right;
                list.add(current.val);
                continue;
            }

            if (current.left!=null){
                current = current.left;
                list.add(current.val);
                continue;
            }
        }
        return list;
    }
}
