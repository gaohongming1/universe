package com.universe.origin.star.leetcode.array.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 55. 跳跃游戏
 * https://leetcode-cn.com/problems/jump-game/
 * todo 666
 */
public class JumpGames55 {

    Set<Integer> not;

    public boolean canJump(int[] nums) {
        not = new HashSet<>();
        return dfs(nums, 0);
    }

    /**
     * dfs 加上缓存方式
     */
    public boolean dfs(int[] num, int index) {

        // 代表能够直接到达末尾
        if (index >= num.length - 1) {
            return true;
        }

        // 到达非最终节点的0元素则返回false
        if (num[index] == 0 || not.contains(index)) {
            return false;
        }

        //如果当前元素能直接到达末尾则返回
        if (num[index] >= num.length - 1 - index) {
            return true;
        }

        // 从高位置向低位置进行回溯 尽快到达尾部
        for (int i = num[index]; i >= 1; i--) {
            boolean statue = dfs(num, index + i);
            if (statue) {
                return statue;
            }
        }

        // 走到这代表从当前节点出发的路径都是死路径 返回false
        not.add(index);
        return false;
    }

    /**
     * 本质上还是贪心的算法  维护最大可跳跃距离k  改进的贪心算法
     * 也有一些动态规划的思想   如果某个点x 能够到达  那么  num[x] + x 的位置也能到达
     * @param nums
     * @return
     */
    public boolean loop(int[] nums) {
        // k 作为最大的可跳跃距离
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            // i 大于k  代表i之前的最大可跳跃距离k  无法跳到i 则返回false
            if (i > k) {
                return false;
            }
            k = Math.max(k, nums[i] + k);
        }
        return true;
    }


}
