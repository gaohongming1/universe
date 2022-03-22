package com.universe.origin.star.leetcode.array.medium;

/**
 * 33. 搜索旋转排序数组
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 */
public class SearchRotateSortArray33 {
    public static void main(String[] args) {
        int[] array = new int[]{3, 1};
        SearchRotateSortArray33 searchRotateSortArray33 = new SearchRotateSortArray33();
        System.out.println(searchRotateSortArray33.search(array, 3));

    }

    public int search(int[] nums, int target) {

        //先确定n-1的位置  也就是最大值
        int index = nums.length - 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                index = i;
            }
        }

        // 在0-(k-1) 的位置 进行二分查找
        if (nums[0] > target) {
            return find(nums, index + 1, nums.length - 1, target);
        } else {
            // 在k-(n-1) 的位置 进行二分查找
            return find(nums, 0, index, target);
        }
    }


    public int find(int[] num, int start, int end, int target) {
        if (end < start) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (num[middle] == target) {
            return middle;
        }
        if (num[middle] > target) {
            return find(num, start, middle - 1, target);
        } else {
            return find(num, middle + 1, end, target);
        }
    }
}
