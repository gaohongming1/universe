package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [0]
 * 输出：[0]
 */
public class BinaryTreeTransToLinkList114 {
    public static void main(String[] args) {

    }


    /**
     * 找前驱节点
     * 根  左  右
     * 根节点的右子树挂在根节点的左子树的最右边的节点上
     * 然后根节点的左子树换到右子树上
     *
     * @param root
     */
    public void flatten2(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            if (current.left!=null){
                TreeNode next = current.left;
                TreeNode index = next;
                // 找到左子树的最右边的点
                while (index.right!=null){
                    index = index.right;
                }
                // 把当前节点的右子树挂在index的右子树上
                index.right = current.right;
                // 当前点挂在current的右子树上
                current.left = null;
                current.right = next;
            }
            current = current.right;
        }
    }


    /**
     * 普通解法
     * 遍历二叉树，使用list记录前序遍历的顺序然后重新组装成链表
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        pre(root, list);

        TreeNode index = root;
        for (int i = 1; i < list.size(); i++) {
            index.left = null;
            index.right = list.get(i);
            index = index.right;
        }
    }

    public void pre(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        pre(root.left, list);
        pre(root.right, list);
    }


}










