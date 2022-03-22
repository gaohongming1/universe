package com.universe.origin.star.leetcode.array.medium;

import java.util.*;

/**
 * 128. 最长连续序列
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 * todo 666  并查集
 */
public class LongestContinuousSequence128 {
    public static void main(String[] args) {
        int[] nums = new int[]{0,3,7,2,5,8,4,6,0,1};
        LongestContinuousSequence128 longestContinuousSequence128 = new LongestContinuousSequence128();
        longestContinuousSequence128.longestConsecutive(nums);
    }

    /**
     * 采用并查集 加上hashMap的方式
     */
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, List<Integer>> tail = new HashMap<Integer, List<Integer>>();
        HashMap<Integer, List<Integer>> head = new HashMap<Integer, List<Integer>>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            } else {
                set.add(nums[i]);
            }
            // 队尾当做key
            if (tail.containsKey(nums[i] + 1)) {
                List current = tail.get(nums[i] + 1);
                current.add(nums[i]);
                tail.remove(nums[i] + 1);
                tail.put(nums[i], current);
            } else {
                List current = new ArrayList();
                current.add(nums[i]);
                tail.put(nums[i], current);
            }
        }
        if (tail.size() == 0) {
            return 0;
        }

        //根据队头元素升序排序
        List<List<Integer>> list = new ArrayList<>(tail.values());
        list.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0)-o2.get(0);
            }
        });

        //求解最大递增长度
        int temp = list.get(0).size();
        List<Integer> allRes = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            // 递增区间末尾 -1 等于前一个递增区间最大元素
            if (list.get(i).get(list.get(i).size() - 1) - list.get(i - 1).get(0)==1){
                temp = temp + list.get(i).size();
            }else {
                allRes.add(temp);
                temp = list.get(i).size();
            }
        }
        allRes.add(temp);


        return allRes.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        }).get();

    }


    /**
     * 采用更巧妙的方法
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int longest = 0;

        // 假设以num为起点不断尝试  num+1   +2  +3  判断是否在集合中，如果不存在则代表以当前为起点的路线中断  记录最长路径
        for (Integer num : set) {
            // 这里注意如果 num-1  在set中 那么代表 num-1判断出的最长路径肯定是包含了  num为起点的最长路径
            int currentNum = num;
            if (!set.contains(num-1)){
                int len = 1;
                while (set.contains(currentNum + 1)) {
                    len++;
                    currentNum++;
                }
                longest = Math.max(currentNum, longest);
            }
        }
        return longest;
    }

}
