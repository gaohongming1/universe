package com.universe.origin.star.leetcode.array.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 581. 最短无序连续子数组
 */
public class ShortestContinuousSubarray581 {
    public static void main(String[] args) {
        ShortestContinuousSubarray581 shortestContinuousSubarray581 = new ShortestContinuousSubarray581();
        int[] arr = new int[]{1, 2, 4, 5, 3};
        shortestContinuousSubarray581.findUnsortedSubarray(arr);
    }

    /**
     * 采用单调栈来解决
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        if (nums.length == 2) {
            if (nums[0] <= nums[1]) {
                return 0;
            } else {
                return 2;
            }
        }

        int n = nums.length - 1;
        Deque<Integer> leftStack = new LinkedList<>();
        Deque<Integer> rightStack = new LinkedList<>();
        int left = 0;
        int right = nums.length - 1;
        leftStack.push(0);
        rightStack.push(nums.length - 1);
        boolean leftStatue = true;
        boolean rightStatus = true;
        for (int i = 1; i < nums.length - 1; i++) {
            // 当前元素必须大于左边栈顶元素 并且小于右栈的栈顶元素
            if (!leftStack.isEmpty()) {
                if (nums[leftStack.peek()] <= nums[i] && leftStatue) {
                    leftStack.push(i);
                    left = i;
                } else {
                    while (!leftStack.isEmpty() && nums[leftStack.peek()] > nums[i]) {
                        left = leftStack.pop();
                        leftStatue = false;
                    }
                }
            }

            if (!rightStack.isEmpty()) {
                if (nums[rightStack.peek()] >= nums[n - i] && rightStatus) {
                    rightStack.push(n - i);
                    right = n - i;
                } else {
                    while (!rightStack.isEmpty() && nums[rightStack.peek()] < nums[n - i]) {
                        right = rightStack.pop();
                        rightStatus = false;
                    }
                }
            }

            // 代表整个数组都需要排序
            if (leftStack.isEmpty() && rightStack.isEmpty()) {
                return nums.length;
            }
        }

        //处理完中间数据后还需要处理两边的数据
        int limitRight = rightStack.isEmpty() ? nums[n] : rightStack.peek();

        // 右边栈顶必须大于左边栈顶
        while (!leftStack.isEmpty() && nums[leftStack.peek()] > limitRight) {
            left = leftStack.pop();
        }
        while (!rightStack.isEmpty() && nums[0] > nums[rightStack.peek()]) {
            right = rightStack.pop();
        }


        if (right <= left) {
            return 0;
        }
        return right - left + 1;
    }
}
