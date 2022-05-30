package com.universe.origin.star.leetcode.string.medium;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 字符串解码
 */
public class StringDecoding394 {
    public static void main(String[] args) {
        String s = "100[leetcode]";
        StringDecoding394 stringDecoding394 = new StringDecoding394();
        stringDecoding394.decodeString(s);
    }


    public String decodeString(String s) {
        StringBuilder str = new StringBuilder();
        Deque<String> deque = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ']') {
                deque.offerFirst(s.substring(i, i + 1));
            } else {
                // 出栈
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder accuStr = new StringBuilder();

                while (!deque.peekFirst().equals("[")) {
                    stringBuilder.insert(0,deque.pop());
                }
                //再将[出栈
                deque.pop();
                // 出栈数字
                StringBuilder num = new StringBuilder();
                while (!deque.isEmpty() && Character.isDigit(deque.peek().charAt(0))) {
                    num.insert(0,deque.pollFirst());
                }
                int count = Integer.valueOf(num.toString());
                for (int j = 0; j < count; j++) {
                    accuStr.append(stringBuilder.toString());
                }
                deque.offerFirst(accuStr.toString());
            }
        }
        while (!deque.isEmpty()) {
            str.insert(0, deque.pop());
        }

        return str.toString();
    }
}
