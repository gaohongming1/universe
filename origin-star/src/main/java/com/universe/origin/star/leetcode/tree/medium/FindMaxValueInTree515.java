package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 515. 在每个树行中找最大值
 * 您需要在二叉树的每一行中找到最大的值。
 * <p>
 * 示例：
 * <p>
 * 输入:
 * <p>
 * 1
 * / \
 * 3   2
 * / \   \
 * 5   3   9
 * <p>
 * 输出: [1, 3, 9]
 */
public class FindMaxValueInTree515 {

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        TreeNode index = root;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(index);

        List<Integer> result = new ArrayList<>();

        while (!deque.isEmpty()) {
            Deque<TreeNode> flow = new LinkedList<>();
            Integer maxValue = deque.peekFirst().val;
            while (!deque.isEmpty()) {
                TreeNode current = deque.pollFirst();
                if (current.val > maxValue) {
                    maxValue = current.val;

                }

                if (current.left != null) {
                    flow.addLast(current.left);
                }

                if (current.right != null) {
                    flow.addLast(current.right);
                }
            }
            deque = flow;
            result.add(maxValue);

        }
        return result;


    }
}
