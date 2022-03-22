package com.universe.origin.star.leetcode.array.medium;

import java.util.*;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
public class FullArrangement46 {

    List<List<Integer>> res;

    /**
     * dfs 进行搜索 每次获取可选排列表
     */
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        //dfs(nums, 0, new HashSet<>(), new ArrayList<>());
        dfs2(nums,0,new ArrayList<>());
        return res;
    }


    /**
     * 简单的回溯  使用set记录已经选择的元素
     */
    public void dfs(int[] nums, int index, Set<Integer> currentRes, List<Integer> currentResList) {
        if (index == nums.length) {
            res.add(new ArrayList<>(currentResList));
        }

        for (int num : nums) {
            if (!currentRes.contains(num)) {
                currentRes.add(num);
                currentResList.add(num);
                dfs(nums, index + 1, currentRes, currentResList);
                currentRes.remove(num);
                currentResList.remove(currentResList.size() - 1);
            }
        }
    }

    /**
     * 采用交换元素的方式 每次从待选择数组中拿值 然后进行回溯
     */
    public void dfs2(int[] nums, int index, List<Integer> currentResList) {
        if (index == nums.length) {
            res.add(new ArrayList<>(currentResList));
            return;
        }
        for (int i = index; i < nums.length; i++) {

            currentResList.add(nums[i]);
            // 交换到已排序
            swap(currentResList.size() - 1, i, nums);
            dfs2(nums, index + 1, currentResList);
            swap(currentResList.size() - 1, i, nums);
            currentResList.remove(currentResList.size() - 1);

        }
    }

    public static void swap(int a, int b, int[] arr) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
