package com.universe.origin.star.leetcode.list.medium;

import com.universe.origin.star.leetcode.list.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 *109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *  todo 二叉搜索树
 */
public class OrderLinkListTransformSearchTree109 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = -10;

        ListNode l2 = new ListNode();
        l2.val = -3;
        l1.next = l2;

        ListNode l3 = new ListNode();
        l3.val = 0;
        l2.next = l3;

        ListNode l4 = new ListNode();
        l4.val = 5;
        l3.next = l4;

        ListNode l5 = new ListNode();
        l5.val = 9;
        l4.next = l5;
        OrderLinkListTransformSearchTree109 orderLinkListTransformSearchTree109 = new OrderLinkListTransformSearchTree109();
        orderLinkListTransformSearchTree109.sortedListToBST(l1);


    }

    public TreeNode sortedListToBST(ListNode head) {
        // 先使用快慢指针到达链表中部[-10, -3, 0, 5, 9],
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode fast = pre;
        ListNode slow = pre;
        List list = new ArrayList();

        while (fast!=null){
            if (fast.next!=null){
                fast = fast.next.next;
            }else {
                fast = null;
            }
            slow = slow.next;
            list.add(slow);
        }

        ListNode middle = slow;
        ListNode rightIndex = middle.next;
        TreeNode treeNode = new TreeNode();
        treeNode.val = middle.val;

        // 挂左右节点
        while (rightIndex!=null){

        }





        return null;


    }

      public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
