package com.universe.origin.star.leetcode.stack.easy;

import java.util.HashMap;
import java.util.Map;

/**
 *给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 *
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 *
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 *
 * @date 2020-12-16 11:43
 * @author gaohongming3
 */
public class WordLaw290 {
    public static void main(String[] args) {
        WordLaw290 wordLaw290 = new WordLaw290();
        System.out.println(wordLaw290.wordPattern("jquery","jquery"));

    }
    public boolean wordPattern(String pattern, String s) {
        // 维护key到单词的映射结构
        Map<Character,String> map = new HashMap<>();
        char[] pa = pattern.toCharArray();
        String[] strs = s.split(" ");
        if (pa.length!=strs.length){
            return false;
        }
        int i = 0;
        for (; i < pa.length; i++) {
            //不包含key 包含value的场景
            if (map.containsValue(strs[i]) && !map.containsKey(pa[i])){
                return false;
            }
            if (!map.containsKey(pa[i])){
                map.put(pa[i],strs[i]);
            }else {
                //代表当前位置的s的单词和map的value必须相等否则false
                if (!strs[i].equals(map.get(pa[i]))){
                    return false;
                }
            }
        }

        if (i!=strs.length){
            return false;
        }
        return true;
    }
}
