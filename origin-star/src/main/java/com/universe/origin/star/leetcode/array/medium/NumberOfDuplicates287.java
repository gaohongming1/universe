package com.universe.origin.star.leetcode.array.medium;

/**
 * 287. 寻找重复数
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 * todo 666 单个数组的操作
 */
public class NumberOfDuplicates287 {
    public static void main(String[] args) {

    }


    /**
     * 使用二分查找的方式
     * 数组内元素都是 n范围内的
     * 也就是第i 位置的元素  在数组中必然有 i-1 个小于i的元素   否则 第i位置就是重复的
     * 根据上面的特性进行二分查找 不断缩小位置
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int index = 1;
        int len = nums.length - 1;
        int ans = -1;
        while (index <= len) {
            int middle = (index + len) / 2;
            int count = 0;

            // 判断小于index位置的元素有多少个
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= middle) {
                    count++;
                }
            }

            // 判断结果
            if (count <= middle) {
                index = middle + 1;
            } else {
                len = middle - 1;
                ans = middle;
            }
        }
        return ans;
    }
}
