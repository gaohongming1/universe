package com.universe.origin.star.leetcode.stack.easy;

import java.util.Stack;

/**
 * 栈实现队列 剑指offer
 */
public class StackAchieveQueue09 {
    public static void main(String[] args) {

    }


    class CQueue {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void appendTail(int value) {
            // 如队 直接在stack1 添加就行
            stack1.add(value);
        }

        public int deleteHead() {
            //当stack2中为空的时候将stack1的数据挪到stack2
            if (!stack2.isEmpty()){
                return stack2.pop();
            }else {
                while (!stack1.isEmpty()){
                    stack2.add(stack1.pop());
                }
                if (!stack2.isEmpty()){
                    return stack2.pop();
                }else {
                    return -1;
                }
            }
        }
    }

}
