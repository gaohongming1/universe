package com.universe.origin.star.leetcode.queue.medium;

/**
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 *
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，
 * 即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 *
 * 你的实现应该支持如下操作：
 *
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 */
public class LoopQueue622 {
    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3);
        circularQueue.enQueue(1);
        circularQueue.enQueue(2);
        circularQueue.enQueue(3);
        circularQueue.enQueue(4);
        circularQueue.Rear();
        circularQueue.isFull();
        circularQueue.deQueue();
        circularQueue.enQueue(4);
        circularQueue.Rear();

    }
    static class MyCircularQueue {
        private int[] array;
        private int head;
        private int size;

        public MyCircularQueue(int k) {
            array = new int[k];
            head = 0;
            size = 0;
        }

        public boolean enQueue(int value) {
            if (size == array.length){
                return false;
            }
            size +=1;
            //当前的队尾
            array[(head+size)%array.length] = value;
            size+=1;
            return true;
        }

        public boolean deQueue() {
            if (size == 0){
                return false;
            }

            // 有size==0校验，不会出现出队不存在的
            int index = (head +1) % array.length;
            array[index] = 0;
            size -=1;
            head = index;
            return true;
        }

        public int Front() {
            if (size==0){
                return -1;
            }
            return array[head];
        }

        public int Rear() {
            if (size==0){
                return -1;
            }
            return array[tail];
        }

        public boolean isEmpty() {
            return size==0;
        }

        public boolean isFull() {
            return size==array.length;
        }
    }
}
