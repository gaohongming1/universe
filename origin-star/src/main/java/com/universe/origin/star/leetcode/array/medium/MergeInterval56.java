package com.universe.origin.star.leetcode.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间
 * https://leetcode-cn.com/problems/merge-intervals/
 */
public class MergeInterval56 {


    public int[][] merge(int[][] intervals) {
        //先将区间按照结束区间进行升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        // 循环数组 记录当前最大值的区间
        int right = 0;
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            // 判断当前区间的左边界是否小于 right 如果小于则代表可能有交集   并且当前节点的左边界小于等于right
            if (intervals[i][0] <= right && intervals[i][1] >= right) {
                // 进行合并策略
                res = merge(res, intervals[i]);
            } else {
                res.add(intervals[i]);
            }
            // 更新区间的right
            right = intervals[i][1];
        }

        return res.toArray(new int[res.size()][]);
    }

    public List<int[]> merge(List<int[]> res, int[] current) {
        List<int[]> newRes = new ArrayList<>();
        if (res.size() == 0) {
            newRes.add(current);
            return newRes;
        }
        for (int i = 0; i < res.size(); i++) {
            // 如果取出区间的右边界小于当前节点的左边界则不能合并
            if (res.get(i)[1] < current[0]) {
                newRes.add(res.get(i));
            } else {
                // 否则则进行合并
                if (res.get(i)[0]<current[0]){
                    current[0] = res.get(i)[0];
                }
                newRes.add(current);
                break;
            }
        }
        return newRes;
    }
}
