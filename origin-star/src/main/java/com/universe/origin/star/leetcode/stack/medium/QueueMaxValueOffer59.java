package com.universe.origin.star.leetcode.stack.medium;

import java.util.*;

/**
 * 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例 1：
 *
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 *
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *
 */
public class QueueMaxValueOffer59 {
    public static void main(String[] args) {

    }


    class MaxQueue {
        private Queue<Integer> queue;
        private Deque<Integer> deque;

        public MaxQueue() {
            deque = new LinkedList<>();
            queue = new LinkedList<>();
            deque.offerLast()
        }

        public int max_value() {
            return stack.peek();

        }

        public void push_back(int value) {
            if (stack.isEmpty()){
                stack.add(value);
                return;
            }
            // 出队一个和放入的元素比较
            int headNum = stack.pop();
            if (headNum>=value){
                stack.add(value);
                stack.add(headNum);
            }else {
                stack.add(headNum);
                stack.add(value);
            }
            queue.add(value);

        }

        public int pop_front() {
            int num =  queue.poll();

            boolean deleteStatus = true;
            //栈不为空并且清除标记还未完成
            while (!stack.isEmpty() && deleteStatus){

            }

            return -1;
        }
    }

}
