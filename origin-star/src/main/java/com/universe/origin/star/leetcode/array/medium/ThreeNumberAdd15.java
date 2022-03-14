package com.universe.origin.star.leetcode.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 */
public class ThreeNumberAdd15 {
    public static void main(String[] args) {

    }


    /**
     * 为了保证不重复，只需要 a<b<c这样就肯定不会重复
     * 先将数组进行排序
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int thirdIndex = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            //避免相同元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                //避免相同元素
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int k = thirdIndex;
                //从高位向低位查找 并记录当前下边，下次直接从下标处开始查找  因为i 和j 都是向高位移动的，所以 第三个数肯定是递减的
                while (k > j && (nums[i] + nums[j] + nums[k]) > 0) {
                    k--;
                }
                if (k == j) {
                    break;
                }

                if ((nums[i] + nums[j] + nums[k]) == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                }

            }
        }
        return res;
    }
}
