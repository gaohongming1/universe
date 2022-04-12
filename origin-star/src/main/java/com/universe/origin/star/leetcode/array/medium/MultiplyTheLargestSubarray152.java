package com.universe.origin.star.leetcode.array.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 152. 乘积最大子数组
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 */
public class MultiplyTheLargestSubarray152 {

    /**
     * 动态规划方式求解
     *
     */
    public int maxProduct(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        System.arraycopy(nums, 0, max, 0, nums.length);
        System.arraycopy(nums, 0, min, 0, nums.length);

        for (int i = 1; i < nums.length; i++) {
            int tempMax = Math.max((max[i - 1] * nums[i]), Math.max(min[i - 1] * nums[i], nums[i]));
            int tempMin = Math.min((max[i - 1] * nums[i]), Math.min(min[i - 1] * nums[i], nums[i]));
            max[i] = tempMax;
            min[i] = tempMin;
        }
        int ans = max[0];
        for (int i = 1; i < max.length; ++i) {
            ans = Math.max(ans, max[i]);
        }

        return ans;
    }
}
