package com.universe.origin.star.leetcode.list.easy;

import com.universe.origin.star.leetcode.list.ListNode;

/**
 * 83. 删除排序链表中的重复元素
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class DeleteLinkNodeRepeat83 {
    public static void main(String[] args) {

    }
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null || head.next==null){
            return head;
        }

        ListNode index = head;
        ListNode next = head.next;
        while (next!=null){
            // 比较index和next的值如果相等移除next
            if (index.val == next.val){
                index.next = next.next;
                next = next.next;
            }else {
                index = index.next;
                next = next.next;
            }
        }
        return head;
    }
}
