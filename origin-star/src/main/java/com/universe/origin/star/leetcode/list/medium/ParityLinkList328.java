package com.universe.origin.star.leetcode.list.medium;

import com.universe.origin.star.leetcode.list.ListNode;

/**
 * 328. 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 *
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 */
public class ParityLinkList328 {
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
        ParityLinkList328 parityLinkList328 = new ParityLinkList328();
        parityLinkList328.oddEvenList(l1);
    }

    /**
     * 遍历，两个指针
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head==null || head.next==null){
            return head;
        }

        ListNode odd = head;
        ListNode oddIndex = head;
        ListNode  even = head.next;
        ListNode  evenIndex = head.next;
        ListNode index = head.next.next;
        oddIndex.next = null;
        evenIndex.next = null;
        int count = 2;
        while (index!=null){
            count+=1;
            if (count%2==0){
                evenIndex.next = index;
                evenIndex = index;
            }else {
                oddIndex.next = index;
                oddIndex = index;
            }
            index = index.next;
        }
        evenIndex.next = null;
        oddIndex.next = null;
        oddIndex.next = even;
        return odd;
    }
}
