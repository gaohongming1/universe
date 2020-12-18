package com.universe.origin.star.leetcode.stack.medium;

import java.util.*;

/**
 * 题目合集
 * <p>
 * 144
 * 二叉树的前序遍历
 * <p>
 * 145
 * 二叉树的后序遍历
 * <p>
 * 94
 * 二叉树的中序遍历
 */
public class BinaryTreeErgodic144 {
    public static void main(String[] args) {
        Deque<Character> stk = new LinkedList<Character>();
        stk.push('a');
        stk.push('b');
        stk.push('d');
        stk.push('k');
        System.out.println(stk.pop());
    }

    /**
     * 非递归方式实现前序遍历  根左右
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (Objects.isNull(root)) {
            return list;
        }
        Stack<TreeNode> treeNodes = new Stack<>();
        treeNodes.add(root);

        while (!treeNodes.isEmpty()) {
            TreeNode node = treeNodes.pop();
            list.add(node.val);

            if (Objects.nonNull(node.right)) {
                treeNodes.add(node.right);
            }
            if (Objects.nonNull(node.left)) {
                treeNodes.add(node.left);
            }
        }
        return list;
    }

    /**
     * 后续遍历非递归方式 左右根
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        if (Objects.isNull(root)) {
            return list;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            if (Objects.nonNull(node.left)) {
                stack1.add(node.left);
            }
            stack2.add(node);
            if (Objects.nonNull(node.right)) {
                stack1.add(node.right);
            }
        }

        while (!stack2.isEmpty()) {
            list.add(stack2.pop().val);
        }
        return list;
    }

    /**
     * 非递归中序遍历 左根右
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (Objects.isNull(root)) {
            return list;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        while (!stack1.isEmpty() || root != null) {
            // 将左孩子加入如栈
            while (root != null) {
                stack1.add(root.left);
                root = root.left;
            }
            //左孩子加入完毕之后开始弹
            root = stack1.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }



public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
}
