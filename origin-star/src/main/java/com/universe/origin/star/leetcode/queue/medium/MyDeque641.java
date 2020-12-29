package com.universe.origin.star.leetcode.queue.medium;

/**
 * 设计实现双端队列。
 * 你的实现需要支持以下操作：
 * <p>
 * MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * <p>
 * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * <p>
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * <p>
 * isEmpty()：检查双端队列是否为空。
 * isFull()：检查双端队列是否满了。
 */
public class MyDeque641 {

    public static void main(String[] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(77);
        circularDeque.insertFront(89);
        circularDeque.getRear();
        circularDeque.deleteLast();
        circularDeque.getRear();
        circularDeque.insertFront(19);


    }


    static class MyCircularDeque {
        private int[] array;
        private int head;
        private int tail;

        private int capacity;

        /**
         * Initialize your data structure here. Set the size of the deque to be k.
         */
        public MyCircularDeque(int k) {
            array = new int[k + 1];
            head = 0;
            tail = 0;
            capacity = k + 1;

        }

        /**
         * Adds an item at the front of Deque. Return true if the operation is successful.
         */
        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            // 队头插入挪动队头
            int index = (head - 1 + capacity) % capacity;
            array[index] = value;
            head = index;
            return true;

        }

        /**
         * Adds an item at the rear of Deque. Return true if the operation is successful.
         */
        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            array[tail] = value;
            // 队尾挪到下一个位置，为了判断是不是队满
            tail = (tail + 1) % capacity;
            return true;

        }

        /**
         * Deletes an item from the front of Deque. Return true if the operation is successful.
         */
        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }

            //队头前移
            int index = (head + 1) % capacity;
            head = index;
            return true;

        }

        /**
         * Deletes an item from the rear of Deque. Return true if the operation is successful.
         */
        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            int index = (tail - 1 + capacity) % capacity;
            array[index] = 0;
            tail = index;
            return true;

        }

        /**
         * Get the front item from the deque.
         */
        public int getFront() {
            if (isEmpty()) {
                return -1;
            }

            return array[head];

        }

        /**
         * Get the last item from the deque.
         */
        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return array[(tail - 1 + capacity) % capacity];
        }

        /**
         * Checks whether the circular deque is empty or not.
         */
        public boolean isEmpty() {
            return head == tail;

        }

        /**
         * Checks whether the circular deque is full or not.
         */
        public boolean isFull() {
            return (tail + 1) % capacity == head;
        }
    }


}
