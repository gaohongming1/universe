package com.universe.origin.star.leetcode.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class TotalNumberOfCombinations39 {
    public static void main(String[] args) {

        int[] arr = new int[]{2, 3, 6, 7};
        TotalNumberOfCombinations39 totalNumberOfCombinations39 = new TotalNumberOfCombinations39();
        System.out.println(totalNumberOfCombinations39.combinationSum(arr, 7).toString());
    }

    List<List<Integer>> res;

    int range;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //先进行升序排序
        Arrays.sort(candidates);
        if (candidates[0] > target) {
            return new ArrayList<>();
        }

        // 找到中间位置
        int index = candidates.length - 1;
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] > target) {
                index = i - 1;
            }
        }
        res = new ArrayList<>();
        this.range = index;

        // 在0到index位置进行dfs +剪枝 查找结果集
        find(candidates, target, 0, new ArrayList<>(), 0);

        return res;

    }

    public void find(int[] candidates, int target, int current, List<Integer> currentRes, int index) {
        if (index > this.range) {
            return;
        }

        // 超过之后直接return不再向下继续进行
        int add = current + candidates[index];
        if (add > target) {
            return;
        }

        //相等则记录结果然后返回 不再向下继续
        if (add == target) {
            currentRes.add(candidates[index]);
            res.add(new ArrayList<>(currentRes));
            currentRes.remove(currentRes.size() - 1);
            return;
        }

        // 小于的情况可以走两条路 加入集合 和不加入集合
        // 不加入集合的路径
        find(candidates, target, current, currentRes, index + 1);

        // 加入集合的路径 下一个要选择的还可以是当前节点
        currentRes.add(candidates[index]);
        find(candidates, target, add, currentRes, index);
        currentRes.remove(currentRes.size() - 1);

    }


}
