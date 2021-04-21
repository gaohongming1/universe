package com.universe.origin.star.leetcode.graph.medium;

import java.util.*;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 */
public class SchoolTimeTable207 {
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;
    int[] indeg;

    public static void main(String[] args) {
        int[][] prerequisites = new int[][]{
                {1, 4},
                {2, 4},
                {3, 1},
                {3, 2}
        };
        SchoolTimeTable207 schoolTimeTable207 = new SchoolTimeTable207();
        schoolTimeTable207.canFinish(5, prerequisites);
    }

    /**
     * 有向图
     * 广度优先  去边法
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0 && numCourses != 0) {
            return true;
        }

        // 记录节点的度
        Map<Integer, Integer> degree = new HashMap<>();
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
            int[] group = prerequisites[i];
            if (!degree.containsKey(group[0])) {
                degree.put(group[0], 0);
            }

            if (!degree.containsKey(group[1])) {
                degree.put(group[1], 0);
            }
            degree.put(group[0], degree.get(group[0]) + 1);

            if (!map.containsKey(group[1])) {
                map.put(group[1], new ArrayList<>());
            }

            if (!map.containsKey(group[0])) {
                map.put(group[0], new ArrayList<>());
            }
            map.get(group[1]).add(group[0]);
        }

        Deque<Integer> deque = new ArrayDeque<>();
        for (Integer key : degree.keySet()) {
            if (degree.get(key) == 0) {
                deque.offer(key);
            }
        }
        List<Integer> visited = new ArrayList<>();


        while (!deque.isEmpty()) {
            int curr = deque.poll();
            visited.add(curr);
            List<Integer> child = map.get(curr);
            // 子节点入度减1
            for (int i = 0; i < child.size(); i++) {
                degree.put(child.get(i), degree.get(child.get(i)) - 1);
                if (degree.get(child.get(i)) == 0) {
                    deque.offer(child.get(i));
                }
            }
        }
        return visited.size() == degree.size();
    }


    /**
     * dfs
     * prerequisites  中 1位是0位的前驱节点
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        //初始化边
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        // 初始化点未访问
        visited = new int[numCourses];
        //初始化点可到达的边
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        //未访问则开始访问
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int node) {
        // 设置为1代表本次递归预占，如果出现环则会碰到为1的则校验失败
        visited[node] = 1;
        // 获得点可到达的边
        for (int v: edges.get(node)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[node] = 2;
    }

    /**
     * 广度优先遍历
     * 移边法
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        indeg = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            edges.get(prerequisite[1]).add(prerequisite[0]);
            ++indeg[prerequisite[0]];
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }
        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int node = queue.poll();
            for (Integer integer : edges.get(node)) {
                --indeg[integer];
                if (indeg[integer] == 0) {
                    queue.offer(integer);
                }

            }
        }
        return visited == numCourses;

    }




    }
