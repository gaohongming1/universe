package com.universe.origin.star.leetcode.stack.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author gaohongming
 * @version 1.0.0
 * @ClassName RemoveRepeatLetter316.java
 * @Description TODO
 * @createTime 2020年12月22日 21:53:00
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 示例 1：
 * <p>
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 * <p>
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *
 *
 * 研究下单调栈
 */
public class RemoveRepeatLetter316 {
    public static void main(String[] args) {
        RemoveRepeatLetter316 removeRepeatLetter316 = new RemoveRepeatLetter316();
        System.out.println(removeRepeatLetter316.removeDuplicateLetters("bbcaac"));
    }

    /**
     * 第一种算法 使用栈的思想 ，每次从字符串中获取字符和栈顶元素比较 如果满足条件
     * 栈顶元素大于当前元素 并且剩余元素包含栈顶元素，则去除栈顶元素，将当前元素入栈
     *
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        if (s.length() < 2) {
            return s;
        }

        Map<Character, Boolean> addStatus = new HashMap<>();
        Map<Character, Integer> repeatNum = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            Character str = s.charAt(i);
            if (addStatus.containsKey(str)) {
                repeatNum.put(str, repeatNum.get(str) + 1);
            } else {
                addStatus.put(str, false);
                repeatNum.put(str, 1);
            }
        }

        Stack<Character> stack = new Stack<>();
        stack.add(s.charAt(0));
        addStatus.put(s.charAt(0), true);
        repeatNum.put(s.charAt(0), repeatNum.get(s.charAt(0)) - 1);
        for (int i = 1; i < s.length(); i++) {
            Character current = s.charAt(i);
            if (addStatus.get(current)){
                repeatNum.put(current,repeatNum.get(current)-1);
                continue;
            }
            // 当栈不空并且栈顶元素大于当前元素  并且当前元素后续的元素中还有栈顶元素，则可将栈顶元素去除
            // 这里不会出现某个元素小于当前元素 但是栈顶元素之前的元素包含这个元素的情况 也就是只会出现  大于当前元素并不重复、当前元素、大于当前元素（重复、不重复）栈顶位置    和当前元素比较
            while (!stack.isEmpty() && stack.peek() > current && repeatNum.get(stack.peek()) > 0) {
                addStatus.put(stack.pop(), false);
            }

            // 加入当前元素
            if (!addStatus.get(current)) {
                stack.add(current);
                addStatus.put(current, true);
                repeatNum.put(current, repeatNum.get(current) - 1);
            }

        }

        // 出栈
        StringBuffer stringBuffer = new StringBuffer();
        while (!stack.isEmpty()) {
            stringBuffer.append(stack.pop());
        }
        return stringBuffer.reverse().toString();
    }
}
