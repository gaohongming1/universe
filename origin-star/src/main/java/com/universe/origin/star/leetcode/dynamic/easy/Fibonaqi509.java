package com.universe.origin.star.leetcode.dynamic.easy;

/**
 * 509. 斐波那契数
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n) 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 */
public class Fibonaqi509 {

    public static void main(String[] args) {
        Fibonaqi509 fibonaqi509 = new Fibonaqi509();
        System.out.println(fibonaqi509.fib(4));

    }

    public int fib(int n) {
        if (n==0){
            return 0;
        }
        if (n ==1){
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }
}
