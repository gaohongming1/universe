package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.Stack;

/**
 * 剑指 Offer 07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 *
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class RebuildBinaryTreeoffer07 {
    private HashMap<Integer,Integer> map;
    public static void main(String[] args) {
        int[] pre = new int[]{3,9,8,5,4,10,20,15,7};
        int[] ino = new int[]{4,5,8,10,9,3,15,20,7};
        RebuildBinaryTreeoffer07 rebuildBinaryTreeoffer07 = new RebuildBinaryTreeoffer07();
        rebuildBinaryTreeoffer07.buildTree2(pre,ino);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return recursion(preorder,inorder,0,n-1,0,n-1);
    }

    public TreeNode recursion(int[] preorder,int[] inorder, int preLeft, int preRight, int inLeft, int inRight ){
        if (preLeft > preRight){
            return null;
        }
        //先找到头结点是前序遍历的第一个节点
        TreeNode head = new TreeNode(preorder[preLeft]);
        // 找到中序中head的位置
        int position = map.get(head.val);
        // 得到左子树的数量
        int leftNum = position - inLeft;
        head.left = recursion(preorder,inorder,preLeft+1,preLeft+leftNum,inLeft,position-1);
        head.right = recursion(preorder, inorder,preLeft+leftNum+1,preRight,position+1,inRight);
        return head;
    }


    /**
     * 使用迭代
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || inorder.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        int inIndex = 0;

        for (int i = 1; i <preorder.length ; i++) {
            TreeNode treeNode = new TreeNode(preorder[i]);
            TreeNode node = stack.peek();
            //前序遍历是从根节点一直向左子树向下遍历递增，中序遍历的第一个元素是叶子节点，不相等则代表还未遇到叶子节点
            if (node.val!=inorder[inIndex]){
                node.left = treeNode;
                stack.add(treeNode);
            }else {
                //代表左子树遍历完了，该右子树了
                while (!stack.isEmpty() && stack.peek().val==inorder[inIndex]){
                    node = stack.pop();
                    inIndex +=1;
                }
                // 这时候不相等的元素inorder[inIndex] 就是node的右子树
                node.right = treeNode;
                stack.add(treeNode);
            }
        }
        return root;
    }
}

















