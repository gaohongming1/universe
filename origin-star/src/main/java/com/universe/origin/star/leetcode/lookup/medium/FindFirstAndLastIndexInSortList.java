package com.universe.origin.star.leetcode.lookup.medium;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 */
public class FindFirstAndLastIndexInSortList {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(find(nums, 0, nums.length - 1, 2));
        FindFirstAndLastIndexInSortList findFirstAndLastIndexInSortList = new FindFirstAndLastIndexInSortList();
        System.out.println(Arrays.toString(findFirstAndLastIndexInSortList.searchRange(nums,8)));
    }

    /**
     * 先试用二分查找找到 元素 如果没找到则代表数组中没有
     * 然后在进行左右移动
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int index = find(nums, 0, nums.length - 1, target);
        if (index == -1) {
            return new int[]{-1, -1};
        }

        int left = index;
        while (left >= 0 && nums[left] == target) {
            left--;
        }
        int right = index;
        while (right <= nums.length - 1 && nums[right] == target) {
            right++;
        }

        return new int[]{left + 1, right - 1};
    }

    public static int find(int[] nums, int start, int end, int target) {

        if (start > end) {
            return -1;
        }
        if (start == end) {
            if (nums[start] == target) {
                return start;
            } else {
                return -1;
            }
        }
        int middle = (start + end - 1) / 2;
        if (nums[middle] == target) {
            return middle;
        }
        //使用小区域递归
        int res = -1;
        if (nums[middle] > target) {
            res = find(nums, start, middle - 1, target);
        } else {
            res = find(nums, middle + 1, end, target);
        }
        return res;
    }

}
