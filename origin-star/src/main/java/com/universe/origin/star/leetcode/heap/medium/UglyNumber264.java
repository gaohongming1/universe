package com.universe.origin.star.leetcode.heap.medium;

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
        uglyNumber264.nthUglyNumber(10);
    }

    /**
     * 后边的丑数是前面的数字乘过来的
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
                p1 +=1;
            }
            if (result == temp[p2] * 3) {
                p2 +=1;
            }
            if (result == temp[p3] * 5) {
                p3 +=1;
            }
            temp[index++] = result;
        }
        return result;
    }
}
