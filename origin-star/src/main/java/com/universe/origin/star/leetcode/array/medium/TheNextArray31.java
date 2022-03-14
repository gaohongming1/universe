package com.universe.origin.star.leetcode.array.medium;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * https://leetcode-cn.com/problems/next-permutation/
 */
public class TheNextArray31 {
    public static void main(String[] args) {
        TheNextArray31 theNextArray31 = new TheNextArray31();
        int[] array = new int[]{100,99,98,97,96,95,94,93,92,91,90,89,88,87,86,85,84,83,82,81,80,79,78,77,76,75,74,73,72,71,70,69,68,67,66,65,64,63,62,61,60,59,58,57,56,55,54,53,52,51,50,49,48,47,46,45,44,43,42,41,40,39,38,37,36,35,34,33,32,31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
        theNextArray31.nextPermutation(array);
        System.out.println(Arrays.toString(array));
//        sort(array, 0, array.length - 1);
//        System.out.println(Arrays.toString(array));
    }

    /**
     * 字典序算法
     * 从右到左找到一个左边小于右边的数字然后交换  交换完成后就是字典序
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        if (nums.length == 2) {
            int temp = nums[1];
            nums[1] = nums[0];
            nums[0] = temp;
            return;
        }

        //寻找非降序的待交换元素
        int index = nums.length - 2;
        boolean find = false;
        while (index >= 0) {
            if (nums[index] >= nums[index + 1]) {
                index--;
            } else {
                find = true;
                break;

            }
        }

        //如果没有找到则需要反转
        if (!find) {
            int l = 0;
            int r = nums.length - 1;
            while (l < r) {
                swap(nums, l, r);
                l++;
                r--;
            }
            return;
        }

        //从右边到左找到大于待交换元素的
        int rIndex = nums.length - 1;
        while (rIndex > index) {
            if (nums[rIndex] > nums[index]) {
                break;
            } else {
                rIndex--;
            }
        }
        swap(nums, index, rIndex);
        sort(nums, index + 1, nums.length - 1);

    }

    public static void swap(int[] num, int l, int r) {
        int temp = num[l];
        num[l] = num[r];
        num[r] = temp;
    }

    public static void sort(int[] nums, int start, int end) {
        int len = end - start + 1;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j + 1 < len - i; j++) {
                if (nums[start + j] > nums[start + j + 1]) {
                    int temp = nums[start + j];
                    nums[start + j] = nums[start + j + 1];
                    nums[start + j + 1] = temp;
                }
            }
        }
    }


}
