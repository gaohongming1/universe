package com.universe.origin.star.leetcode.list.easy;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class PalindromeLinkList234 {
    ListNode z;
    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = 1;

        ListNode l2 = new ListNode();
        l2.val = 0;
        l1.next = l2;

        ListNode l3 = new ListNode();
        l3.val = 1;
        l2.next = l3;
        PalindromeLinkList234 palindromeLinkList234 = new PalindromeLinkList234();
        palindromeLinkList234.isPalindrome(l1);
        //palindromeLinkList234.change(l1);

    }

    public void change(ListNode head) {
        ListNode l3 = new ListNode();
        l3.val = 100;
        head.next=l3;
    }

    /**
     * 利用递归的思想
     * 先找到尾结点，将尾结点返回
     * 拿到返回值再从第一个节点向后遍历对比
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head==null || head.next==null){
            return true;
        }
        this.z = head;
        ListNode d = head;

        return recursion(d);
    }

    public boolean recursion(ListNode d) {
        // 到达尾部 开始和头结点判断
        if (d.next==null){
            if (z.val==d.val){
                return true;
            }else {
                return false;
            }
        }

        boolean status = recursion(d.next);
        z = z.next;

        if (!status){
            return false;
        }else {
            if (z.val == d.val){
                return true;
            }else {
                return false;
            }
        }
    }



    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
