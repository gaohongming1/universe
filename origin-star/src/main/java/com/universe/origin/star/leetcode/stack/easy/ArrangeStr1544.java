package com.universe.origin.star.leetcode.stack.easy;

import java.util.Stack;

/**
 * 给你一个由大小写英文字母组成的字符串 s 。
 *
 * 一个整理好的字符串中，两个相邻字符 s[i] 和 s[i+1]，其中 0<= i <= s.length-2 ，要满足如下条件:
 *
 * 若 s[i] 是小写字符，则 s[i+1] 不可以是相同的大写字符。
 * 若 s[i] 是大写字符，则 s[i+1] 不可以是相同的小写字符。
 * 请你将字符串整理好，每次你都可以从字符串中选出满足上述条件的 两个相邻 字符并删除，直到字符串整理好为止。
 *
 * 请返回整理好的 字符串 。题目保证在给出的约束条件下，测试样例对应的答案是唯一的。
 *
 * 注意：空字符串也属于整理好的字符串，尽管其中没有任何字符。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "leEeetcode"
 * 输出："leetcode"
 * 解释：无论你第一次选的是 i = 1 还是 i = 2，都会使 "leEeetcode" 缩减为 "leetcode" 。
 * 示例 2：
 *
 * 输入：s = "abBAcC"
 * 输出：""
 * 解释：存在多种不同情况，但所有的情况都会导致相同的结果。例如：
 * "abBAcC" --> "aAcC" --> "cC" --> ""
 * "abBAcC" --> "abBA" --> "aA" --> ""
 * 示例 3：
 *
 * 输入：s = "s"
 * 输出："s"
 *

 */
public class ArrangeStr1544 {
    public static void main(String[] args) {
        System.out.println('a' == 'A');
        System.out.println('A' - 'a');
        System.out.println('a' - 'A');
        System.out.println('b' - 'B');
        System.out.println();
    }

    public String makeGood(String s) {
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            stack2.add(s.charAt(i));
        }

        //入队到栈1一个
        stack1.add(stack2.pop());
        while (!stack2.isEmpty()){
            // 如果栈1为空则从栈2出栈一个到栈1
            if (stack1.isEmpty()){
                stack1.add(stack2.pop());
            }

            // 每个栈各出队一个对比
            char s1 = stack1.pop();
            char s2 = stack2.pop();
            if (s1-s2 ==32 || s1-s2==-32){
                System.out.println(s1 + "和" + s2 + "出队");
            }else {
                //不相等则将当前对比元素入栈1
                stack1.add(s1);
                stack1.add(s2);
            }

        }
        StringBuffer stringBuffer = new StringBuffer();
        while (!stack1.isEmpty()){
            stringBuffer.append(stack1.pop());
        }
        return stringBuffer.toString();
    }
}
