package com.universe.origin.star.leetcode.list.medium;

import com.universe.origin.star.leetcode.list.ListNode;

/**
 * 82. 删除排序链表中的重复元素 II
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class DeleteLinkNodeRepeat82 {
    public static void main(String[] args) {

    }

    /**
     * 需要使用三个指针
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode current = head;
        ListNode next = head.next;
        ListNode newHead = null;
        int lastDelVal = Integer.MAX_VALUE;
        while (current != null) {
            // 判断当前节点和next的值是否相等,相等则删除
            if (next!=null && current.val == next.val) {
                lastDelVal = current.val;
                pre.next = next.next;
                current = next.next;
                if (next.next != null) {
                    next = next.next.next;
                } else {
                    next = null;
                }
                continue;
            }

            // 当前节点和上个删除的元素值一样则删除current 节点
            if (current.val == lastDelVal) {
                pre.next = current.next;
                current = current.next;
                if (next!=null){
                    next = next.next;
                }
                continue;
            }

            if (newHead == null) {
                newHead = current;
            }
            pre = pre.next;
            current = current.next;
            if (next!=null){
                next = next.next;
            }
        }
        return newHead;
    }

}
