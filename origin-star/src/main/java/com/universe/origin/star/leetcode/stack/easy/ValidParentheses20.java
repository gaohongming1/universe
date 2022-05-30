package com.universe.origin.star.leetcode.stack.easy;

import java.util.Deque;
import java.util.LinkedList;

public class ValidParentheses20 {


    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList();
        stack.offerFirst(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            char index = s.charAt(i);
            if (stack.isEmpty()) {
                stack.offerFirst(index);
                continue;
            }
            char top = stack.peekFirst();
            if (top == '(' && index == ')') {
                stack.pop();
                continue;
            }
            if (top == '{' && index == '}') {
                stack.pop();
                continue;
            }
            if (top == '[' && index == ']') {
                stack.pop();
                continue;
            }
            stack.offerFirst(index);
        }
        return stack.isEmpty();

    }
}
