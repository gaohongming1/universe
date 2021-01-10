package com.universe.origin.star.leetcode.heap.medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author gaohongming
 * @version 1.0.0
 * @ClassName SortStrFrequence451.java
 * @Description TODO
 * @createTime 2021年01月10日 20:44:00
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 示例 1:
 *
 * 输入:
 * "tree"
 *
 * 输出:
 * "eert"
 *
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 *
 * 输入:
 * "cccaaa"
 *
 * 输出:
 * "cccaaa"
 *
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 *
 * 输入:
 * "Aabb"
 *
 * 输出:
 * "bbAa"
 *
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *

 */
public class SortStrFrequency451 {
    public static void main(String[] args) {
        SortStrFrequency451 sortStrFrequency451 = new SortStrFrequency451();
        sortStrFrequency451.frequencySort("tree");

    }


    /**
     * 哈希表
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }else {
                map.put(s.charAt(i),1);
            }
        }
        // 进行排序
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return map.get(o2) - map.get(o1);
            }
        });

        for (Map.Entry<Character,Integer> entry:map.entrySet()){
            priorityQueue.add(entry.getKey());
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!priorityQueue.isEmpty()){
            Character character = priorityQueue.poll();
            Integer num = map.get(character);
            for (int i = 0; i < num; i++) {
                stringBuilder.append(character);
            }
        }
        return stringBuilder.toString();

    }



}






























