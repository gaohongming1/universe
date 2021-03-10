package com.universe.origin.star.leetcode.tree.medium;


import java.util.*;

/**
 * 429. N 叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * <p>
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树的高度不会超过 1000
 * 树的节点总数在 [0, 10^4] 之间
 */
public class NBinaryTreeFlow429 {



    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Node index = root;
        Deque<Node> deque = new LinkedList<>();
        deque.addLast(root);
        List<List<Integer>> allResult = new ArrayList<>();

        while (!deque.isEmpty()) {
            Deque<Node> flow = new LinkedList<>();
            List<Integer> list = new ArrayList<>();

            while (!deque.isEmpty()) {
                Node current = deque.pollFirst();
                list.add(current.val);
                if (Objects.nonNull(current.children) && current.children.size()!=0){
                    flow.addAll(current.children);
                }
            }
            allResult.add(list);
            deque = flow;
        }
        return allResult;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
