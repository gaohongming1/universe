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
 * <p>
 * 103 二叉树的锯齿形遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 *173. 二叉搜索树迭代器
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 *
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 *
 *
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
                // 左孩子依次入栈
                stack1.add(root);
                root = root.left;
            }

            //左孩子加入完毕之后开始弹
            root = stack1.pop();
            list.add(root.val);
            // 遍历右节点的孩子
            root = root.right;

        }
        return list;
    }


    /**
     * 二叉树的锯齿形遍历
     * 使用两个栈
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        if (Objects.isNull(root)) {
            return list;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        // 表示1左节点先加入还是2右节点加入
        int model = 1;
        stack1.add(root);

        for (; ; ) {
            // stack1 清空数据输出
            List<Integer> list1 = new ArrayList<>();
            while (!stack1.isEmpty()) {
                TreeNode node = stack1.pop();
                list1.add(node.val);

                if (model == 1) {
                    if (node.left != null) {
                        stack2.add(node.left);
                    }
                    if (node.right != null) {
                        stack2.add(node.right);
                    }
                } else {
                    if (node.right != null) {
                        stack2.add(node.right);
                    }
                    if (node.left != null) {
                        stack2.add(node.left);
                    }
                }
            }
            list.add(list1);

            //再把stack2 清空进行下次插入
            if (!stack2.isEmpty()) {
                stack1 = stack2;
                stack2 = new Stack<>();
                if (model == 1) {
                    model = 2;
                } else {
                    model = 1;
                }
            } else {
                break;
            }
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
