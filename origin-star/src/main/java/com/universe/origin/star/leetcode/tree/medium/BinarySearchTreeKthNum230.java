package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 * 提示：
 * <p>
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 * <p>
 * <p>
 * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 * <p>
 * 通过次数92,353提交次数126,473
 */
public class BinarySearchTreeKthNum230 {
    private Integer current = 0;

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node5.left = node3;
        node5.right = node6;
        node3.left = node2;
        node3.right = node4;
        node2.left = node1;
        BinarySearchTreeKthNum230 binarySearchTreeKthNum230 = new BinarySearchTreeKthNum230();
        binarySearchTreeKthNum230.kthSmallest(node5,4);
    }

    /**
     * 二叉搜索树的中序遍历是递增序列
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        return dfs(root,k);
    }

    public Integer dfs(TreeNode root, int k) {
        if (root == null) {
            return null;
        }

        Integer left = dfs(root.left, k);
        if (left!=null){
            return left;
        }

        // 输出当前元素
        current += 1;
        System.out.println(root.val);
        if (k == current) {
            return root.val;
        }

        Integer right = dfs(root.right, k);
        if (right!=null){
            return right;
        }

        return null;
    }
}
