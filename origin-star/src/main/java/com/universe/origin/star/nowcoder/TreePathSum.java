package com.universe.origin.star.nowcoder;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 给定一个二叉树和一个值sum，请找出所有的根节点到叶子节点的节点值之和等于sum的路径，
 */
public class TreePathSum {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }

    /**
     *
     * @param root TreeNode类
     * @param sum int整型
     * @return int整型ArrayList<ArrayList<>>
     */
    public ArrayList<ArrayList<Integer>> pathSum (TreeNode root, int sum) {
        if(root == null){
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        dfs(root, 0, sum, result, path);
        return result;
    }

    private void dfs(TreeNode node, int pathSum, int sum, ArrayList<ArrayList<Integer>> result, LinkedList<Integer> path){
        if(node == null){
            return;
        }

        if(path.add(node.val) && (pathSum += node.val) == sum
                && node.left == null && node.right == null){
            result.add(new ArrayList<>(path));
        }

        dfs(node.left, pathSum, sum, result, path);
        dfs(node.right, pathSum, sum, result, path);

        path.removeLast();
    }
}
