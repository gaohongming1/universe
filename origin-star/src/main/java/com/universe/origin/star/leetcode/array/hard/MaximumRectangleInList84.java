package com.universe.origin.star.leetcode.array.hard;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 84. 柱状图中最大的矩形
 */
public class MaximumRectangleInList84 {

    /**
     *求解最大矩形的过程可以通过循环每个元素求解该元素对应的最大宽度   或者循环每个元素的高度
     */

    /**
     * 枚举各个边界值求解
     * 这个暴力枚举每一个区间值 求解区间内能够形成的最大矩形的面积
     */
    public int largestRectangleArea1(int[] heights) {
        int ans = 0;
        int n = heights.length;
        for (int i = 0; i < n; i++) {
            int h = heights[i];
            for (int j = i; j < n; j++) {
                // 求解 i 和j组成的最大矩形
                h = Math.min(h, heights[j]);
                ans = Math.max(ans, h * (j - i + 1));
            }
        }
        return ans;
    }

    /**
     * 枚举矩形的高度  即当前节点在矩形中的话能够达到的最大高度
     * 所以也就需要查找当前节点  i  的左右两边第一个小于i的元素 也就是高度为height[i] 所能组成的最大矩形
     */
    public int largestRectangleArea2(int[] heights) {
        int ans = 0;
        int n = heights.length;
        for (int i = 0; i < n; i++) {
            int left = i;
            int right = i;
            while (left - 1 >= 0 && heights[left - 1] >= heights[i]) {
                left--;
            }
            while (right + 1 <= n - 1 && heights[right + 1] >= heights[i]) {
                right++;
            }
            int w = right - left + 1;
            ans = Math.max(ans, w * heights[i]);
        }
        return ans;
    }

    /**
     * 经过上边的引导  我们可以通过枚举高度来实现求解最大矩形  可以聚焦于怎么查找节点i 左右两边的边界值
     * 证明：从左到右使用一个单调递增栈进行记录
     * 当遍历到元素i的时候  如果i大于栈顶元素，则i加入，并且记录i的左边界是栈顶元素位置  因为栈顶前面的元素肯定是比栈顶元素小的   所以取栈顶为当前元素的左边界
     * 同理求出右边界
     */
    public int largestRectangleArea3(int[] heights) {
        int ans = 0;
        int n = heights.length;
        Deque<Integer> leftStack = new LinkedList<>();
        Deque<Integer> rightStack = new LinkedList<>();
        int[] left = new int[n];
        int[] right = new int[n];
        leftStack.push(0);
        rightStack.push(n-1);
        right[n - 1] = n;
        left[0] = -1;
        // 加工左边的边界值
        for (int i = 1; i < n; i++) {
            if (heights[leftStack.peek()] < heights[i]) {
                left[i] = leftStack.peek();
                leftStack.push(i);
                continue;
            }

            while (!leftStack.isEmpty() && heights[leftStack.peek()] >= heights[i]) {
                leftStack.pop();
            }
            if (leftStack.isEmpty()) {
                left[i] = -1;
            } else {
                left[i] = leftStack.peek();
            }
            leftStack.push(i);
        }

        // 加工右边界值
        for (int i = n - 2; i >= 0; i--) {
            if (heights[rightStack.peek()] < heights[i]) {
                right[i] = rightStack.peek();
                rightStack.push(i);
                continue;
            }

            while (!rightStack.isEmpty() && heights[rightStack.peek()] >= heights[i]) {
                rightStack.pop();
            }
            if (rightStack.isEmpty()) {
                right[i] = n;
            } else {
                right[i] = rightStack.peek();
            }
            rightStack.push(i);
        }

        // 计算每个位置i 组成的矩形的最大值
        for (int i = 0; i < n; i++) {
            int lrange = left[i];
            int rRange = right[i];
            int w = heights[i];
            ans = Math.max(ans, (rRange - lrange -1) * w);
        }
        return ans;
    }

    /**
     *考虑栈的使用
     * 对上面的单调栈继续进行优化
     * 当一个元素出栈的时候   寓意当前遍历的元素  i  小于等于当前元素   那么当前出栈元素的右边界也就确定了是 i  这里求解右边界是小于等于     但是最后一个右边界元素肯定是小于
     * 所以不影响最终的解
     */
    public int largestRectangleArea4(int[] heights) {
        int ans = 0;
        int n = heights.length;
        Deque<Integer> stack = new LinkedList<>();
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        for (int i = 0 ; i <n ; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int index = stack.pop();
                right[index] = i;
            }

            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // 计算每个位置i 组成的矩形的最大值
        for (int i = 0; i < n; i++) {
            int lrange = left[i];
            int rRange = right[i];
            int w = heights[i];
            ans = Math.max(ans, (rRange - lrange -1) * w);
        }
        return ans;

    }
}


























