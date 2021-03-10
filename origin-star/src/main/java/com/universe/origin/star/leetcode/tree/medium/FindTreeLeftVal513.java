package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 513. 找树左下角的值
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * <p>
 * 2
 * / \
 * 1   3
 * <p>
 * 输出:
 * 1
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * <p>
 * 1
 * / \
 * 2   3
 * /   / \
 * 4   5   6
 * /
 * 7
 * <p>
 * 输出:
 * 7
 * <p>
 * <p>
 * 注意: 您可以假设树（即给定的根节点）不为 NULL。
 */
public class FindTreeLeftVal513 {


    public int findBottomLeftValue2(TreeNode root) {
        TreeNode index = root;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(index);
        TreeNode last = root;
        while (!deque.isEmpty()) {
            TreeNode current = deque.pollFirst();
            last = current;
            if (current.right != null) {
                deque.addLast(current.right);
            }
            if (current.left != null) {
                deque.addLast(current.left);
            }
        }
        return last.val;
    }


    /**
     * 层序遍历
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        TreeNode index = root;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(index);
        TreeNode last = root;
        while (!deque.isEmpty()) {
            Deque<TreeNode> flow = new LinkedList<>();
            last = deque.peekFirst();
            while (!deque.isEmpty()) {
                TreeNode current = deque.pollFirst();
                if (current.left != null) {
                    flow.addLast(current.left);
                }
                if (current.right != null) {
                    flow.addLast(current.right);
                }
            }
            deque = flow;
        }
        return last.val;
    }
}
