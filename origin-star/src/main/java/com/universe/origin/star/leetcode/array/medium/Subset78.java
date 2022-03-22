package com.universe.origin.star.leetcode.array.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 */
public class Subset78 {
    List<List<Integer>> res;

    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        res.add(new ArrayList<>());
        dfs(nums, new ArrayList<>(), 0);
        return res;
    }

    public void dfs(int[] nums,List<Integer> currentRes, int index) {
        if (index == nums.length) {
            return;
        }
        //index 元素存在选择和不选择两种状态 选择的情况就是创建了一个新的解
        currentRes.add(nums[index]);
        res.add(new ArrayList<>(currentRes));
        dfs(nums,currentRes,index+1);
        currentRes.remove(currentRes.size() - 1);
        dfs(nums,currentRes,index+1);
    }


}
