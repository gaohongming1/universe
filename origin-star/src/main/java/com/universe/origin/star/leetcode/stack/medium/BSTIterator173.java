package com.universe.origin.star.leetcode.stack.medium;

import java.util.ArrayList;

/**
 * 二叉搜索迭代器
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 * <p>
 * 解法1：利用两个栈
 * <p>
 * 解法2：利用二叉搜索树的性质  二叉搜索树的中序遍历就是递增序列，使用数组存储中序序列遍历结果。
 */
public class BSTIterator173 {

    private int index = -1;
    private ArrayList<Integer> array;

    public BSTIterator173(TreeNode root) {
        array = new ArrayList<>();
        this.inorder(root);


    }

    public void inorder(TreeNode root){

        if (root == null){
            return;
        }

        this.inorder(root.left);
        array.add(root.val);
        this.inorder(root.right);
    }

    public int next() {
        return this.array.get(++index);
    }

    public boolean hasNext() {
        return this.index+1 < this.array.size();
    }




    //private Stack<Integer> stack;
//    /**
//     * 利用栈 栈顶维持最大
//     * @param root
//     */
//    public BSTIterator173(TreeNode root) {
//        this.stack = new Stack<>();
//        Stack<TreeNode> temp = new Stack<>();
//        temp.add(root);
//        while (!temp.isEmpty()) {
//            TreeNode treeNode = temp.pop();
//
//            // 在stack的栈顶保持最大元素
//            //当栈不为空并且当前元素小于栈顶元素，则需要把栈顶元素弹出
//            Stack<Integer> temp2 = new Stack<>();
//            while (!stack.isEmpty() && treeNode.val > stack.peek()) {
//                temp2.add(stack.pop());
//            }
//            //再把当前元素加入栈顶
//            stack.add(treeNode.val);
//            //再把前面有序的加回到栈顶
//            while (!temp2.isEmpty()) {
//                stack.add(temp2.pop());
//            }
//
//            if (treeNode.left != null) {
//                temp.add(treeNode.left);
//            }
//
//            if (treeNode.right != null) {
//                temp.add(treeNode.right);
//            }
//
//        }
//
//    }
//
//    public int next() {
//        return stack.pop();
//    }
//
//    public boolean hasNext() {
//        return !stack.isEmpty();
//    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
