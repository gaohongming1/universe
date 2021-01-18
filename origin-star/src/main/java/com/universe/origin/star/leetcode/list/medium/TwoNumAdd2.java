package com.universe.origin.star.leetcode.list.medium;

import com.universe.origin.star.leetcode.list.ListNode;

/**
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 *
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 *
 * 提示：
 *
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 */
public class TwoNumAdd2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = 9;

        ListNode l2 = new ListNode();
        l2.val = 9;
        l1.next = l2;

        ListNode l3 = new ListNode();
        l3.val = 9;
        l2.next = l3;

        ListNode l4 = new ListNode();
        l4.val = 9;
        l3.next = l4;

        ListNode r1 = new ListNode();
        r1.val = 5;

        ListNode r2 = new ListNode();
        r2.val = 6;
        r1.next = r2;

        ListNode r3 = new ListNode();
        r3.val = 4;
        r2.next = r3;
        TwoNumAdd2 twoNumAdd2 = new TwoNumAdd2();
        twoNumAdd2.addTwoNumbers(l1,r1);



    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode current = head;
        int carry = 0;
        while (l1!=null || l2!=null){
            int l1Val = 0;
            if (l1!=null){
                l1Val = l1.val;
                l1 = l1.next;
            }
            int l2Val = 0;
            if (l2!=null){
                l2Val= l2.val;
                l2 = l2.next;
            }

            int sum = l1Val + l2Val + carry;
            carry = sum / 10;
            sum = sum % 10;
            ListNode temp = new ListNode();
            temp.val = sum;
            current.next = temp;
            current = current.next;
        }
        if (carry!=0){
            ListNode temp = new ListNode();
            temp.val = carry;
            current.next = temp;
        }
        return head.next;
    }


}
