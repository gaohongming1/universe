package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.list.ListNode;
import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.*;

/**
 * 面试题 04.03. 特定深度节点链表
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：[1,2,3,4,5,null,7,8]
 * <p>
 * 1
 * /  \
 * 2    3
 * / \    \
 * 4   5    7
 * /
 * 8
 * <p>
 * 输出：[[1],[2,3],[4,5,7],[8]]
 */
public class SpecialDeepNodeList0403 {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode4.left = treeNode8;
        treeNode3.right = treeNode7;
        SpecialDeepNodeList0403 specialDeepNodeList0403 = new SpecialDeepNodeList0403();
        specialDeepNodeList0403.listOfDepth(treeNode1);
    }


    /**
     * 层序遍历
     *
     * @param tree
     * @return
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return null;
        }

        TreeNode index = tree;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(index);

        List<ListNode> allResult = new ArrayList<>();

        while (!deque.isEmpty()) {
            Deque<TreeNode> flow = new LinkedList<>();
            ListNode head = new ListNode(deque.peekFirst().val);
            ListNode tail = head;
            allResult.add(head);
            int temp = 1;
            while (!deque.isEmpty()) {
                TreeNode current = deque.pollFirst();

                if (temp!=1) {
                    ListNode listNode = new ListNode(current.val);
                    tail.next = listNode;
                    tail = tail.next;
                }
                temp +=1;
                if (current.left != null) {
                    flow.addLast(current.left);
                }

                if (current.right != null) {
                    flow.addLast(current.right);
                }
            }
            deque = flow;
        }
        return allResult.toArray(new ListNode[allResult.size()]);
    }
}
