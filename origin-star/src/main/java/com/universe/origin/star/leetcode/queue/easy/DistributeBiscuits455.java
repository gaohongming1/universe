package com.universe.origin.star.leetcode.queue.easy;

import java.util.Arrays;

/**
 * 455. 分发饼干
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 *
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。
 * 如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 *  
 * 示例 1:
 *
 * 输入: g = [1,2,3], s = [1,1]
 * 输出: 1
 * 解释:
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 * 示例 2:
 *
 * 输入: g = [1,2], s = [1,2,3]
 * 输出: 2
 * 解释:
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 *
 */
public class DistributeBiscuits455 {

    public int findContentChildren(int[] g, int[] s) {
        // 升序排序
        Arrays.sort(g);
        Arrays.sort(s);

        int peopleNum = 0;
        int sIndex = 0;
        for (int i = 0; i < g.length; i++) {

            // 跳过不满足的饼干
            while (sIndex<s.length && s[sIndex]<g[i]){
                sIndex +=1;
            }

            //饼干分完了跳出
            if (sIndex>=s.length){
                break;
            }

            peopleNum +=1;
            //当前饼干sIndex分配给i人，饼干后移
            sIndex +=1;
        }
        return peopleNum;
    }
}
