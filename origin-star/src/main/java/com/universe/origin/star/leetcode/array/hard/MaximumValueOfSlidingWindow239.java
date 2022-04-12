package com.universe.origin.star.leetcode.array.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 滑动窗口的最大值
 */
public class MaximumValueOfSlidingWindow239 {

    /**
     * 滑动窗口最大值
     * 使用单调队列
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> stack = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        stack.push(0);

        // 这个stack 是一个当前窗口的递减队列并且   元素的位置和num的顺序一致
        for (int i = 1; i < k; i++) {
            while (!stack.isEmpty() && nums[i] >= nums[stack.peekLast()]) {
                stack.pollLast();
            }

            stack.offerLast(i);
        }
        int index = 0;
        res[index] = nums[stack.peekFirst()];
        index++;

        // 从分组的下一个元素开始进行复制  到最后一个元素
        for (int i = k; i < nums.length; i++) {
            // 将当前元素加入到合适的位置
            while (!stack.isEmpty() && nums[i] >= nums[stack.peekLast()]) {
                stack.pollLast();
            }
            stack.offerLast(i);

            // 再保证队头元素在窗口内
            while (stack.peekFirst() <= i - k) {
                stack.pollFirst();
            }
            res[index++] = nums[stack.peekFirst()];

        }
        return res;
    }
}
