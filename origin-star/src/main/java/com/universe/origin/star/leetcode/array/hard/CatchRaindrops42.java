package com.universe.origin.star.leetcode.array.hard;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 接雨滴42
 */
public class CatchRaindrops42 {

    public static void main(String[] args) {
        int[] arr = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        CatchRaindrops42 catchRaindrops42 = new CatchRaindrops42();
        catchRaindrops42.trap2(arr);
    }


    /**
     * 分析
     * 假设当前单元是i  则i位置能够借助的容量是i左边和右边元素的 最大值中的最小值x   x-hight[i]
     */

    /**
     * 动态规划方式
     * 使用两个数组来存储 从左边向右的 每个位置  i  【0，i】区间的最大值   以及[i,0] 的最大值
     * 然后遍历所有元素 求解每个位置能够承接的雨水量
     */
    public int trap(int[] height) {
        if (height.length <= 1) {
            return 0;
        }
        int n = height.length - 1;
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        left[0] = height[0];
        right[n - 1] = height[n - 1];

        for (int i = 1; i <= n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        for (int i = n-1; i >=0 ; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        // 为每个元素求解当前元素能够接到的雨水量
        int ans = 0;
        for (int i = 0; i <= n ; i++) {
            int area = Math.min(left[i], right[i]) - height[i];
            ans += area;
        }
        return ans;
    }

    /**
     * 由动态规划可以察觉到 求解每个元素左侧最大值的过程就是一个单调栈
     * 使用单调栈来进行求解 从栈底到栈顶元素依次递减   当遇到大于栈顶的元素的时候弹出栈顶  计算栈顶元素可以承接的雨水量  当栈为空或者是到最后一个元素则求解完毕
     *
     */
    public int trap2(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 采用双指针的方式 同样是动态规划的思想 使用两个指针动态的维护当前元素的最大值
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        int ans = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[0];
        int rightMax = height[height.length - 1];

        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            // 出现这种情况则代表肯定存在 leftMax < rightMax   则当前元素能够承接的雨水量 = leftMax - height[left]
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                left++;
            }else {
                ans += rightMax - height[right];
                right--;
            }
        }

        return ans;
    }














}
