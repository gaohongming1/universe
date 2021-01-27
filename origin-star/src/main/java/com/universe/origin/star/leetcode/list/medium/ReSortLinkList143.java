package com.universe.origin.star.leetcode.list.medium;

import com.universe.origin.star.leetcode.list.ListNode;

/**
 * 143. 重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 */
public class ReSortLinkList143 {
    private ListNode newHead;
    private ListNode newIndex;
    private ListNode positiveLoopIndex;
    private Integer len=1;
    private Integer count;
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
        ReSortLinkList143 reSortLinkList143 = new ReSortLinkList143();
        reSortLinkList143.reorderList(l1);

    }

    /**
     *采用递归的方式
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head==null || head.next==null){
            return;
        }
        newHead = head;
        newIndex = head;
        count = 1;
        positiveLoopIndex = head.next;

        // 找到链表长度进行中断
        ListNode temp = head;
        len = 1;
        while (temp.next!=null){
            temp = temp.next;
            len+=1;
        }
        build(head);
        head = newHead;
    }

    public void build(ListNode index){
        if (index.next!=null){
            build(index.next);
            if (count.equals(len)){
                return;
            }
            // 先挂正向遍历的
            newIndex.next = positiveLoopIndex;
            // 正向遍历后移
            positiveLoopIndex = positiveLoopIndex.next;
            count+=1;
            //相等则中断代表全部连接完毕
            if (count.equals(len)){
                newIndex = newIndex.next;
                newIndex.next = null;
                return;
            }
            // 指针后移
            newIndex = newIndex.next;

            // 再挂逆向遍历的
            newIndex.next = index;
            count+=1;
            if (count.equals(len)){
                newIndex = newIndex.next;
                newIndex.next = null;
                return;
            }
            //再后移
            newIndex = newIndex.next;

        }else {
            // 开始构造 头结点指向尾节点
            newIndex.next = index;
            newIndex = newIndex.next;
            count+=1;
        }
    }
}
