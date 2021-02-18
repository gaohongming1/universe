package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 129. 求根到叶子节点数字之和
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * <p>
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * <p>
 * 计算从根到叶子节点生成的所有数字之和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 1
 * / \
 * 2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 * <p>
 * 输入: [4,9,0,5,1]
 * 4
 * / \
 * 9   0
 * / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 */
public class RootToLeafSum129 {
    public static void main(String[] args) {
//        TreeNode treeNode1 = new TreeNode(3);
//        TreeNode treeNode2 = new TreeNode(5);
//        TreeNode treeNode3 = new TreeNode(6);
//        TreeNode treeNode4 = new TreeNode(2);
//        TreeNode treeNode5 = new TreeNode(7);
//        TreeNode treeNode6 = new TreeNode(4);
//        TreeNode treeNode7 = new TreeNode(1);
//        TreeNode treeNode8 = new TreeNode(0);
//        TreeNode treeNode9 = new TreeNode(8);
//        treeNode1.left = treeNode2;
//        treeNode1.right = treeNode7;
//        treeNode2.left = treeNode3;
//        treeNode2.right = treeNode4;
//        treeNode4.left = treeNode5;
//        treeNode4.right = treeNode6;
//        treeNode7.left = treeNode8;
//        treeNode7.right = treeNode9;
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(0);
        //TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        //treeNode1.right = treeNode3;
        RootToLeafSum129 rootToLeafSum129 = new RootToLeafSum129();
        List<String> result = new ArrayList<>();
        rootToLeafSum129.dfs(treeNode1, "", result);
        System.out.println("test");

    }

    public int sumNumbers(TreeNode root) {
        List<String> result = new ArrayList<>();
        dfs(root,"",result);
        int sum = 0;
        for (int i = 0; i < result.size(); i++) {
            sum += Integer.valueOf(result.get(i));
        }
        return sum;

    }

    /**
     * 深度优先遍历
     *
     * @param root
     * @param current
     * @param result
     */
    public void dfs(TreeNode root, String current, List<String> result) {
        if (root == null) {
            return;
        }
        current = current.concat(String.valueOf(root.val));

        if (root.left == null && root.right == null) {
            result.add(new String(current));
        }
        if (root.left != null) {
            dfs(root.left, current, result);
        }

        if (root.right != null) {
            dfs(root.right, current, result);
        }
        current = current.substring(0, current.length() - 1);
    }


}














