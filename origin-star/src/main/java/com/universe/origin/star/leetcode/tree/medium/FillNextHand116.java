package com.universe.origin.star.leetcode.tree.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
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
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，
 * 如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数量少于 4096
 * -1000 <= node.val <= 1000
 */
public class FillNextHand116 {
    public static void main(String[] args) {
        Node treeNode1 = new Node(1);
        Node treeNode2 = new Node(2);
        Node treeNode3 = new Node(3);
        Node treeNode4 = new Node(4);
        Node treeNode5 = new Node(5);
        Node treeNode6 = new Node(6);
        Node treeNode7 = new Node(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        FillNextHand116 fillNextHand116 = new FillNextHand116();
        fillNextHand116.connect2(treeNode1);
    }


    /**
     * 从叶子节点开始先构造同一父节点下的子节点的next指针
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if (root == null){
            return null;
        }
        Node temp = root;
        Node tempLeft = root.left;
        Node index = root;
        while (index!=null){
            // index 节点的左右相连
            if (index.left!=null){
                index.left.next = index.right;
            }
            if (index.right!=null){
                if (index.next!=null){
                    index.right.next = index.next.left;
                }else {
                    index.right.next = null;
                }
            }
            if (index.next!=null){
                index = index.next;
            }else {
                index = tempLeft;
                if (index!=null){
                    tempLeft = index.left;
                }else {
                    tempLeft = null;
                }
            }
        }
        return temp;
    }



    /**
     * 使用层序遍历
     * 空间复杂度是o(n)
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Deque<Node> flow = new LinkedList<>();
        flow.addFirst(root);
        while (!flow.isEmpty()) {
            Deque<Node> childFlow = new LinkedList<>();

            while (!flow.isEmpty()) {
                Node node = flow.pollFirst();
                Node next = null;
                if (!flow.isEmpty()) {
                    next = flow.peekFirst();
                }
                node.next = next;
                // 左右孩子加入
                if (node.left != null) {
                    childFlow.addLast(node.left);
                }
                if (node.right != null) {
                    childFlow.addLast(node.right);
                }
            }

            flow = childFlow;
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













