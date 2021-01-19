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
        rotateLinkList61.rotateRight(l1,2);
    }

    public ListNode rotateRight(ListNode head, int k) {
        ListNode index = head;
//        int count = 0;
//        ListNode change = head;
//        while (count < k && change != null) {
//            change = change.next;
//            count += 1;
//        }

        int score = 1;
        int temp = 0;
        while (score < k) {
            // index 和count开始交换
            while (index != null) {
                ListNode change = index.next;
                //记录下个节点的值
                temp = change.val;
                change.val = index.val;


                //向后移动
                index = index.next;
                if (change.next == null) {
                    change = head;
                } else {
                    change = change.next;
                }

            }
            score +=1;
        }
        return head;
    }
}
