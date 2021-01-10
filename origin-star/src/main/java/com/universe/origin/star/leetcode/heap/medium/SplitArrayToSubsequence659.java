package com.universe.origin.star.leetcode.heap.medium;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author gaohongming
 * @version 1.0.0
 * @ClassName SplitArrayToSubsequence659.java
 * @Description TODO
 * @createTime 2021年01月10日 18:24:00
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。
 * <p>
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 * 示例 2：
 * <p>
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * 示例 3：
 * <p>
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 */
public class SplitArrayToSubsequence659 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 3, 4, 4, 5, 5};
        SplitArrayToSubsequence659 splitArrayToSubsequence659 = new SplitArrayToSubsequence659();
        splitArrayToSubsequence659.isPossible2(arr);

    }

    /**
     * 解法2 贪心思路
     *
     * @param nums
     * @return
     */
    public boolean isPossible2(int[] nums) {
        // 统计每个的出现次数
        Map<Integer, Integer> countMap = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (countMap.containsKey(nums[i])) {
                countMap.put(nums[i], countMap.get(nums[i]) + 1);
            } else {
                countMap.put(nums[i], 1);
            }
        }

        // 统计结尾key：子序数量 但凡是记录endMap的子序长度都会大于等于3
        Map<Integer, Integer> endMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (countMap.get(nums[i]) == 0) {
                continue;
            }

            if (endMap.containsKey(nums[i] - 1) && endMap.get(nums[i] - 1) >= 1) {
                // x-1 结尾的子序数量减1
                endMap.put(nums[i] - 1, endMap.get(nums[i] - 1) - 1);
                // x 结尾的子序数量加一
                if (endMap.containsKey(nums[i])) {
                    endMap.put(nums[i], endMap.get(nums[i]) + 1);
                } else {
                    endMap.put(nums[i], 1);
                }
                countMap.put(nums[i], countMap.get(nums[i]) - 1);
            } else {
                // 不存在x-1结尾的子序，需要新建  x x+1  x+2  为一个新的子序 如果x+1  x+2 的剩余数量小于0则返回false
                if (countMap.containsKey(nums[i] + 1) && countMap.get(nums[i] + 1) > 0 && countMap.containsKey(nums[i] + 2) && countMap.get(nums[i] + 2) > 0) {
                    countMap.put(nums[i] + 1, countMap.get(nums[i] + 1) - 1);
                    countMap.put(nums[i] + 2, countMap.get(nums[i] + 2) - 1);
                    countMap.put(nums[i], countMap.get(nums[i]) - 1);
                    if (endMap.containsKey(nums[i] + 2)) {
                        endMap.put(nums[i] + 2, endMap.get(nums[i] + 2) + 1);
                    } else {
                        endMap.put(nums[i] + 2, 1);
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 解法1    hash表加上小根堆  保证x结尾的子序的长度必须大于三 贪心思路
     * 基本思想是贪心的思想  如何保证每个子序列长度超过三？
     * 只能是碰到一个元素X  分配给x-1 结尾的子序列，但是需要分配给长度最小的X-1结尾的子序列
     *
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {
        // key是子序列的结尾   值是以这个值结尾的子序列的长度
        Map<Integer, PriorityQueue<Integer>> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int length = 1;

            // 包含i-1结尾的子序
            if (countMap.containsKey(nums[i] - 1)) {
                PriorityQueue<Integer> priorityQueue = countMap.get(nums[i] - 1);
                // 弹出堆顶元素
                if (priorityQueue.size() > 0) {
                    length = priorityQueue.poll() + 1;
                }
            }

            // 记录当前元素结尾的子序长度
            if (countMap.containsKey(nums[i])) {
                PriorityQueue<Integer> priorityQueue2 = countMap.get(nums[i]);
                priorityQueue2.add(length);
            } else {
                PriorityQueue<Integer> priorityQueue2 = new PriorityQueue<>();
                priorityQueue2.add(length);
                countMap.put(nums[i], priorityQueue2);
            }
        }

        //判断所有的子序列长度是不是都大于3
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : countMap.entrySet()) {
            if (entry.getValue().size() > 0 && entry.getValue().element() < 3) {
                return false;
            }
        }
        return true;
    }

}






























