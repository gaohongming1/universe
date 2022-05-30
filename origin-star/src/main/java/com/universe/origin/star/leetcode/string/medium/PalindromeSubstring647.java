package com.universe.origin.star.leetcode.string.medium;

/**
 * 647. 回文子串
 */
public class PalindromeSubstring647 {

    public int countSubstrings(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        if (arr.length == 1) {
            return 1;
        }
        boolean dp[][] = new boolean[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i][i] = true;
        }

        // 如果dp[i][j] 是回文字符串 那么dp[i][j+1] 的值  和i-1  j+1 是否相等有关
        int ans = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                // j-i<2  代表 a b x 这样的串， dp[i+1][j-1] 是一个字符串 一个字符串肯定是true
                if (arr[i] == arr[j] && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ++ans;
                }
            }
        }
        return ans;
    }

    /**
     * 中心扩展法
     *
     * @param s
     * @return 比如字符串 a  b  c  d  e  f
     * 对应中心扩展点
     * a 、 ab  b  bc ...
     */
    public int countSubstrings2(String s) {
        int len = s.length() * 2 - 1;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            // 这里意思是中心扩展点的左下标 中心扩展点的下标是奇数那么就是两个
            int left = i / 2;
            int right = i / 2 + i % 2;
            //进行扩展
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                ++ans;
                left--;
                right++;
            }

        }

        return ans;
    }


    public static void main(String[] args) {
        int[][] arr = new int[10][10];
        for (int k = 1; k < arr.length; k++) {
            for (int i = 0; i < arr.length - 1; i++) {
                int j = k;
                arr[i][j] = 1;
            }
        }
        System.out.println("true");

    }
}
