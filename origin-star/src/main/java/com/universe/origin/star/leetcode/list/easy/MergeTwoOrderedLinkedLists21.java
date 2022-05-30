package com.universe.origin.star.leetcode.list.easy;

import com.universe.origin.star.leetcode.list.ListNode;

public class MergeTwoOrderedLinkedLists21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            if (list1 == null && list2 == null){
                return null;
            } else if (list1 == null) {
                return list2;
            }else {
                return list1;
            }
        }

        ListNode index;
        if (list1.val < list2.val) {
            index = list1;
            list1 = list1.next;
        } else {
            index = list2;
            list2 = list2.next;
        }
        ListNode head = new ListNode();
        head.next = index;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                index.next = list1;
                list1 = list1.next;
            } else {
                index.next = list2;
                list2 = list2.next;
            }
            index = index.next;
        }
        index.next = list1 == null ? list2 : list1;

        return head.next;


    }
}
