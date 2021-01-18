package com.universe.origin.star.leetcode.array.medium;

/**
 * 剑指 Offer 46. 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，
 * 1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。
 * 请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= num < 231
 */
public class TransformNumToStrOffer46 {
    public static void main(String[] args) {
        System.out.println(Integer.valueOf('1' + "" + '8'));
        TransformNumToStrOffer46 transformNumToStrOffer46 = new TransformNumToStrOffer46();
        transformNumToStrOffer46.translateNum(18580);
    }


    /**
     * 动态规划的思想
     * 当前一个元素和当前元素组合小于26的时候状态转移方程
     * f(i) = f(i-1) + f(i-2)
     * 代表截止到当前点的转换方式是截止到前i-1 和i-2的
     *
     * @param num
     * @return
     */
    public int translateNum(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        int[] dp = new int[arr.length];
        if (arr.length == 1) {
            dp[0] = 1;
            return dp[arr.length - 1];
        }
        if (arr.length == 2) {
            if (arr[0] - 48 > 0 && Integer.valueOf(arr[0] + "" + arr[1]) < 26) {
                dp[1] = 2;
            } else {
                dp[1] = 1;
            }
            return dp[arr.length - 1];
        }
        dp[0] = 1;
        if (arr[0] - 48 > 0 && Integer.valueOf(arr[0] + "" + arr[1]) < 26) {
            dp[1] = 2;
        } else {
            dp[1] = 1;
        }

        for (int i = 2; i < arr.length; i++) {
            if (arr[i-1] - 48 > 0 && Integer.valueOf(arr[i-1] + "" + arr[i]) < 26) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[arr.length - 1];
    }
}
