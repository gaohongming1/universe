package com.universe.origin.star.leetcode.stack.hard;

import java.util.Arrays;

/**
 * 分发糖果
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * <p>
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2:
 * <p>
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 * <p>
 * 使用贪心算法，在本次循环的过程中尽可能保证最小解，每个人初始只分发一个糖果
 * 序列 A1  B1   A2   B2  A3  B3
 * 整个序列需要满足两个规则
 * 从左向右遍历：在这次遍历的过程中采用贪心的策略得到当前的最优解 也就是  B1>A1  的时候  B1 = A1+1  满足这个规则
 * 从右向左遍历：在这次遍历的过程      A3>B3   A3=B3+1  满足这个规则
 * 同时满足以上两个规则则是最终结果
 */
public class DistributeCandy135 {
    public static void main(String[] args) {
        DistributeCandy135 distributeCandy135 = new DistributeCandy135();
        distributeCandy135.candy(new int[]{1, 3, 4, 5, 2});
    }

    public int candy(int[] ratings) {
        if (ratings.length == 1) {
            return 1;
        }
        //从左向右 记录糖果数量
        int[] left = new int[ratings.length];
        left[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        //从右向左
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && left[i] < left[i + 1]) {
                left[i] = left[i + 1] + 1;
            }
        }
        return Arrays.stream(left).sum();
    }
}
