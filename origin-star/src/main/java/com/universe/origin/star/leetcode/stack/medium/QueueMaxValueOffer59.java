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
        //正常序列
        private Queue<Integer> queue;
        //维护正常队列中如果出对不对递减序列造成影响的序列
        private Deque<Integer> deque;

        public MaxQueue() {
            deque = new LinkedList<>();
            queue = new LinkedList<>();
        }

        public int max_value() {
            if (deque.isEmpty()){
                return -1;
            }
            return deque.peekFirst();
        }

        public void push_back(int value) {
            //维护递减序列
            while(!deque.isEmpty() && value > deque.peekLast()){
                //双端队列队尾出队
                deque.pollLast();
            }

            deque.offerLast(value);
            queue.offer(value);

        }

        public int pop_front() {
            if (queue.isEmpty()){
                return -1;
            }
            //队列出队一个
            int n = queue.poll();
            if (n == deque.peekFirst()){
                deque.pollFirst();
            }
            return n;
        }
    }

}
