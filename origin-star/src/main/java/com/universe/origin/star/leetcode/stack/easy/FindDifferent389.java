package com.universe.origin.star.leetcode.stack.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 找不同
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 *
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *
 * 请找出在 t 中被添加的字母。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 * 示例 2：
 *
 * 输入：s = "", t = "y"
 * 输出："y"
 * 示例 3：
 *
 * 输入：s = "a", t = "aa"
 * 输出："a"
 * 示例 4：
 *
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 * 哈希表
 */
public class FindDifferent389 {
    public static void main(String[] args) {

    }
    public char findTheDifference(String s, String t) {

        Map<Character,Integer> sMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (sMap.containsKey(s.charAt(i))){
                sMap.put(s.charAt(i),sMap.get(s.charAt(i))+1);
            }else {
                sMap.put(s.charAt(i),1);
            }
        }

        for (int i = 0; i < t.length(); i++) {
            if (sMap.containsKey(t.charAt(i))){
                if (sMap.get(t.charAt(i))==0){
                    return t.charAt(i);
                }else {
                    sMap.put(t.charAt(i),sMap.get(t.charAt(i)) - 1);
                }
            }else {
                return t.charAt(i);
            }
        }
        return 1;
    }
}
