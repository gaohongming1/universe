package com.universe.origin.star.leetcode.array.easy;

import java.util.*;

/**
 * 两数之和
 */
public class TwoNumAdd1 {
    public static void main(String[] args) {
        TwoNumAdd1 twoNumAdd1 = new TwoNumAdd1();
        int[] arr = new int[]{0,4,3,0};
        twoNumAdd1.twoSum(arr, 0);
    }
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!indexMap.containsKey(nums[i])) {
                indexMap.put(nums[i], new ArrayList<>());
            }
            indexMap.get(nums[i]).add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (indexMap.containsKey(target - nums[i])) {
                List<Integer> right = indexMap.get(target - nums[i]);
                for (int j = 0; j < right.size(); j++) {
                    if (right.get(j) != i) {
                        return new int[]{i, right.get(j)};
                    }
                }
            }
        }
        return null;
    }

    /**
     * 先用map判断
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexMap.containsKey(target - nums[i])) {
                return new int[]{i, indexMap.get(target - nums[i])};
            }
            indexMap.put(nums[i],i);
        }

        return null;
    }
}
