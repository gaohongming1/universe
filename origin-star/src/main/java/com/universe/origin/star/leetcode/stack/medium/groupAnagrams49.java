package com.universe.origin.star.leetcode.stack.medium;


import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * 基本思想是使用哈希表加上字符是有序的原则进行记录，主要是哈希表。
 */
public class groupAnagrams49 {
    public static void main(String[] args) {
        System.out.println('a'+'b');

    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
          // 字符串排序
            char[] str = strs[i].toCharArray();
            Arrays.sort(str);
            String key = new String(str);
            List<String> list = map.getOrDefault(key,new ArrayList<>());
            list.add(strs[i]);
            map.put(key,list);
        }

        return map.values().stream().collect(Collectors.toList());
    }
}
