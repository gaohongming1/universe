package com.universe.origin.star.leetcode.array.easy;

/**
 * 136. 只出现一次的数字
 * 使用异或运算
 * 异或运算规则
 * 一个数和它本身异或运算返回的是0
 *
 * 一个数和0异或运算返回的是数字本身
 */
public class OnlyOnceNum136 {

    public int singleNumber(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums[i]; i++) {
            sum ^= nums[i];
        }
        return sum;
    }
}
