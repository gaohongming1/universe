package com.universe.origin.star.leetcode.heap.medium;

import java.util.*;

/**
 * @author gaohongming
 * @version 1.0.0
 * @ClassName RebuildStr767.java
 * @Description TODO
 * @createTime 2021年01月10日 20:59:00
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * <p>
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: S = "aab"
 * 输出: "aba"
 * 示例 2:
 * <p>
 * 输入: S = "aaab"
 * 输出: ""
 * 注意:
 * <p>
 * S 只包含小写字母并且长度在[1, 500]区间内。
 * todo 根据题解优化  下标
 */
public class RebuildStr767 {

    public static void main(String[] args) {
        RebuildStr767 rebuildStr767 = new RebuildStr767();
        rebuildStr767.reorganizeString("vvvlo");
    }

    /**
     * 采用贪心的策略，每次提取剩余元素最多的
     *
     * @param
     * @return
     */
    public String reorganizeString(String S) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            if (countMap.containsKey(S.charAt(i))) {
                countMap.put(S.charAt(i), countMap.get(S.charAt(i)) + 1);
            } else {
                countMap.put(S.charAt(i), 1);
            }
        }

        // 大根堆维护剩余的最多的元素
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return countMap.get(o2) - countMap.get(o1);
            }
        });

        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            priorityQueue.add(entry.getKey());
        }

        StringBuilder stringBuilder = new StringBuilder();

        while (!priorityQueue.isEmpty()) {

            Character character1 = priorityQueue.poll();
            // 判断是不是和左边的元素一致，如果一致则继续取出
            Character character2 = null;
            // 剩下最后一个元素的时候，其剩余数量肯定也是1 否则返回“”
            if (priorityQueue.isEmpty() ) {
                if (countMap.get(character1) == 1){
                    return stringBuilder.append(character1).toString();
                }else {
                    return "";
                }
            }else {
                character2 = priorityQueue.poll();
            }

            // 当前取出的不等于左边的
            if (stringBuilder.length() >= 1 && character1.equals(stringBuilder.charAt(stringBuilder.length() - 1))) {
                stringBuilder.append(character2);
                stringBuilder.append(character1);
            } else {
                stringBuilder.append(character1);
                stringBuilder.append(character2);
            }
            // 加入的元素只有是剩余数量大于0才加入
            if (countMap.get(character2) > 1) {
                countMap.put(character2, countMap.get(character2) - 1);
                priorityQueue.add(character2);
            }

            if (countMap.get(character1) > 1){
                countMap.put(character1, countMap.get(character1) - 1);
                priorityQueue.add(character1);
            }
        }
        return stringBuilder.toString();


    }
}
