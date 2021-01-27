package com.universe.origin.star.leetcode.list.medium;

import com.universe.origin.star.leetcode.list.ListNode;

/**
 * 147. 对链表进行插入排序
 * 对链表进行插入排序。
 * <p>
 * <p>
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 * <p>
 * <p>
 * <p>
 * 插入排序算法：
 * <p>
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class InsertSortLinkList147 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = -1;

        ListNode l2 = new ListNode();
        l2.val = 5;
        l1.next = l2;

        ListNode l3 = new ListNode();
        l3.val = 3;
        l2.next = l3;

        ListNode l4 = new ListNode();
        l4.val = 4;
        l3.next = l4;

        ListNode l5 = new ListNode();
        l5.val = 0;
        l4.next = l5;
        InsertSortLinkList147 insertSortLinkList147 = new InsertSortLinkList147();
        insertSortLinkList147.insertionSortList(l1);
    }

    /**
     * 1：可以遍历放到数组中，然后对数组进行插入排序
     * 2：使用指针进行移动判断
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode index = head.next;
        // 已排序区间只有头结点并且断开下个节点的连接
        ListNode temp = head;
        ListNode tempHead = temp;
        temp.next = null;
        while (index != null) {
            ListNode indexNext = index.next;

            if (temp.val > index.val) {
                //头插法
                index.next = temp;
                temp = index;
                tempHead = index;
                index = indexNext;
                continue;
            }

            // index 从temp.next开始比较遍历
            while (temp.next != null && index.val >= temp.next.val) {
                temp = temp.next;
            }
            //如果temp.next==null 并且 index.val > temp.val 则插在已排序区间的尾部
            if (temp.next == null && index.val > temp.val){
                temp.next = index;
                index.next = null;
                // 恢复temp游标
                temp = tempHead;
                index = indexNext;
                continue;
            }

            // 否则插入在已排序列表的中间
            ListNode changeListNode = temp.next;
            temp.next = index;
            index.next = changeListNode;
            // 恢复temp游标
            temp = tempHead;
            index = indexNext;
        }

        return tempHead;
    }
}
