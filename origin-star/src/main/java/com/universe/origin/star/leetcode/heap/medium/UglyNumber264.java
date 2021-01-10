package com.universe.origin.star.leetcode.heap.medium;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 编写一个程序，找出第 n 个丑数。
 * <p>
 * 丑数就是质因数只包含 2, 3, 5 的正整数。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 */
public class UglyNumber264 {
    public static void main(String[] args) {
        UglyNumber264 uglyNumber264 = new UglyNumber264();
        uglyNumber264.nthUglyNumber2(10);
    }


    /**
     * 使用小根堆方法 基本思想还是当前丑数是之前的丑数通过乘因子得到的
     *
     * @param n
     * @return
     */
    public int nthUglyNumber2(int n) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1);
        Set<Integer> set = new HashSet<>();
        set.add(1);

        for (int i = 1; i < n; i++) {
            int current = priorityQueue.poll();

            int p1 = current * 2;
            int p2 = current * 3;
            int p3 = current * 5;
            if (!set.contains(p1)) {
                priorityQueue.add(p1);
            }
            if (!set.contains(p2)) {
                priorityQueue.add(p2);
            }
            if (!set.contains(p3)) {
                priorityQueue.add(p3);
            }
        }
        return priorityQueue.poll();

    }


    /**
     * 后边的丑数是前面的数字乘过来的
     * 动态规划方法
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;

        int index = 1;
        int result = 1;
        int[] temp = new int[n];
        temp[0] = 1;
        while (index < n) {
            result = Math.min(Math.min(temp[p1] * 2, temp[p2] * 3), temp[p3] * 5);
            if (result == temp[p1] * 2) {
                p1 += 1;
            }
            if (result == temp[p2] * 3) {
                p2 += 1;
            }
            if (result == temp[p3] * 5) {
                p3 += 1;
            }
            temp[index++] = result;
        }
        return result;
    }
}
