package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

/**
 * 450. 删除二叉搜索树中的节点
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
 * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * <p>
 * 示例:
 * <p>
 * root = [5,3,6,2,4,null,7]
 * key = 3
 * <p>
 * 5
 * / \
 * 3   6
 * / \   \
 * 2   4   7
 * <p>
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * <p>
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * <p>
 * 5
 * / \
 * 4   6
 * /     \
 * 2       7
 * <p>
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 * <p>
 * 5
 * / \
 * 2   6
 * \   \
 * 4   7
 */
public class BinarySearchTreeDelete450 {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        treeNode1.right = treeNode2;
        BinarySearchTreeDelete450 binarySearchTreeDelete450 = new BinarySearchTreeDelete450();
        binarySearchTreeDelete450.deleteNode(treeNode1,1);


    }


    /*
  One step right and then always left
  */
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }

    /*
    One step left and then always right
    */
    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        // delete from the right subtree
        if (key > root.val) root.right = deleteNode(root.right, key);
            // delete from the left subtree
        else if (key < root.val) root.left = deleteNode(root.left, key);
            // delete the current node
        else {
            // the node is a leaf
            if (root.left == null && root.right == null) root = null;
                // the node is not a leaf and has a right child
            else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            }
            // the node is not a leaf, has no right child, and has a left child
            else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            }
        }

        // 找到当前节点和其父节点
        TreeNode indexP = null;
        TreeNode index = root;

        while (index != null && index.val != key) {
            indexP = index;
            if (index.val > key) {
                index = index.left;
            } else {
                index = index.right;
            }
        }
        if (index == null) {
            return root;
        }

        //删除叶总节点
        if (index.left == null && index.right == null) {
            if (indexP.left == index) {
                indexP.left = null;
            } else {
                indexP.right = null;
            }
            return root;
        }

        // 有左右节点
        if (index.left != null && index.right != null) {
            // 找到index的右子树的最小点
            TreeNode minRight = index.right;
            TreeNode minRightP = index;
            while (minRight.left != null) {
                minRightP = minRight;
                minRight = minRight.left;
            }
            // 交换index minRight
            index.val = minRight.val;

            // 删除最小节点
            if (minRightP == index) {
                minRightP.right = minRight.right;
            } else {
                minRightP.left = minRight.right;
            }
            return root;
        } else {
            if (index.left != null) {
                if (indexP==null){
                    return index.left;
                }else {
                    if (index == indexP.left) {
                        indexP.left = index.left;
                    } else {
                        indexP.right = index.left;
                    }
                }
            } else {
                if (indexP == null){
                    return index.right;
                }else {
                    if (index == indexP.left) {
                        indexP.left = index.right;
                    } else {
                        indexP.right = index.right;
                    }
                }
            }
            return root;
        }
    }
}
