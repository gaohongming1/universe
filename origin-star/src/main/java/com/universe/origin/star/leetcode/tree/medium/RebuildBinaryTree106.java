package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class RebuildBinaryTree106 {
    public static void main(String[] args) {
        int[] ints = new int[]{15, 9, 10, 3, 20, 5, 7, 8, 4};
        int[] post = new int[]{15, 10, 9, 5, 4, 8, 7, 20, 3};
        RebuildBinaryTree106 rebuildBinaryTree106 = new RebuildBinaryTree106();
        rebuildBinaryTree106.buildTree2(ints, post);

    }

    /**
     * 递归的方式
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return recursion(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1, map);
    }

    public TreeNode recursion(int[] inorder, int[] postorder, int inLeft, int inRight, int postLeft, int postRight, Map<Integer, Integer> map) {

        if (inLeft > inRight) {
            return null;
        }

        // 先找到头结点 头结点是后序遍历的倒数第一个节点
        TreeNode head = new TreeNode(postorder[postRight]);
        // 计算节点的左右边界
        Integer index = map.get(head.val);

        // 计算左边节点的数量
        Integer leftNum = index - inLeft;
        head.left = recursion(inorder, postorder, inLeft, index - 1, postLeft, postLeft + leftNum - 1, map);
        head.right = recursion(inorder, postorder, index + 1, inRight, postLeft + leftNum, postRight - 1, map);
        return head;
    }


    /**
     * 迭代方式
     * 后续遍历逆序  是右子树 当碰到不等于中序的第一个节点的时候，证明是叶子节点
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        Stack<TreeNode> stack = new Stack<>();
        Integer length = postorder.length - 1;
        TreeNode root = new TreeNode(postorder[length]);
        stack.add(root);
        Integer inIndex = inorder.length - 1;
        for (int i = length - 1; i >= 0; i--) {
            TreeNode node = stack.peek();
            TreeNode currentNode = new TreeNode(postorder[i]);

            // 代表是右子树还未遍历完全
            if (node.val != inorder[inIndex]) {
                node.right = currentNode;
                stack.add(currentNode);
            } else {
                // 相等则代表到达了叶子节点  叶子节点的下一位就是第一个左节点
                while (!stack.isEmpty() && stack.peek().val == inorder[inIndex]){
                    node = stack.pop();
                    inIndex = inIndex-1;
                }
                // 代表找到可以挂currentNode的节点了
                node.left = currentNode;
                stack.add(currentNode);
            }
        }
        return root;
    }

}


















