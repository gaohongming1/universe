package com.universe.origin.star.leetcode.stack.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * <p>
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 * <p>
 * 示例 1:
 * <p>
 * 输入: N = 10
 * 输出: 9
 * <p>
 * <p>
 * 输入: N = 1234
 * 输出: 1234
 * <p>
 * 输入: N = 332
 * 输出: 299
 * <p>
 * 暴力法 、 回溯法DFS+剪枝
 * 贪心法：思想是尽量让高位数字和N保持一样，并且满足单调递增。是在不满足了再将前一位对应n的位置减一，但是后续的都可以设置为9保持最大
 */
public class IncreasingNum738 {

    public static void main(String[] args) {
        //IncreasingNum738 increasingNum738 = new IncreasingNum738();
        //System.out.println(increasingNum738.monotoneIncreasingDigits(3768543));
        //System.out.println(increasingNum738.monotoneIncreasingDigits(332));
        IncreasingNum738 increasingNum738 = new IncreasingNum738();
        System.out.println(increasingNum738.greedy(3768543));
    }

    /**
     * 贪心算法
     * 思想是尽量把每一位和N对其，碰到不满足的也就是 i<i-1的情况，则将i-1数字设置为 N的i-1位置的减1， i 到length-1 的数字全部设置为9即可
     *
     * @param N
     * @return
     */
    public int greedy(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        // 找到不符合条件的位置
        while (strN[i] >= strN[i - 1] && i < strN.length) {
            i+=1;
        }

        //如果i没有到终点则设置9
        if (i < strN.length) {
            // 向高位递减直到符合条件
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            //低位补9
            for (int j = i + 1; j < strN.length; j++) {
                strN[j] = '9';
            }

        }
        return Integer.parseInt(new String(strN));

    }


    /**
     * DFS + 剪枝
     *
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits(int N) {
        List<Integer> nums = analyse(N);
        // 初始化第一个节点 循环第一个节点的可行解范围 0<=  第一位 <= N的第一位
        List<Integer> solutionRange = buildSolutionRange(0, nums.get(0));
        int bestValue = 0;
        List<Integer> currentValue = new ArrayList<>();
        for (int i = 0; i < solutionRange.size(); i++) {
            // 从第一位开始每一位开启一条链路
            currentValue.add(0, solutionRange.get(i));
            bestValue = calc(nums, 1, 0, currentValue);
            //找到的第一个解就是最大解
            if (bestValue > 0) {
                return bestValue;
            }
        }
        return bestValue;

    }

    public Integer calc(List<Integer> nums, int index, int bestValue, List<Integer> currentValue) {

        /**
         * 判断终点 即找到一个可行解
         */
        if (index == nums.size()) {
            // 输出最优解
            System.out.println(currentValue.toString());
            return getNumByIndex(currentValue, index - 1);
        }

        /**
         * 找到当前节点可走路径
         * 如果前面的数字相等则需要符合   前一位数字<= 当前选择 <= nums[index]
         * 如果前面的数字小于则符合      前一位数字<= 当前选择 <=9
         */

        //提取当前路径前面的数字
        int currentNum = getNumByIndex(currentValue, index - 1);
        int indexNum = getNumByIndex(nums, index - 1);
        List<Integer> solutionRange;
        if (currentNum == indexNum) {
            // 构造 前一位数字<= 当前选择 <= nums[index]  可行解范围
            solutionRange = buildSolutionRange(currentValue.get(index - 1), nums.get(index));
        } else {
            // 构造 前一位数字<= 当前选择 <=9 可行解范围
            solutionRange = buildSolutionRange(currentValue.get(index - 1), 9);
        }

        // 循环可行解进行递归  从高位向低位进行递归
        for (int i = 0; i < solutionRange.size(); i++) {
            //当前路径这个位置的选择
            currentValue.add(index, solutionRange.get(i));
            bestValue = calc(nums, index + 1, bestValue, currentValue);

            // 找到一个解肯定就是最大解
            if (bestValue > 0) {
                break;
            }
        }
        return bestValue;


    }

    public static List analyse(int N) {
        List<Integer> list = new ArrayList<>();
        // 获得剩下的数字
        int digit = N / 10;
        // 数字从低位到高位 获取各个位置的数字
        int digNum = N % 10;
        list.add(digNum);
        while (digit != 0) {
            digNum = digit % 10;
            digit = digit / 10;
            list.add(digNum);
        }
        Collections.reverse(list);
        return list;
    }

    // integer 低位   integer1 高位
    private List<Integer> buildSolutionRange(Integer integer, Integer integer1) {
        List<Integer> solutionRange = new ArrayList<>();
        // 从高位到低位递归 先走数字高的路径
        for (int i = integer1; i >= integer; i--) {
            solutionRange.add(i);
        }
        return solutionRange;
    }

    private static int getNumByIndex(List<Integer> currentValue, int index) {
        if (index == 0) {
            return currentValue.get(0);
        }
        int num = currentValue.get(0);

        for (int i = 1; i <= index; i++) {
            num = num * 10 + currentValue.get(i);
        }
        return num;
    }

//    public int monotoneIncreasingDigits(int N) {
    // 暴力法挨个递减判断
//        int index = 0;
//        boolean flag = true;
//        while (flag) {
//            // 递减index
//            boolean isLast = true;
//            int currentNum = N - index;
//            index++;
//
//            // 获得剩下的数字
//            int digit = currentNum / 10;
//            // 数字从低位到高位 获取各个位置的数字
//            int digNum = currentNum % 10;
//            // 判断current是否满足条件 拆分
//            List<Integer> nums = new ArrayList<>();
//            nums.add(digNum);
//            while (digit != 0) {
//                digNum = digit % 10;
//                digit = digit / 10;
//                nums.add(digNum);
//            }
//            //判断数字是不是递减的
//            for (int i = 0; i < nums.size() - 1; i++) {
//                if (nums.get(i) < nums.get(i + 1)) {
//                    isLast = false;
//                    break;
//                }
//            }
//
//            // 如果不符合则继续向下寻找
//            if (isLast) {
//                return currentNum;
//            }
//
//        }
//    }
}
