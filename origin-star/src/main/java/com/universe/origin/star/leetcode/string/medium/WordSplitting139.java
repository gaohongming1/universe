package com.universe.origin.star.leetcode.string.medium;

import java.util.*;

/**
 * 单词拆分
 */
public class WordSplitting139 {
    Set<String> dict;
    Set<String> routeCache;

    public static void main(String[] args) {
        String s = "catsandog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("and");
        wordDict.add("cat");
        WordSplitting139 wordSplitting139 = new WordSplitting139();
        wordSplitting139.wordBreak(s, wordDict);
    }

    /**
     * 动态规划解法
     * dp[i] 代表 0-i的字符串能否被字典拆分
     * 则 dp[i] = dp[j] && str(j,i) 是否在字典中
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        Set<String> wordDictSet = new HashSet(wordDict);
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];

    }

    public boolean wordBreak(String s, List<String> wordDict) {
        dict = new HashSet<>(wordDict);
        routeCache = new HashSet<>();
        return dfs(s, 0);
    }

    public boolean dfs(String s, int nextStart) {
        // 代表走到了最后 返回true
        if (nextStart == s.length()) {
            return true;
        }

        // 寻找字典包含的字符串 从最大的字符串进行递减提高效率
        StringBuilder word = new StringBuilder(s.substring(nextStart));
        int len = word.length();
        for (int i = 0; i < len; i++) {
            if (dict.contains(word.toString())) {
                int nextStartIndex = s.length() - i;
                if (routeCache.contains(word.toString() + (nextStartIndex-1))){
                    return false;
                }
                boolean status = dfs(s, nextStartIndex);
                if (status) {
                    return status;
                } else {
                    // 记录当前位置结束拼接的单词不行
                    routeCache.add(word.toString() + (nextStartIndex-1));
                    word.deleteCharAt(word.length() - 1);
                }
            }else {
                word.deleteCharAt(word.length() - 1);
            }

        }
        return false;
    }
}
