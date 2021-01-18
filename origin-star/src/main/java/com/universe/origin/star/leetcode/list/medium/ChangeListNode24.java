package com.universe.origin.star.leetcode.list.medium;

import com.universe.origin.star.leetcode.list.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：[1]
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 *
 * 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
 */
public class ChangeListNode24 {
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
        ChangeListNode24 changeListNode24 = new ChangeListNode24();
        changeListNode24.swapPairs(l1);
    }


    public ListNode swapPairs(ListNode head) {
        if (head==null || head.next==null){
            return head;
        }

        //在头部加入虚拟节点
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode mid = head;
        ListNode next = head.next;
        ListNode re = null;
        while (next!=null){
            // 交换mid 和next
            mid.next = next.next;
            next.next = mid;
            pre.next = next;
            if (re==null){
                re = pre;
            }

            //位置恢复
            ListNode temp = mid;
            mid = next;
            next = temp;

            //向后移动两位
            pre = pre.next.next;
            mid = mid.next.next;
            next = next.next;
            if (next!=null){
                next = next.next;
            }
        }
        return re.next;
    }

}
