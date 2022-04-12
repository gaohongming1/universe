package com.universe.origin.star.leetcode.array.medium;

/**
 * 300. 最长递增子序列
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence300 {

    public static void main(String[] args) {

    }

    /**
     * 采用回溯判断每个元素加入和不加入的结果
     *
     * @param nums
     * @return
     */
    private int max = 0;

    public int lengthOfLIS(int[] nums) {
        dfs(nums, 0, 0, Integer.MIN_VALUE);
        return max;
    }

    /**
     * 相当于暴力解法了 超时
     */
    public void dfs(int[] nums, int index, int count, int before) {
        if (index > nums.length - 1) {
            return;
        }

        int temp = before;
        // 当前节点大于前一个节点则可以走加入路径
        if (nums[index] > before) {
            count++;
            max = Math.max(count, max);
            temp = nums[index];
            // 剪枝函数 剩余元素数量+当前累计值  小于目前计算的最大值则返回
            if (((nums.length - index - 1) + count) <= max) {
                return;
            }

            dfs(nums, index + 1, count, temp);
            count--;
        }

        // 走不加入
        // 剪枝函数 剩余元素数量+当前累计值  小于目前计算的最大值则返回
        if (((nums.length - index - 1) + count) <= max) {
            return;
        }
        dfs(nums, index + 1, count, before);
    }


    /**
     * 贪心方法  +二分
     * 需要证明 存在一个数组d[i] 记录以i为结尾的最长递增公共子序列的长度
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];

        for (int i = 1; i < n; ++i) {

            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {

                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

    /**
     * 动态规划方式
     * 以第i个元素为结尾的最长公共子序列的 长度 = max( 1 - (i-1) 区间最长公共子序列 +1) 但是能累加的前提是num[i] 大于那个元素  如果没有能够累加的则代表当前原数是最大的
     */
    public int lengthOfLIS3(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i <nums.length; i++) {
            dp[i] = 1;
            for (int j = i-1; j >=0 ; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

}
