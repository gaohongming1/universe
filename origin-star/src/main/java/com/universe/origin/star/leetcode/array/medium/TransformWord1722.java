package com.universe.origin.star.leetcode.array.medium;

import java.util.*;

/**
 * 给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词， 但是一次只能改变一个字符。每一步得到的新词都必须能在字典中找到。
 * <p>
 * 编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出:
 * ["hit","hot","dot","lot","log","cog"]
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: []
 * <p>
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 */
public class TransformWord1722 {
    public static void main(String[] args) {
        TransformWord1722 transformWord1722 = new TransformWord1722();
        List<String> list = new ArrayList<>();
        list.add("hot");
        //list.add("dot");
        list.add("dog");
        list.add("dot");
        //list.add("log");
        //list.add("cog");
        transformWord1722.findLadders("hot", "dog", list);
    }

    /**
     * 使用dfs进行搜索，开始单词和结束单词的差别字母作为结束的步骤
     * 超时
     */
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        //初始化当前元素可一步转换成的元素
        Map<String, List<String>> map = new HashMap<>();
        boolean status = false;
        // 初始化map
        for (int i = 0; i < wordList.size(); i++) {
            if (!status && wordList.get(i).equals(endWord)) {
                status = true;
            }
            map.put(wordList.get(i), new ArrayList<>());
        }
        if (!status) {
            return new ArrayList<>();
        }

        //找到开始节点可以访问的并放入map中
        map.put(beginWord, new ArrayList<>());
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).equals(beginWord)){
                continue;
            }
            if (isDifferOneStr(beginWord, wordList.get(i))) {
                map.get(beginWord).add(wordList.get(i));
            }
        }


        for (int i = 0; i < wordList.size(); i++) {
            String currentStr = wordList.get(i);
            if (currentStr.equals(beginWord)) {
                continue;
            }
            for (int j = i + 1; j < wordList.size(); j++) {
                String judgeStr = wordList.get(j);
                if (judgeStr.equals(beginWord)) {
                    continue;
                }
                //判断两个字符是否是相差一个字符
                if (isDifferOneStr(currentStr, judgeStr)) {
                    // 分别记录
                    map.get(currentStr).add(wordList.get(j));
                    map.get(judgeStr).add(wordList.get(i));
                }
            }
        }
        List<String> firstList = map.get(beginWord);
        for (int i = 0; i < firstList.size(); i++) {
            List<String> result = new ArrayList<>();
            Set<String> visit = new HashSet<>();
            visit.add(firstList.get(i));
            result.add(beginWord);
            result.add(firstList.get(i));
            boolean status2 = calc(endWord, map, visit, firstList.get(i), result);
            if (status2) {
                return result;
            }
        }
        return new ArrayList<>();
    }

    public boolean calc(String end, Map<String, List<String>> map, Set<String> visited, String currentStr, List<String> result) {
        boolean status = false;
        //找到当前的元素可一步转换成的元素
        List<String> canVisit = map.get(currentStr);

        if (canVisit.contains(end)){
            result.add(end);
            return true;
        }

        // 循环可一步转换的列表
        for (int i = 0; i < canVisit.size(); i++) {
            String current = canVisit.get(i);

            if (current.equals(end)) {
                result.add(end);
                return true;
            }

            if (!visited.contains(current)) {
                // 进行访问
                visited.add(current);
                result.add(current);
                status = calc(end, map, visited, current, result);
                //代表递归找到了，直接返回，否则回退
                if (status) {
                    break;
                } else {
                    result.remove(current);
                }
            }
        }
        return status;
    }

    public boolean isDifferOneStr(String str1, String str2) {
        int count = 0;
        if (str1.length()!=str2.length()){
            return false;
        }
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count += 1;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }
}
