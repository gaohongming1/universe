package com.universe.origin.star.leetcode.tree.base;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.*;

public class BinaryTreeBase {


    /**
     * 递归方式实现dfs
     * dfs 本质就是前序遍历
     */
    public void dfs(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        dfs(node.left, result);
        dfs(node.right, result);
    }

    /**
     * 非递归方式实现dfs
     * 前序遍历
     */
    public List<Integer> dfs2(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(node);
        List<Integer> result = new ArrayList<>();
        result.add(node.val);
        while (!stack.isEmpty()) {
            TreeNode snode = stack.pop();
            result.add(snode.val);
            if (snode.right != null) {
                stack.add(snode.right);
            }
            if (snode.left != null) {
                stack.add(snode.left);
            }
        }
        return result;
    }

    /**
     * 非递归方式实现bfs
     * 层序遍历
     */
    public List<Integer> bfs2(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(node);
        List<Integer> result = new ArrayList<>();
        result.add(node.val);

        while (!deque.isEmpty()) {
            TreeNode node1 = deque.pollFirst();
            if (node1.left != null) {
                deque.addLast(node1.left);
            }
            if (node1.right != null) {
                deque.addLast(node1.right);
            }
            result.add(node1.val);
        }
        return result;
    }

    /**
     * 递归 中序遍历
     *
     * @param node
     * @param result
     */
    public void inOrder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inOrder(node.left, result);
        result.add(node.val);
        inOrder(node.right, result);
    }

    /**
     * 非递归方式实现中序遍历
     *
     * @param node
     * @return
     */
    public List<Integer> inOrder(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = node;
        List<Integer> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            // 先将左子树全部加入到栈中
            while (curr != null) {
                stack.add(curr);
                curr = curr.left;
            }

            TreeNode node1 = stack.pop();
            // 访问右子树
            curr = node1.right;
            result.add(curr.val);
        }
        return result;

    }

    /**
     * 递归方式后续遍历
     *
     * @param node
     * @param result
     */
    public void afterOrder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        afterOrder(node.left, result);
        afterOrder(node.right, result);
        result.add(node.val);
    }

    /**
     * 非递归方式后续遍历
     *
     * @param node
     * @return
     */
    public List<Integer> afterOrder(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = node;
        List<Integer> result = new ArrayList<>();
        TreeNode prev = curr;

        while (!stack.isEmpty()) {
            // 先将左子树全部加入到栈中
            while (curr != null) {
                stack.add(curr);
                curr = curr.left;
            }
            // 先查看当前待访问节点是不是父节点
            curr = stack.peek();
            // 这里curr.right != prev  的意思是当栈顶的节点有右子树，会将右子树入栈，由子树出栈完后   会再次访问这个节点为了避免再次访问右子树
            if (curr.right == null || curr.right != prev) {
                result.add(curr.val);
                stack.pop();
                prev = curr;
                curr = null;
            } else {
                curr = curr.right;
            }
        }
        return result;
    }

}
