package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 * 示例 2：
 * <p>
 * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * 输出：19
 *  
 */
public class BestDeepLeafSum1302 {


    public int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        TreeNode index = root;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        Integer sum = 0;
        while (!deque.isEmpty()) {
            Deque<TreeNode> flow = new LinkedList<>();
            sum = 0;
            while (!deque.isEmpty()) {
                TreeNode current = deque.pollFirst();
                sum += current.val;


                if (current.left != null) {
                    flow.addLast(current.left);


                }
                if (current.right != null) {
                    flow.addLast(current.right);

                }
            }
            deque = flow;
        }
        return sum;
    }
}
