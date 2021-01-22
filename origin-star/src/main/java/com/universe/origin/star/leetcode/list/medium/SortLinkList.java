package com.universe.origin.star.leetcode.list.medium;

import com.universe.origin.star.leetcode.list.ListNode;

import java.util.ArrayList;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 进阶：
 * <p>
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 * <p>
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 * <p>
 * 输入：head = []
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 */
public class SortLinkList {
    public static void main(String[] args) {
    }


    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        int len = 0;
        ListNode index = head;
        ArrayList<ListNode> list = new ArrayList<>();
        list.add(head);
        while (index.next != null) {
            index = index.next;
            len += 1;
            list.add(index);
        }

        return null;
    }


    public ListNode sort(ArrayList<ListNode> array, int left, int right) {
        if (left == right) {
            return array.get(left);
        }
        int middle = (left + right) / 2;
        ListNode list1 = sort(array, left, middle);
        ListNode list2 = sort(array, middle + 1, right);

        //合并
        ListNode head = merge(list1,list2);
        return head;

    }

    /**
     * 合并
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode merge(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                head.next = list2;
                list2 = list2.next;
                //断开当前节点
                head.next.next = null;
            } else {
                head.next = list1;
                list1 = list1.next;
                //断开当前节点
                head.next.next = null;
            }
            head = head.next;
        }

        while (list1 != null) {
            head.next = list1;
            list1 = list1.next;
            head.next.next = null;
            head = head.next;
        }

        while (list2 != null) {
            head.next = list2;
            list2 = list2.next;
            head.next.next = null;
            head = head.next;
        }
        return head.next;
    }
}
