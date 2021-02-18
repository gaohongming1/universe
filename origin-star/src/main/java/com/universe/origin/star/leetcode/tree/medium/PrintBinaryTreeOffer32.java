package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 * <p>
 * <p>
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 */
public class PrintBinaryTreeOffer32 {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode3.right = treeNode5;
        PrintBinaryTreeOffer32 printBinaryTreeOffer32 = new PrintBinaryTreeOffer32();
        printBinaryTreeOffer32.levelOrder(treeNode1);
    }


    /**
     * 层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        // true 代表正向
        boolean addStatus = true;
        while (!deque.isEmpty()) {
            Deque<TreeNode> child = new LinkedList<>();
            List<Integer> current = new ArrayList<>();
            while (!deque.isEmpty()) {
                // 正向
                TreeNode node = deque.pollFirst();
                current.add(node.val);
                // 本次正向，下次逆向
                if (!addStatus){
                    if (node.right != null) {
                        child.addFirst(node.right);
                    }
                    if (node.left != null) {
                        child.addFirst(node.left);
                    }
                }else {
                    // 本次逆向下次正向
                    if (node.left != null) {
                        child.addFirst(node.left);
                    }
                    if (node.right != null) {
                        child.addFirst(node.right);
                    }
                }
            }
            result.add(current);

            while (!child.isEmpty()){
                deque.addLast(child.pollFirst());
            }
            addStatus = !addStatus;
        }
        return result;
    }
}
