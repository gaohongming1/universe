package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1367. 二叉树中的列表
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
 * <p>
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 * <p>
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 * 解释：树中蓝色的节点构成了与链表对应的子路径。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：false
 * 解释：二叉树中不存在一一对应链表的路径。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 二叉树和链表中的每个节点的值都满足 1 <= node.val <= 100 。
 * 链表包含的节点数目在 1 到 100 之间。
 * 二叉树包含的节点数目在 1 到 2500 之间。
 */
public class BinaryTreeList1367 {
    /**
     * head
     */
    public ListNode head = null;


    public static void main(String[] args) {
        ListNode listNode0 = new ListNode(1);
        ListNode listNode1 = new ListNode(10);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(6);
        ListNode listNode4 = new ListNode(8);
        listNode0.next = listNode1;
        //listNode1.next = listNode2;
//        listNode2.next = listNode3;
        //listNode3.next = listNode4;

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(1);

        TreeNode node5 = new TreeNode(9);
        node1.right = node2;
        node2.right = node4;
        node2.left = node3;
        node3.left = node5;



//        TreeNode node6 = new TreeNode(2);
//        TreeNode node7 = new TreeNode(6);
//
//        TreeNode node8 = new TreeNode(8);
//        TreeNode node9 = new TreeNode(1);
//        TreeNode node10 = new TreeNode(3);
//        node1.left = node2;
//        node2.right = node3;
//        node3.left = node4;
//
//        node1.right = node5;
//        node5.left = node6;
//        node6.left = node7;
//        node6.right = node8;
//        node8.left = node9;
//        node8.right = node10;

        BinaryTreeList1367 binaryTreeList1367 = new BinaryTreeList1367();
        binaryTreeList1367.isSubPath2(listNode0, node1);


    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        list.add(root);

        return dfs(head, root, list);
    }


    public boolean isSubPath2(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }
            return dfs2(head, root) || isSubPath2(head,root.left) || isSubPath2(head,root.right);
    }


    public boolean dfs2(ListNode head, TreeNode root) {
        // head为null代表全部匹配
        if (head == null) {
            return true;
        }
        // 代表没有到达链表的终点则路径不对
        if (root == null) {
            return false;
        }
        if (head.val != root.val) {
            return false;
        }
        return dfs2(head.next, root.left) || dfs2(head.next, root.right);
    }


    /**
     * 方法一
     * 到子节点然后在进行遍历
     *
     * @param listIndex
     * @param node
     * @return
     */
    public boolean dfs(ListNode listIndex, TreeNode node, List<TreeNode> path) {

        if (node == null) {
            return false;
        }

        //到大叶子节点
        if (node.left == null && node.right == null) {
            // 循环路径判断
            for (int i = 0; i < path.size(); i++) {
                if (path.get(i).val == listIndex.val) {

                    // 开始判断是否包含这条路径
                    ListNode index = listIndex;
                    boolean find = true;
                    for (int j = i; j < path.size() && find && index != null; j++) {
                        if (path.get(j).val == index.val) {
                            index = index.next;
                        } else {
                            find = false;
                        }
                    }

                    // 找到一条路径 index 必定是null
                    if (find && index == null) {
                        return true;
                    }
                    // 开始下个节点的判断
                    index = listIndex;
                }
            }
        }

        boolean left = false;
        if (node.left != null) {
            path.add(node.left);
            left = dfs(listIndex, node.left, path);
            path.remove(path.size() - 1);
        }
        boolean right = false;
        if (node.right != null) {
            path.add(node.right);
            right = dfs(listIndex, node.right, path);
            path.remove(path.size() - 1);

        }
        return left || right;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
