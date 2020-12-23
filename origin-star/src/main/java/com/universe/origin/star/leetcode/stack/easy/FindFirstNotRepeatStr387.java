package com.universe.origin.star.leetcode.stack.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 */
public class FindFirstNotRepeatStr387 {
    public static void main(String[] args) {
        FindFirstNotRepeatStr387 findFirstNotRepeatStr387 = new FindFirstNotRepeatStr387();
        findFirstNotRepeatStr387.firstUniqChar("");

    }

    /**
     * 使用hash表
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),-1);
            }else {
                map.put(s.charAt(i),i);
            }
        }
        // 找到i最小的并返回
        int first = s.length();
        for (Map.Entry<Character,Integer> entry : map.entrySet()){
            int index = entry.getValue();
            if (index<first && index!=-1){
                first = index;
            }
        }
        if (first == s.length()){
            return -1;
        }
        return first;
    }
}
