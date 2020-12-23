package com.universe.origin.star.leetcode.stack.hard;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 *
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 *
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 *
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 *
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 *
 */
public class RemoveKdigits402 {
    public static void main(String[] args) {
        RemoveKdigits402 removeKdigits402 = new RemoveKdigits402();
        System.out.println(removeKdigits402.removeKdigits("112",1));
    }
    /**
     * 使用单调栈 保证栈底的元素最大并且其过程只能删除三个元素
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        Deque<Integer> stack = new LinkedList<>();
        if (num.length() == k || num.length() == 0){
            return "0";
        }
        char[] numStrs = num.toCharArray();
        int deleteNum = 0;
        // 第一个元素入栈
        stack.push(numStrs[0] - '0');
        for (int i = 1; i < numStrs.length; i++) {
            Integer currentNum = numStrs[i] - '0';
            while (deleteNum < k &&!stack.isEmpty() && stack.getLast() > currentNum){
                stack.removeLast();
                deleteNum +=1;
            }

            if (stack.size() == num.length()-k){

            }
            stack.add(currentNum);
        }
        StringBuffer stringBuffer = new StringBuffer();
        //去0
        while (!stack.isEmpty() && stack.getFirst() ==0){
            stack.removeFirst();
        }
        // 去尾
        while (!stack.isEmpty() && stack.size()>(num.length()-k)){
            stack.removeLast();
        }
        if (stack.isEmpty()){
            return "0";
        }

        while (!stack.isEmpty()){
            stringBuffer.append(stack.pop());
        }
        return stringBuffer.toString();

    }
}
