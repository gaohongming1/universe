package com.universe.origin.star.leetcode.link.easy;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author gaohongming
 * @version 1.0.0
 * @ClassName SplitLinkList86.java
 * @Description TODO
 * @createTime 2021年01月03日 21:52:00
 * 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 * <p>
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：head = 1->4->3->2->5->2, x = 3
 * 输出：1->2->2->4->3->5
 */
public class SplitLinkList86 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(4);
        head.next = head2;
        ListNode head3 = new ListNode(3);
        head2.next = head3;
        ListNode head4 = new ListNode(2);
        head3.next = head4;
        ListNode head5 = new ListNode(5);
        head4.next = head5;
        ListNode head6 = new ListNode(2);
        head5.next = head6;
        SplitLinkList86 splitLinkList86 = new SplitLinkList86();
        splitLinkList86.partition(head,3);


    }

    public ListNode partition(ListNode head, int x) {
        if ( head == null|| head.next == null){
            return head;
        }

        // 用双端队列
        Deque<Integer> dequeL = new LinkedList();
        Deque<Integer> dequeR = new LinkedList();

        ListNode temp = head;

        while (temp!=null){
            if (temp.val<x){
                dequeL.addFirst(temp.val);
            }else {
                dequeR.addFirst(temp.val);
            }
            temp =temp.next;
        }

        // L出队
        ListNode index = head;
        while (!dequeL.isEmpty()){
            index.val = dequeL.pollLast();
            index = index.next;
        }

        // R出队
        while (!dequeR.isEmpty()){
            index.val = dequeR.pollLast();
            index = index.next;
        }
        return head;
    }

   static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
