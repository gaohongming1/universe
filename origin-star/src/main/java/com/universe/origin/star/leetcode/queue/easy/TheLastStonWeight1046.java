package com.universe.origin.star.leetcode.queue.easy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 *
 *  
 *
 * 示例：
 *
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
 * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
 * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
 * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
 *
 */
public class TheLastStonWeight1046 {
    public static void main(String[] args) {
        TheLastStonWeight1046 theLastStonWeight1046 = new TheLastStonWeight1046();
        theLastStonWeight1046.lastStoneWeight(new int[]{2,7,4,1,8,1});
    }

    /**
     * 使用最大堆维护
     * @param stones
     * @return
     */
    public int lastStoneWeight2(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int i = 0; i < stones.length; i++) {
            pq.offer(stones[i]);
        }
        while (pq.size()>1){
            int x = pq.poll();
            int y = pq.poll();
            int temp = x-y;
            if (temp!=0){
                pq.offer(temp);
            }
        }

        return pq.size()==0? 0:pq.poll();


    }


    /**
     * 暴力法
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        // 递增排序
        Arrays.sort(stones);
        for (int i = stones.length-1; i >0; i--) {
            // 取出第i个
            int x = stones[i];
            int y = stones[i-1];
            int temp = x -y;
            stones[i] = 0;
            stones[i-1] = temp;

            // 移动数组
            for (int j = i-2; j >=0 ; j--) {
                if (stones[j] > temp){
                    stones[j+1] = stones[j];
                    stones[j] = temp;
                }
            }
        }
        return stones[0];

    }
}
