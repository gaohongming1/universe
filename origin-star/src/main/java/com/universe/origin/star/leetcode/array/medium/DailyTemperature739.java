package com.universe.origin.star.leetcode.array.medium;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DailyTemperature739 {

    /**
     * 暴力法
     */
    public int[] dailyTemperatures(int[] temperatures) {

        if (temperatures.length == 1) {
            return new int[]{0};
        }
        // 记录值以及最后出现的位置
        Map<Integer, Integer> indexMap = new HashMap<>();
        int n = temperatures.length - 1;
        int max = temperatures[n];
        indexMap.put(max, n);
        int[] res = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            indexMap.put(temperatures[i], i);
            // 代表未来没有温度能够超过当前温度
            if (temperatures[i] > max) {
                res[i] = 0;
                max = temperatures[i];
                continue;
            }
            // 代表未来有温度能够超过当前温度，从 temperatures[i] 到max累加  尝试第一的大于当前温度的值
            // TODO: 2022/3/31 这里可以用单调栈来维护 递增队列
            int min = Integer.MAX_VALUE;
            for (int j = temperatures[i] + 1; j <= max; j++) {
                if (indexMap.containsKey(j)) {
                    min = Math.min(min, indexMap.get(j) - i);
                }
            }
            if (min != Integer.MAX_VALUE) {
                res[i] = min;
            }
        }
        return res;
    }

    /**
     * 使用单调栈
     */
    public int[] dailyTemperatures2(int[] temperatures) {

        if (temperatures.length == 1) {
            return new int[]{0};
        }
        int[] res = new int[temperatures.length];

        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < temperatures.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                //栈顶元素和当前元素进行对比
                while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                    int index = stack.pop();
                    res[index] = i - index;
                }
                stack.push(i);
            }
        }

        return res;
    }
}
