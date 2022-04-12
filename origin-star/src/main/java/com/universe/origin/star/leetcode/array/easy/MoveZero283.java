package com.universe.origin.star.leetcode.array.easy;

/**
 * 283. 移动零
 */
public class MoveZero283 {

    /**
     * 使用双指针的方式
     */
    public void moveZeroes(int[] nums) {

        // 移动非0的元素
        int zeroIndex = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 当前元素和0下标元素置换
                if (zeroIndex != -1 && zeroIndex <= i) {
                    int temp = nums[i];
                    nums[i] = nums[zeroIndex];
                    nums[zeroIndex] = temp;
                    // 挪动0下标
                    zeroIndex = zeroIndex + 1;
                }
            } else {
                if (zeroIndex == -1) {
                    zeroIndex = i;
                }
            }
        }

    }
}
