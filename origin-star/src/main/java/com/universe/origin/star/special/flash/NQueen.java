package com.universe.origin.star.special.flash;

import java.util.*;
import java.util.stream.Collectors;

/**
 * N皇后问题
 * N*N大小的棋盘，防止N个不能互相攻击的皇后，求所有的放法
 * 使用数组记录第i个旗子的放置位置
 * 使用list记录所有的结果
 */
public class NQueen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        //记录所有的结果
        List<List<Integer>> allResult = new ArrayList<>();
        //记录当前流程节点状态
        int[] currentValue = new int[n];
        calc(allResult,currentValue,0,n);
        System.out.println(allResult.toString());

    }

    /**
     * 放旗子
     *
     * @param allResult
     * @param currentValue
     * @param i
     */
    public static void calc(List<List<Integer>> allResult, int[] currentValue, int i, int n) {

        // 搜索结束 i大于数组的长度
        if (i > n - 1) {
            //代表找到一种可能解加入到所有的解之中
            allResult.add(Arrays.stream(currentValue).boxed().collect(Collectors.toList()));
        }

        /**
         * 搜索过程 就是判断当前列是否能够存放
         */
        for (int j = 0; j < n; j++) {
            if (isOK(currentValue, i, j, n)) {
                // 代表当前点可以放
                currentValue[i] = j;
                // 递归进行下一行的存放
                calc(allResult, currentValue, i + 1, n);
            }
        }

    }

    /**
     * 判断当前位置是否能够放置棋子
     *
     * @param currentValue
     * @param i            当前棋子索引
     * @param j            列
     * @param n            总大小
     * @return
     */
    private static boolean isOK(int[] currentValue, int i, int j, int n) {
        //找到是否和当前节点同列或者同斜线的 循环每一行
        for (int k = 0; k < i; k++) {
            //找同列
            if (currentValue[k] == j) {
                return false;
            }
            //获得第k行的l列 对角线左侧
            int l = j - (i - k);
            int r = j - l + j;
            //判断是不是在一个横线上
            if (currentValue[k] == l || currentValue[k] == r) {
                return false;
            }
        }

        return true;
    }
}
