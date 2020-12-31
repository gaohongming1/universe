package com.universe.origin.star.leetcode.queue.medium;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * 输出: 1
 *
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: [ [1,2], [1,2], [1,2] ]
 *
 * 输出: 2
 *
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: [ [1,2], [2,3] ]
 *
 * 输出: 0
 *
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 *
 */
public class NotRepeatSection435 {
    public static void main(String[] args) {
        int[][] ints = new int[][]{{1,2},{2,3}};
        NotRepeatSection435 notRepeatSection435 = new NotRepeatSection435();
        notRepeatSection435.eraseOverlapIntervals2(ints);

    }

    /**
     * 使用动态规划方法
     * 1、先按照左端点进行排序
     * 状态转移方程：fi = fi-1  + 1
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length<=1){
            return 0;
        }
        int n = intervals.length;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        //初始化dp表
        int[] dp = new int[n];
        Arrays.fill(dp,1);

        //开始补充dp表
        for (int i = 1; i < n ; i++) {
            for (int j = 0; j < i; j++) {
                // 代表j区间可以加入 拥有i区间的结果
                if (intervals[j][1] <= intervals[i][0]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        return n - Arrays.stream(dp).max().getAsInt();
    }


    /**
     * 贪心法
     * 按照左节点排序，先进性一遍过滤，同七点的必定重复，则选择挪走长度最长的
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length<=1){
            return 0;
        }
        int n = intervals.length;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 如果相等则根据末尾排序
                if (o1[1] == o2[1]){
                    //右端点相同的看左端点，左端点大的靠前
                    return o2[0] - o1[0];
                }
                //根据右端点进行升序排序
                return o1[1]-o2[1];
            }
        });

        //队列记录下标
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (deque.size()==0 || intervals[deque.getLast()][1] != intervals[i][1]){
                deque.addLast(i);
            }
        }

        //对剩下的不满足 下一个端点的左端点不大于上一个端点的右端点的移除

        int[][] nums = new int[deque.size()][2];
        int length = deque.size();
        for (int i = 0; i < length; i++) {
            nums[i] = intervals[deque.removeFirst()];
        }

        //出队完了继续入队
        deque.addLast(0);
        for (int i = 1; i < length; i++) {
            if (nums[deque.getLast()][1] <= nums[i][0] ){
                deque.addLast(i);
            }
        }

        return intervals.length - deque.size();
    }
}
