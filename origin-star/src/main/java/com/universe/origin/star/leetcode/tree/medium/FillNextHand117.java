package com.universe.origin.star.leetcode.tree.medium;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 */
public class FillNextHand117 {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        node4.left = node7;
        node6.right = node8;
        FillNextHand117 fillNextHand117 = new FillNextHand117();
        fillNextHand117.connect(node1);


    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node index = root;
        index.next = null;

        Node nextFlowIndex = index;
        if (root.left != null) {
            nextFlowIndex = index.left;
        } else {
            if (root.right != null) {
                nextFlowIndex = index.right;
            }
        }
        while (index != null) {
            Node tempNext = index.next;
            // 如果父节点的下个节点是叶子节点则继续向右
            while (tempNext!=null && tempNext.left==null && tempNext.right==null){
                tempNext = tempNext.next;
            }
            Node tempNextFirstChild = null;
            if (tempNext!=null){
                if (tempNext.left!=null){
                    tempNextFirstChild = tempNext.left;
                }else {
                    tempNextFirstChild = tempNext.right;
                }
            }

            // 左孩子
            if (index.left != null) {
                // 右孩子不为空
                if (index.right != null) {
                    index.left.next = index.right;
                } else {
                    // index left tempNext的tempNextFirstChild 节点上
                    index.left.next = tempNextFirstChild;
                }
            }

            // 右孩子
            if (index.right !=null){
                index.right.next = tempNextFirstChild;
            }

            // 游标处理
            if (index.next!=null){
                index = index.next;
            }else {
                index = nextFlowIndex;
                if (index!=null){
                    nextFlowIndex = index.left;
                }
            }
            // 处理nextFlowIndex 为空的情况
            if (nextFlowIndex == null && index!=null){
                if (index.left==null){
                    nextFlowIndex = index.right;
                }else {
                    nextFlowIndex = index.left;
                }
            }
        }
        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }


    }
}
