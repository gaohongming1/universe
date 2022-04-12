package com.universe.origin.star.leetcode.array.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 238. 除自身以外数组的乘积
 * https://leetcode-cn.com/problems/product-of-array-except-self/
 * todo 666 左右乘积列表
 */
public class ProductOfArraysOtherThanItself238 {

    /**
     * 使用两个map记录 位置 ：向左的乘积累计
     * 空间复杂度是2n
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums.length <= 1) {
            return new int[]{0};
        }
        Map<Integer, Integer> leftMap = new HashMap<>();
        Map<Integer, Integer> rightMap = new HashMap<>();
        int len = nums.length;
        int leftCount = 1;
        int rightCount = 1;
        for (int i = 0; i < len; i++) {
            leftCount *= nums[i];
            rightCount *= nums[len - i - 1];
            leftMap.put(i, leftCount);
            rightMap.put(len - i - 1, rightCount);
        }
        int[] res = new int[len];
        for (int i = 1; i < len - 1; i++) {
            res[i] = leftMap.get(i - 1) * rightMap.get(i + 1);
        }
        res[0] = rightMap.get(1);
        res[len - 1] = leftMap.get(len - 2);
        return res;
    }

    /**
     * 思想和上面的一样
     * 但是空间是o1  因为输出数组不计算占用空间
     * 先把输出数组存储
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        if (nums.length <= 1) {
            return new int[]{0};
        }
        int len = nums.length;
        int[] res = new int[len];
        res[0] = nums[0];
        for (int i = 1; i < len; i++) {
            res[i] = res[i - 1] * nums[i];
        }

        // 在构造从右边的前缀乘积的时候在动态构造结果集
        // 最后一个元素的结果  = 前一个元素的左累积
        res[len - 1] = res[len - 2];
        int rightCount = 1;
        for (int i = len - 1; i > 0; i--) {
            res[i] = rightCount * res[i - 1];
            // 先计算当前元素的右累积
            rightCount = rightCount * nums[i];
        }
        res[0] = rightCount;
        return res;
    }

}
