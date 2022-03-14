package com.universe.origin.star.leetcode.array.medium;

/**
 * 盛水最多的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class MostWaterHolding11 {
    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 4, 5, 18, 17, 6};
        MostWaterHolding11 mostWaterHolding11 = new MostWaterHolding11();
        System.out.println(mostWaterHolding11.maxArea(array));
    }

    /**
     * 这个没有办法 循环找到所有的解 找到最大值
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {

            for (int j = height.length - 1; j > i; j--) {
                if (height[i] * (j - i) <= max) {
                    break;
                }

                int minEdge = Math.min(height[i], height[j]);
                int res = (j - i) * minEdge;
                if (res > max) {
                    max = res;
                }
            }
        }
        return max;
    }

    /**
     * 采用双指针的方法
     *
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int max = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            if (area > max) {
                max = area;
            }

            //这一步的意思就是讲边比较短的向另一边移动以追求得到更大的解  如果
            if (height[l] < height[r]) {
                l++;
            }else {
                r--;
            }
        }

        return max;
    }


}
