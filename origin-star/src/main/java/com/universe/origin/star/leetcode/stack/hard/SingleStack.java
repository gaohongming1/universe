package com.universe.origin.star.leetcode.stack.hard;

import java.util.Objects;
import java.util.Stack;

/**
 * 单调栈练习
 * 单调栈分为单调递增栈和单调递减栈
 *通过单调栈可以访问到下一个比他大（小的元素）可以再o(1)的情况下返回
 */
public class SingleStack {
    private Stack<Integer> stack;

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
    }

    /**
     * 单调递增入栈 保持栈顶元素最大
     */
    public void addWithRemove(Integer num){
        if (Objects.isNull(stack)){
            stack = new Stack<>();
        }

        //
        if (stack.isEmpty()){
            stack.add(num);
        }else {
            while (stack.peek()>num){
                stack.pop();
            }
            stack.add(num);
        }
    }
}
