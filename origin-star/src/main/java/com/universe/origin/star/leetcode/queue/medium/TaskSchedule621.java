package com.universe.origin.star.leetcode.queue.medium;

import java.util.*;

/**
 * 621. 任务调度器
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，
 * 并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * <p>
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * <p>
 * 你需要计算完成所有任务所需要的 最短时间 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
 * 在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 * 示例 2：
 * <p>
 * 输入：tasks = ["A","A","A","B","B","B"], n = 0
 * 输出：6
 * 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * 诸如此类
 * 示例 3：
 * <p>
 * 输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * 输出：16
 * 解释：一种可能的解决方案是：
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 *
 *
 *
 * 待优化
 */
public class TaskSchedule621 {

    public static void main(String[] args) {
        TaskSchedule621 taskSchedule621 = new TaskSchedule621();
        taskSchedule621.leastInterval(new char[]{'A','A','A','B','B','B', 'C','C','C', 'D', 'D', 'E'},2);
    }


    /**
     * 贪心 + 矩阵
     * 宽度为窗口大小+1  长度是最多的任务长度  然后将任务按列向下排列
     * 这时候分为两种情况
     * 最大时间   =  （n+1）* （maxTaskNum - 1） + 长度最大任务的并列数量
     * 当任务种类大于窗口大小的时候
     * 最大时间  = task。size   也就是任务数量
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval2(char[] tasks, int n) {
        // 建立 宽度为 n+1   长度为最大任务数量的队列
        //求解任务种类和最大的任务数量
        Map<Character, Integer> map = new HashMap<>();
        Integer maxNum = 0;
        Integer maxTaskNum = 0;
        for (int i = 0; i < tasks.length; i++) {
            if (map.containsKey(tasks[i])) {
                map.put(tasks[i], map.get(tasks[i]) + 1);
            } else {
                map.put(tasks[i], 1);
            }
            if (map.get(tasks[i]) > maxNum){
                maxNum = map.get(tasks[i]);
            }
        }

        // 找到最大并行数量
        for (Map.Entry<Character,Integer> entry:map.entrySet()){
          if (entry.getValue().equals(maxNum)){
              maxTaskNum +=1;
          }
        }
            return Math.max(tasks.length,(maxNum -1) * (n +1) + maxTaskNum);
    }


    /**
     * todo 待优化
     * 目前复杂度较高每次获取下次可进入窗口的任务
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {

        //维护任务的剩余数量
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> windows = new HashSet<>();
        //维护当前窗口可以加入的任务种类
        Deque<Character> canScheduleTask = new LinkedList<>();
        //维护当前窗口N的任务
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < tasks.length; i++) {
            if (map.containsKey(tasks[i])) {
                map.put(tasks[i], map.get(tasks[i]) + 1);
            } else {
                map.put(tasks[i], 1);
                canScheduleTask.add(tasks[i]);
            }
        }
        boolean status = true;
        int sum = 0;

        while (status){

            // 出窗口 set 和窗口都移除
            if (deque.size() == n+1){
                windows.remove(deque.removeLast());

            }

            // 进入窗口
            Character in = '*';
            boolean isAllFinish = true;
            int surplusTimes = 0;

            //循环剩余的找到可以入窗口的
            for (Map.Entry<Character,Integer> entry:map.entrySet()){
                // 有一个任务没有结束则不能结束
                if (entry.getValue()>0){
                    isAllFinish = false;
                }

                // 找到下个可以入窗口的   当前窗口内不包含这个任务并且任务数>0  并且剩余执行次数最大的
                if (!windows.contains(entry.getKey()) && entry.getValue()>0 && entry.getValue()>surplusTimes){
                    in = entry.getKey();
                    surplusTimes = entry.getValue();
                }
            }

            // 如果待加入的是* 并且已经没有可执行的任务了则不再加入队列
            if (in.equals('*') && isAllFinish){
                System.out.println("当前没有可执行任务进入窗口");
                status = false;
            }else {
                // 进入窗口
                deque.addFirst(in);
                windows.add(in);
                //累加时间
                sum +=1;
                //任务数量减1
                if (!in.equals('*')){
                    map.put(in,map.get(in)-1);
                }
            }
        }
        return sum;
    }



}
