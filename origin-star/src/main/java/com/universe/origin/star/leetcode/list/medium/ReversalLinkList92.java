package com.universe.origin.star.leetcode.list.medium;

import com.universe.origin.star.leetcode.list.ListNode;

/**
 * 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class ReversalLinkList92 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = 1;

        ListNode l2 = new ListNode();
        l2.val = 2;
        l1.next = l2;

//        ListNode l3 = new ListNode();
//        l3.val = 3;
//        l2.next = l3;
//
//        ListNode l4 = new ListNode();
//        l4.val = 4;
//        l3.next = l4;
//
//        ListNode l5 = new ListNode();
//        l5.val = 5;
//        l4.next = l5;
        ReversalLinkList92 reversalLinkList92 = new ReversalLinkList92();
        reversalLinkList92.reverseBetween(l1, 1, 2);
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) {
            return head;
        }
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode mid = head;
        ListNode next = head.next;
        ListNode left1 = new ListNode();
        left1.next = head;
        ListNode left2 = head;
        int index = 1;
        while (mid != null && pre != null) {
            // 不进行转换
            if (index < m) {
                // 前进
                pre = pre.next;
                mid = mid.next;
                next = next.next;
                index +=1;
                continue;
            }
            // 到达左边界 记录左边界的点left1 left2
            if (index == m) {
                left1 = pre;
                pre = pre.next;
                left2 = pre;
                mid = mid.next;
                next = next.next;
                index +=1;
                continue;
            }

            // 大于左边界 小于右边界 则交换
            if (index > m && index < n) {
                mid.next = pre;
                pre = mid;
                mid = next;
                if (next != null) {
                    next = next.next;
                }
                index +=1;
                continue;
            }

            //到达右边界 进行连接
            if (index == n) {
                mid.next = pre;
                //左边连接到尾部
                left1.next = mid;
                if (left2 != null) {
                    left2.next = next;
                }
                if (m==1){
                    return mid;
                }
                break;
            }

        }
        return head;
    }

}
