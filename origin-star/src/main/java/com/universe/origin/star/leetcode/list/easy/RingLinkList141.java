package com.universe.origin.star.leetcode.list.easy;

/**
 * 141. 环形链表
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * <p>
 * 进阶：
 * <p>
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 * <p>
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目范围是 [0, 104]
 * -105 <= Node.val <= 105
 * pos 为 -1 或者链表中的一个 有效索引 。
 */
public class RingLinkList141 {
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
        RingLinkList141 ringLinkList141 = new RingLinkList141();
        ringLinkList141.hasCycle(l1);
    }

    /**
     * 使用快慢指针，快指针和慢指针相遇则代表有环
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast = head.next;
        ListNode slow = head;
        while (slow != fast) {
            if (fast==null || fast.next ==null){
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }
        return true;

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
