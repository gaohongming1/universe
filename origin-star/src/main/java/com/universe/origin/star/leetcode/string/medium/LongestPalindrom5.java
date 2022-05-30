package com.universe.origin.star.leetcode.string.medium;

/**
 * 5. 最长回文子串
 */
public class LongestPalindrom5 {


    /**
     * 可以使用中心扩展法
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length() * 2 - 1;
        int ans = 0;
        String ansStr = "";
        for (int i = 0; i < len; i++) {
            int left = i / 2;
            int right = i / 2 + i % 2;

            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            left++;
            right--;
            int nowAns = right - left + 1;
            if (ans < nowAns) {
                ansStr = s.substring(left, right+1);
                ans = nowAns;
            }
        }
        return ansStr;
    }

}
