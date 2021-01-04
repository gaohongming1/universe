package com.universe.origin.star.leetcode.queue.easy;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *线段树、ST算法
 */
public class MaxSuborderSum53 {
    public static void main(String[] args) {
        int[] array = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        MaxSuborderSum53 maxSuborderSum53 = new MaxSuborderSum53();
        maxSuborderSum53.maxSubArray2(array);

    }

    /**
     * 二分法求解 使用线段树  ST算法
     * @param nums
     * @return
     */
    public int maxSubArray3(int[] nums) {
        return -1;
    }

    static class Status{
        // 以l为左端点的最大子段和 最大  l.lSum   l.iSum + r.lSum
        private int lSum;
        // 以r为右端点的最大子段和   最大 r.rSum  r.iSum + l.rSum
        private int rSum;
        // lr内的最大子段和 = 最大：l.mSum   r.mSum   l.mSum+r.mSum
        private int mSum;
        //lr内的区间和  = l.iSum + r.iSum
        private int iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }

        public Status getInfo(int[] arr,int l , int r){
            if (l == r){
                return new Status(arr[0],arr[0],arr[0],arr[0]);
            }
            int m = (l+r)>>1;
            Status lSum = getInfo(arr,l,m);
            Status rSum = getInfo(arr,m+1,r);
            return pushUp(lSum,rSum);
        }

        public Status pushUp(Status l, Status r) {
            int iSum = l.iSum + r.iSum;
            int lSum = Math.max(l.lSum, l.iSum + r.lSum);
            int rSum = Math.max(r.rSum, r.iSum + l.rSum);
            int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
            return new Status(lSum, rSum, mSum, iSum);
        }


    }


    /**
     * 动态规划的方法求解 当前一段加上当前元素还没有当前元素大呢就把前一段舍弃 从当前元素继续累加
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int bestValue = nums[0];
        int finalBestValue = nums[0];
        int m = nums.length;
        for (int i = 1; i < m; i++) {
            bestValue = Math.max(bestValue + nums[i],nums[i]);
            finalBestValue = Math.max(bestValue,finalBestValue);
        }
        return finalBestValue;
    }





    /**
     * 前缀和 187 / 202 个通过测试用例 不对
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        if (nums.length ==1){
            return nums[0];
        }
        int n = nums.length;
        int[] numsPre = new int[n];
        numsPre[0] = nums[0];

        int minTemp2 = nums[0];
        for (int i = 1; i < n; i++) {
            numsPre[i] = nums[i] + numsPre[i-1];
            if (minTemp2 < nums[i]){
                minTemp2 = nums[i];
            }
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int maxIndex = 0;
        int minIndex = 0;
        // 找到最小值和最大值
        int minTemp = numsPre[0];
        for (int i = 0; i < n; i++) {

            if (minTemp < numsPre[i]){
                minTemp = numsPre[i];
            }

            if (numsPre[i] < min){
                min = numsPre[i];
                minIndex = i;
            }

            if (numsPre[i] > max){
                max = numsPre[i];
                maxIndex = i;
            }
        }

        int temp2 = maxIndex>minIndex?  max - min:min-max;

        return  Math.max(minTemp2,Math.max(temp2,minTemp));
    }

}
