package com.universe.origin.star.leetcode.list.easy;

import com.universe.origin.star.leetcode.list.ListNode;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 */
public class ReversalLinkList206 {
    private ListNode listNode;
    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = 1;

        ListNode l2 = new ListNode();
        l2.val = 2;
        l1.next = l2;

        ListNode l3 = new ListNode();
        l3.val = 3;
        l2.next = l3;

        ListNode l4 = new ListNode();
        l4.val = 4;
        l3.next = l4;

        ListNode l5 = new ListNode();
        l5.val = 5;
        l4.next = l5;
        ReversalLinkList206 reversalLinkList206 = new ReversalLinkList206();
        reversalLinkList206.reverseList(l1);
    }

    /**
     * 迭代
     * 记录三个节点
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next==null){
            return head;
        }
        ListNode pre =head;
        ListNode mid = head.next;

        ListNode next = head.next.next;
        head.next = null;
        while (mid!=null && pre!=null){
            // mid指向pre
            mid.next = pre;
            pre = mid;
            mid = next;
            if (next!=null){
                next = next.next;
            }
        }
        return pre;
    }

    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head==null || head.next==null){
            return head;
        }
        recursion(head);
        return listNode;
    }

    public ListNode recursion(ListNode head) {
        if (head.next==null){
            listNode = head;
            return listNode;
        }
        ListNode listNode = recursion(head.next);
        listNode.next = head;
        head.next=null;
        return head;
    }
}
