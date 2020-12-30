package com.universe.origin.star.leetcode.queue.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。
 *
 * 如果没有和至少为 K 的非空子数组，返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = [1], K = 1
 * 输出：1
 * 示例 2：
 *
 * 输入：A = [1,2], K = 4
 * 输出：-1
 * 示例 3：
 *
 * 输入：A = [2,-1,2], K = 3
 * 输出：3
 *
 */
public class SumKArray862 {
    public static void main(String[] args) {
        SumKArray862 sumKArray862 = new SumKArray862();
        //System.out.println(sumKArray862.shortestSubarray(new int[]{2,-1,2},3));
        System.out.println(sumKArray862.shortestSubarray3(new int[]{1},1));

    }

    /**
     * 官方解法
     * 双端队列
     * @param A
     * @param K
     * @return
     */
    public int shortestSubarray3(int[] A, int K) {
        int N = A.length;
        int[]p = new int[N+1];
        for (int i = 0; i < N; i++) {
            p[i+1] = A[i] + p[i];
        }

        //维护y 到某个x 满足 p[y] - p[x] >= K
        Deque<Integer> deque = new LinkedList<>();
        int ans = N+1;
        for (int y = 0; y < p.length; y++) {
            /**
             * 为什么要维护单调递增队列？
             * 因为如果存在 x1   x2   如果p[x2] < p[x1]  那么如果当前p[y] - p[x1] 满足大于k
             * 那么p[y] - p[x2]必定大于k 所以不需要x1  只需要维护单点递增队列就行了
             *
             */
            while (!deque.isEmpty() && p[y] <= p[deque.getLast()]){
                deque.removeLast();
            }
            // 找到满足的点
            while (!deque.isEmpty() && p[y] >= p[deque.getFirst()] + K){
                // 头结点X 到Y 之间的和大于等于K   查看是否是当前的最优解。是单调递增的
                ans = Math.min(ans,y - deque.removeFirst());
            }
            deque.addLast(y);
        }

        return ans==A.length+1 ? -1: ans;

    }


    /**
     * death fuck 不对但是可以改进
     * @param A
     * @param K
     * @return
     */
    public int shortestSubarray2(int[] A, int K) {
        boolean status = false;
        for (int i = A.length; i >0 ; i--) {

            //当前窗口大小从最高开始递减
            int loopValue = 0;
            int index = 0;

            for (int j =0; j < A.length; j++) {

                //减去窗口前面的
                if (index == i){
                    loopValue = loopValue - A[j-index];
                    index = index-1;
                }
                loopValue = loopValue + A[j];
                //窗口大小加1
                index = index+1;

                //找到一个大于K的解范围，那么子集肯定包含最优解
                if (loopValue >= K){

                    int[] temp = new int[index];
                    int m = 0;
                    for (int k = j-index+1; k <= j; k++) {
                        temp[m] = A[k];
                        m +=1;
                    }
                    // 下一个解从当前解中查找
                    status = true;
                    A = temp;
                    i = index;
                    break;
                }
            }
            //如果根据当前窗口遍历完了还没达到K则代表i+1是最小的窗口
            if (loopValue<K && index < A.length && status){
                return i+1;
            }
            if (A.length ==1 && loopValue>=K){
                return 1;
            }
        }
        return -1;
    }

    /**
     * 解法超时  暴力法
     * @param A
     * @param K
     * @return
     */
    public int shortestSubarray(int[] A, int K) {
        int currentValue = 0;
        for (int i = 1; i <= A.length; i++) {
            //当前窗口大小是i
            Deque<Integer> deque = new LinkedList<>();
            int loopValue = 0;
            for (int j =0; j < A.length; j++) {
                if (deque.size() == i){
                    //出队一个
                    loopValue = loopValue - deque.removeFirst();
                }
                loopValue = loopValue + A[j];
                deque.addLast(A[j]);

                if (loopValue>=K){
                    return i;
                }
            }

        }
        return -1;
    }
}
