package com.universe.origin.star.leetcode.stack.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: true
 */
public class Duplicate271 {
    public static void main(String[] args) {

    }

    public boolean containsDuplicate(int[] nums) {
        //return Arrays.stream(nums).distinct().count()!=nums.length;
        //使用栈去重
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        return set.size() != nums.length;


    }

}
