package com.universe.origin.star.leetcode.array.easy;

/**
 * 169. 多数元素
 * 使用投票算法
 */
public class MostElements169 {
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
           count = candidate == nums[i] ? count + 1 : count - 1;
        }
        return candidate;
    }

}
