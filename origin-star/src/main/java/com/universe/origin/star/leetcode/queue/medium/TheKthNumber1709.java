package com.universe.origin.star.leetcode.queue.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，
 * 而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 *
 * 示例 1:
 *
 * 输入: k = 5
 *
 * 输出: 9
 *
 *
 */
public class TheKthNumber1709 {
    public static void main(String[] args) {
        TheKthNumber1709 theKthNumber1709 = new TheKthNumber1709();
        //theKthNumber1709.getKthMagicNumber(251);
        theKthNumber1709.getKthMagicNumber2(20);
    }


    /**
     * 采用三指针的方式
     * 下一个数是前面的数乘以3  5  7 得来的
     * 也是动态规划的思想  后边的结果由前边的结果获得
     * @param k
     * @return
     */
    public int getKthMagicNumber2(int k) {
        int[] array = new int[k];
        array[0] = 1;
        int p1=0;
        int p2=0;
        int p3=0;
        for (int i = 1; i < k; i++) {
            int num = Math.min(Math.min(array[p1] *3,array[p2]*5),array[p3]*7);
            array[i] = num;
            if (num == array[p1]*3){
                p1+=1;
            }
            if (num == array[p2]*5){
                p2+=1;
            }
            if (num == array[p3]*7){
                p3+=1;
            }
        }
        return array[k-1];
    }



    /**
     * 这种解法超时 不能一个个判断
     * @param k
     * @return
     */
    public int getKthMagicNumber(int k) {
        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(1);
        while (deque.size()<k){
            //从1累加找素因子
            for (int i = 3; i < Integer.MAX_VALUE; i++) {
                int n = i;
                boolean status = true;
                List<Integer> list = new ArrayList<>();
                for (int j = 2; j <= n; j++) {
                    if (n % j == 0){
                        n /= j;
                        list.add(j);
                        if (j!=3 && j!=7 && j!=5){
                            status = false;
                            break;
                        }
                        j--;
                    }
                }
                if (status){
                    deque.addLast(i);
                    if (deque.size() == k){
                        break;
                    }
                }
            }
        }
        return deque.getLast();
    }

}
