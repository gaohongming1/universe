package com.universe.origin.star.leetcode.list.medium;

import com.universe.origin.star.leetcode.list.ListNode;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * <p>
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 * 。
 */
public class RotateLinkList61 {
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
        RotateLinkList61 rotateLinkList61 = new RotateLinkList61();
        rotateLinkList61.rotateRight2(l1, 2);
    }


    /**
     * 使用数组记录
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int len = 0;
        ListNode node = head;
        while (node.next != null) {
            len += 1;
            node = node.next;
        }

        len+=1;

        // 计算K
        if (k > len) {
            k = k % len;
        }
        if (k==len){
            return head;
        }

        // 将链表连成环
        node.next = head;

        // 从头结点开始的K位置断开 k+1 作为新的头结点
        int count = 0;
        ListNode newHead = head;
        while (count < len-k-1 ) {
            newHead = newHead.next;
            count+=1;
        }
        ListNode result = newHead.next;
        newHead.next = null;
        return result;

    }


    /**
     * 每次移动一位 超过时间限制
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int score = 1;
        //缩减次数
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len += 1;
            node = node.next;
        }

        // 计算K
        if (k > len) {
            k = k % len;
        }

        while (score <= k) {
            // index 和count开始交换
            ListNode change = head.next;
            ListNode index = head;
            int be = head.val;
            int af = change.val;

            while (index != null) {
                //先记录下个节点的值
                af = change.val;
                // 再把前一个的值更新到下个节点上
                change.val = be;
                // 在更新前一个节点的值
                be = af;

                index = index.next;
                if (index != null && index.next != null) {
                    change = index.next;
                } else {
                    change = head;
                }
            }
            score += 1;
        }
        return head;
    }
}
