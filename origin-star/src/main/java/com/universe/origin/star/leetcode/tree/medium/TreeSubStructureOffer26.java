package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 26. 树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * <p>
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * <p>
 * 例如:
 * 给定的树 A:
 * <p>
 * 3
 * / \
 * 4   5
 * / \
 * 1   2
 * 给定的树 B：
 * <p>
 * 4
 * /
 * 1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 * <p>
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 */
public class TreeSubStructureOffer26 {
    public static void main(String[] args) {

    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }
        // 遍历节点A
        TreeNode aIndex = A;
        TreeNode bIndex = B;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(aIndex);
        while (!deque.isEmpty()) {
            Deque<TreeNode> child = new LinkedList<>();
            while (!deque.isEmpty()) {
                aIndex = deque.pollFirst();
                boolean status = isSame(aIndex, bIndex);
                if (status) {
                    return true;
                }

                if (aIndex.left != null) {
                    child.addLast(aIndex.left);
                }
                if (aIndex.right != null) {
                    child.addLast(aIndex.right);
                }
            }
            deque = child;
        }
        return false;
    }



    public boolean isSame(TreeNode A, TreeNode B) {
        if (A == null && B == null) {
            return true;
        }
        if (A!=null && B==null){
            return true;
        }

        if ((A == null && B != null)) {
            return false;
        }

        if (A.val == B.val) {
            return isSame(A.left, B.left) && isSame(A.right, B.right);
        } else {
            return false;
        }
    }
}
