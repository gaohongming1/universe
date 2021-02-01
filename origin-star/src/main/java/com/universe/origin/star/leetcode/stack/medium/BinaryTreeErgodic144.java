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
 * <p>
 * 102. 二叉树的层序遍历
 * <p>
 * 103 二叉树的锯齿形遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 173. 二叉搜索树迭代器
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * <p>
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
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
            // 弹出一个加入 子节点加入栈
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
     * 递归方式实现前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        return recursion1(root, result);
    }

    public List<Integer> recursion1(TreeNode root, List<Integer> list) {
        if (root == null) {
            return list;
        }

        list.add(root.val);
        recursion1(root.left, list);
        recursion1(root.right, list);
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
     * 后续遍历非递归解法2
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal22(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (Objects.isNull(root)) {
            return list;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode pre = null;
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                deque.push(root);
                root = root.left;
            }

            root = deque.pop();
            /**
             * 这里为什么要用pre？
             * 当出队加入结果的是右子节点，那么下一层出栈的是这个右子节点的父亲节点，就不会满足root.right == null
             * 但是右子树已经遍历完，所以这时候需要判断下右子树是否遍历完。也就是root.right == pre
             */
            if (root.right == null || root.right == pre) {
                list.add(root.val);
                pre = root;
                root = null;
            } else {
                deque.push(root);
                root = root.right;
            }

        }
        return list;
    }

    /**
     * 递归方式实现后续遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        return recursion2(root, result);
    }

    public List<Integer> recursion2(TreeNode root, List<Integer> list) {
        if (root == null) {
            return list;
        }
        recursion2(root.left, list);
        recursion2(root.right, list);
        list.add(root.val);
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
     * 中序遍历递归
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        return recursion3(root, result);
    }

    public List<Integer> recursion3(TreeNode root, List<Integer> list) {
        if (root == null) {
            return list;
        }
        recursion3(root.left, list);
        list.add(root.val);
        recursion3(root.right, list);
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


    /**
     * 层序遍历 非递归方式
     * 使用两个栈
     * BFS思想
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        if (Objects.isNull(root)) {
            return list;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(root);
        while (!stack1.isEmpty()) {
            List<Integer> currentResult = new ArrayList<>();
            while (!stack1.isEmpty()) {
                TreeNode current = stack1.pop();
                currentResult.add(current.val);
                if (current.left != null) {
                    stack2.push(current.left);
                }
                if (current.right != null) {
                    stack2.push(current.right);
                }
            }
            // stack2 转移到stack1 上
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            list.add(currentResult);
        }
        return list;
    }

    /**
     * 递归方式执行中序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder4(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        if (Objects.isNull(root)) {
            return list;
        }
        List<TreeNode> nextLayer = new ArrayList<>();
        nextLayer.add(root);
        List<List<Integer>> result = new ArrayList<>();
        recursion4(nextLayer,result);
        return result;
    }

    public void recursion4(List<TreeNode> list, List<List<Integer>> result) {
        if (list.isEmpty()){
            return;
        }
        List<TreeNode> nextLayer = new ArrayList<>();
        List<Integer> currentResult = new ArrayList<>();
        while (!list.isEmpty()) {
            TreeNode current = list.remove(0);

            if (current.left != null) {
                nextLayer.add(current.left);
            }
            if (current.right != null) {
                nextLayer.add(current.right);
            }
            currentResult.add(current.val);
        }
        result.add(currentResult);
        recursion4(nextLayer,result);
        return;
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
