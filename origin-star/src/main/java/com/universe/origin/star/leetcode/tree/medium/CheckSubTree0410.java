package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

/**
 * 面试题 04.10. 检查子树
 * 检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
 * <p>
 * 如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
 * <p>
 * 注意：此题相对书上原题略有改动。
 * <p>
 * 示例1:
 * <p>
 * 输入：t1 = [1, 2, 3], t2 = [2]
 * 输出：true
 * 示例2:
 * <p>
 * 输入：t1 = [1, null, 2, 4], t2 = [3, 2]
 * 输出：false
 * 提示：
 * <p>
 * 树的节点数目范围为[0, 20000]。
 */
public class CheckSubTree0410 {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode t21 = new TreeNode(2);
        node1.left = node2;
        node1.right = node3;
        CheckSubTree0410 checkSubTree0410 = new CheckSubTree0410();
        checkSubTree0410.checkSubTree(node1, t21);
    }

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return false;

        }
        return dfs(t1, t2) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }

    public boolean dfs(TreeNode t1, TreeNode t2) {
        if ((t1 == null && t2 != null) || (t1 != null && t2 == null)) {
            return false;
        }
        if (t1 == null && t2 == null){
            return true;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return dfs(t1.left, t2.left) && dfs(t1.right, t2.right);
    }
}
