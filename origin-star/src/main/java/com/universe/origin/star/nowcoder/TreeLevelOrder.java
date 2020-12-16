package com.universe.origin.star.nowcoder;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 给定一个二叉树，返回该二叉树层序遍历的结果，（从左到右，一层一层地遍历）
 * 例如：
 * 给定的二叉树是{3,9,20,#,#,15,7},
 *
 * 该二叉树层序遍历的结果是
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class TreeLevelOrder {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * @param root TreeNode类
     * @return int整型ArrayList<ArrayList <>>
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }

        int currentLevelNum = 1, nextLevelNum = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> levelNode = new ArrayList<>();

        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            levelNode.add(node.val);
            if(node.left != null){
                queue.offer(node.left);
                nextLevelNum++;
            }
            if(node.right != null){
                queue.offer(node.right);
                nextLevelNum++;
            }
            if(--currentLevelNum == 0){
                result.add(levelNode);
                levelNode = new ArrayList<>();
                currentLevelNum = nextLevelNum;
                nextLevelNum = 0;
            }
        }
        return result;
    }
}
