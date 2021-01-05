package com.universe.origin.star.leetcode.heap.easy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * <p>
 * 请实现 KthLargest 类：
 * <p>
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 返回当前数据流中第 k 大的元素。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 * <p>
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 */
public class TheKthNum703 {

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(2,new int[]{0});
        kthLargest.add(-1);
        kthLargest.add(1);
        kthLargest.add(-2);
        kthLargest.add(-4);
        kthLargest.add(3);
    }

    static class KthLargest {
        // 小根堆
        private PriorityQueue<Integer> priorityQueue2 = new PriorityQueue();
        // 大根堆
        private PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        private int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            Arrays.sort(nums);

            int len = nums.length;
            int index = len>=k?len-k-1:-1;

            // 小根堆维护前K大的元素
            for (int i = nums.length-1; i >index; i--) {
                priorityQueue2.add(nums[i]);
            }
            // 大根堆维护剩余的元素
            if (k < nums.length){
                for (int i = 0; i <=index; i++) {
                    priorityQueue.add(nums[i]);
                }
            }
            System.out.println("初始化完成");

        }

        public int add(int val) {
            // 如果val小于大顶堆的堆顶 则大顶堆堆顶移除到小顶堆
            int num;
            if (priorityQueue2.size() < k){
                priorityQueue2.add(val);
                return priorityQueue2.element();
            }

            if (val > priorityQueue2.element()){
                // 当前第k大元素维护到大顶堆
                priorityQueue.add(priorityQueue2.poll());
                priorityQueue2.add(val);
                num = priorityQueue2.element();
            }else {
                // 大顶堆堆顶元素先弹出
                priorityQueue.add(val);
                num = priorityQueue2.element();
            }
            return num;
        }
    }
}
