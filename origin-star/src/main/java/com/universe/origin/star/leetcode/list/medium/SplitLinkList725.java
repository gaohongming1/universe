package com.universe.origin.star.leetcode.list.medium;

import com.universe.origin.star.leetcode.list.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 725. 分隔链表
 * 给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
 * <p>
 * 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。
 * <p>
 * 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。
 * <p>
 * 返回一个符合上述规则的链表的列表。
 * <p>
 * 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * root = [1, 2, 3], k = 5
 * 输出: [[1],[2],[3],[],[]]
 * 解释:
 * 输入输出各部分都应该是链表，而不是数组。
 * 例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.next.next.next = null。
 * 第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
 * 最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。
 * 示例 2：
 * <p>
 * 输入:
 * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 * 输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 * 解释:
 * 输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
 */
public class SplitLinkList725 {
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
//
//        ListNode l5 = new ListNode();
//        l5.val = 5;
//        l4.next = l5;
//
//        ListNode l6 = new ListNode();
//        l6.val = 6;
//        l5.next = l6;
//
//        ListNode l7 = new ListNode();
//        l7.val = 7;
//        l6.next = l7;
//
//        ListNode l8 = new ListNode();
//        l8.val = 8;
//        l7.next = l8;
        SplitLinkList725 splitLinkList725 = new SplitLinkList725();
        splitLinkList725.splitListToParts(l1, 5);

    }

    /**
     * 1： 先计算长度决定每组的大小
     * 2： 在拿余数作为前面每组的+1 分组完成
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        if (root == null ) {
            ListNode[] listNodes = new ListNode[k];
            return listNodes;
        }

        if (root.next==null){
            ListNode[] listNodes = new ListNode[k];
            listNodes[0] = root;
            return listNodes;
        }

        if (k==1){
            return new ListNode[]{root};
        }

        int len = 0;
        ListNode index = root;
        while (index != null) {
            index = index.next;
            len += 1;
        }

        // 计算每组大小
        int groupSize = len / k;

        int another = len % k;
        if (another == 0) {
            another = -1;
        }
        if (groupSize == 0) {
            groupSize = 1;
            another = -1;
        }


        int anotherIndex = 0;

        List<ListNode> list = new ArrayList<ListNode>();
        List<ListNode> listLast = new ArrayList<ListNode>();

        ListNode[] array = new ListNode[k];
        index = root;
        int arrayIndex = 0;
        int currentCount = 0;

        ListNode currentNodeIndex = null;
        while (index != null) {
            ListNode temp = index.next;
            // list暂时记录额外分的
            if (currentCount == groupSize && anotherIndex < another) {
                list.add(index);
                listLast.add(currentNodeIndex);
                index = index.next;
                anotherIndex += 1;
                arrayIndex += 1;
                currentCount = 0;
                continue;
            }
            // 累加
            if (currentCount == 0) {
                currentNodeIndex = index;
                array[arrayIndex] = currentNodeIndex;
                currentCount += 1;
            } else {
                currentNodeIndex.next = index;
                currentNodeIndex = currentNodeIndex.next;
                currentCount += 1;
            }

            //当前组大小已经满足 并且不用补充1
            if (currentCount == groupSize && anotherIndex >= another) {
                currentCount = 0;
                arrayIndex += 1;
                // 末尾节点设置为null
                currentNodeIndex.next = null;
            }
            index = temp;
        }

        for (int i = 0; i < list.size(); i++) {
            listLast.get(i).next = list.get(i);
            list.get(i).next = null;
        }

        return array;

    }
}
