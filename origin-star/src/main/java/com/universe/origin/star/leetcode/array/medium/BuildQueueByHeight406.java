package com.universe.origin.star.leetcode.array.medium;

import java.util.*;

public class BuildQueueByHeight406 {

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {6, 0}, {5, 0}, {4, 0}, {3, 2}, {2, 2}, {1, 4}
        };
        BuildQueueByHeight406 buildQueueByHeight406 = new BuildQueueByHeight406();
        buildQueueByHeight406.reconstructQueue(arr);
    }




    /**
     * 从低向高进行排
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue1(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });

        int[][] res = new int[people.length][];
        for (int i = 0; i < people.length; i++) {
            // 也就是people[i]前面需要有space个空的位置
            int space = people[i][1] + 1;
            for (int j = 0; j < people.length; j++) {
                if (res[j] == null) {
                    space--;
                    if (space == 0) {
                        res[j] = people[i];
                    }
                }
            }
        }
        return res;
    }

    /**
     * 从高向低排
     * 从高向低排采用插队的方式
     * 也就是先拍高的  开始的排序现根据身高排序  再根据序列排序
     * 这样就会保证插入i元素的时候  i前面的都比i打
     *
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o2[0] - o1[0];
                }
            }
        });

        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            res.add(people[i][1], people[i]);
        }
        return res.toArray(new int[res.size()][]);
    }



    /**
     * dfs 解法会超出时间限制
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });
        List<int[]> currentRes = new ArrayList<>();
        int[] visited = new int[people.length];
        currentRes.add(people[0]);
        visited[0] = 1;
        boolean status = dfs(1, people, visited, currentRes);
        if (status) {
            int[][] res = new int[people.length][2];
            for (int i = 0; i < currentRes.size(); i++) {
                res[i] = currentRes.get(i);
            }
            return res;
        }
        return null;

    }

    public boolean dfs(int index, int[][] people, int[] visited, List<int[]> currentRes) {
        if (index == people.length) {
            return true;
        }
        // 找到当前位置的可行解
        List<Node> route = find(people, currentRes, visited);
        route.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.arr[0] - o2.arr[0];
            }
        });
        // 代表没有可行解则返回
        if (route.size() == 0) {
            return false;
        }

        for (int i = 0; i < route.size(); i++) {
            currentRes.add(route.get(i).arr);
            visited[route.get(i).index] = 1;
            boolean status = dfs(index + 1, people, visited, currentRes);
            if (status) {
                return status;
            }
            currentRes.remove(currentRes.size() - 1);
            visited[route.get(i).index] = 0;
        }

        // 代表走了所有路径还是没有最优解
        return false;

    }

    private List<Node> find(int[][] people, List<int[]> currRes, int[] visited) {
        List<Node> route = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            // 可行解必须满足条件
            int count = 0;
            for (int[] currRe : currRes) {
                if (currRe[0] >= people[i][0]) {
                    count++;
                }
            }

            // 判断能够作为可行解
            if (count == people[i][1]) {
                Node node = new Node();
                node.index = i;
                node.arr = people[i];
                route.add(node);
            }
        }
        return route;
    }

    class Node {
        public int[] arr;
        public int index;
    }
}
