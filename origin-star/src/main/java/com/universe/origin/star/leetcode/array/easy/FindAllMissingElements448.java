package com.universe.origin.star.leetcode.array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FindAllMissingElements448 {


    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int x = (nums[i] - 1) % n;
            nums[x] += n;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
