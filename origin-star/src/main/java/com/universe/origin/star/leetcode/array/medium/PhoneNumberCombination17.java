package com.universe.origin.star.leetcode.array.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 */
public class PhoneNumberCombination17 {

    List<String> arr = new ArrayList<>();
    Map<Character, String> phoneMap;

    public List<String> letterCombinations(String digits) {
        phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        dfs(digits, 0, new StringBuffer());
        return arr;
    }

    public void dfs(String digits, int index,StringBuffer currentRes) {
        if (index == digits.length()) {
            arr.add(currentRes.toString());
            return;
        }else {
            char digit = digits.charAt(index);
            String arrs = phoneMap.get(digit);
            for (int i = 0; i < arrs.length(); i++) {
                currentRes.append(arrs.charAt(i));
                dfs(digits, index+1, currentRes);
                currentRes.deleteCharAt(currentRes.length() - 1);
            }
        }

    }


}
