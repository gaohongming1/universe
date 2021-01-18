package com.universe.origin.star.leetcode.list.medium;

import com.universe.origin.star.leetcode.list.ListNode;

/**
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 */
public class DeleteThNNode19 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = 1;

        ListNode l2 = new ListNode();
        l2.val = 2;
        l1.next = l2;

        ListNode l3 = new ListNode();
        l3.val = 3;
        l2.next = l3;

        DeleteThNNode19 deleteThNNode19 = new DeleteThNNode19();
        deleteThNNode19.removeNthFromEnd(l1,2);
    }

    /**
     * 使用快慢指针思想，中间相差N个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next==null && n==1){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode slowPre = head;
        int index = 0;
        while (fast!=null){
            index +=1;
            fast = fast.next;
            if (index>n){
                slowPre = slow;
                slow = slow.next;
            }
        }
        /**
         * 这里由于slow 和slowPre都是从head开启的所以需要特殊判断，如果在头结点之前添加哑节点，则不需要特殊判断了
         */
        if ((slow == head && n==2) || index==n){
            head = head.next;
            return head;
        }

        slowPre.next = slow.next;
        return head;
    }




}
