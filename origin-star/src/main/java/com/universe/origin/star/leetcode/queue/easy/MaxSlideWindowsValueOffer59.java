package com.universe.origin.star.leetcode.queue.easy;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author gaohongming
 * @version 1.0.0
 * @ClassName MaxSlideWindowsValueOffer59.java
 * @Description TODO
 * @createTime 2020年12月24日 22:14:00
 * <p>
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 *  
 * 使用单调队列的原理 单调队列保留窗口内的递增序列
 */
public class MaxSlideWindowsValueOffer59 {
    public static void main(String[] args) {
        MaxSlideWindowsValueOffer59 maxSlideWindowsValueOffer59 = new MaxSlideWindowsValueOffer59();
        System.out.println(Arrays.toString(maxSlideWindowsValueOffer59.maxSlidingWindow(new int[]{1,-1}, 1)));
    }




    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length ==0 || k==0){
            return new int[]{};
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];

        int index = 0;
        for (int i = 0; i < nums.length; i++) {

            //保持当前窗口单调队列递减判断当前窗口出队元素
            if (i >= k) {
                //窗口出队 i-k 位置元素
                if (deque.getLast() == nums[i - k]) {
                    deque.removeLast();
                }
            }

            // 入单调递减队列 从队头向队尾冒
            while (deque.size() != 0 && deque.getFirst() < nums[i]) {
                deque.removeFirst();
            }
            deque.addFirst(nums[i]);
            if (i>=k-1){
                result[index] = deque.getLast();
                index +=1;
            }
        }

        return result;

    }

}
