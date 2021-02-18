package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.*;

/**
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 示例 2：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 示例 3：
 * <p>
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 * 提示：
 * <p>
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 */
public class RecentCommonAncestor236 {

    private TreeNode ans;
    private HashMap<TreeNode,TreeNode> map = new HashMap<>();
    private Set<TreeNode> visited = new HashSet<>();


    public static void main(String[] args) {
        RecentCommonAncestor236 recentCommonAncestor236 = new RecentCommonAncestor236();
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(6);
        TreeNode treeNode4 = new TreeNode(2);
        TreeNode treeNode5 = new TreeNode(7);
        TreeNode treeNode6 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(1);
        TreeNode treeNode8 = new TreeNode(0);
        TreeNode treeNode9 = new TreeNode(8);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode7;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;
        treeNode4.left = treeNode5;
        treeNode4.right = treeNode6;
        treeNode7.left = treeNode8;
        treeNode7.right = treeNode9;
        List<TreeNode> path = new ArrayList<>();
        recentCommonAncestor236.lowestCommonAncestor(treeNode1, treeNode2, treeNode6);
        System.out.println("yes");
    }


    /**
     * 解法3 利用hash表和set
     * 使用hash表记录父亲节点，也就是访问路径，使用set记录是否到访节点，如果存在一个到访过的节点那么就表示是公共节点
     * 自底向上找
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {

        dfs2(root);

        // 把跑的父几点加入到访问过的集合中
        while (p!=null){
            visited.add(p);
            p = map.get(p);
        }

        while (q!=null){
            if (visited.contains(q)){
                return q;
            }
            q = map.get(q);
        }
        return map.get(q);
    }

    // 初始化map记录所有节点的父亲节点
    public void dfs2(TreeNode root){
        if (root.left!=null){
            map.put(root.left,root);
            dfs2(root.left);
        }

        if (root.right!=null){
            map.put(root.right,root);
            dfs2(root.right);
        }
    }



    /**
     * 使用证明的解法
     * 1：情况一  两个节点在公共节点的左右子树存在，那么公共节点肯定满足定理    p  q  包含在公共节点的左右子树中
     * 2：情况二： 其中一个节点是公共节点，另一个节点在这个节点的左子树或者右子树上
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root,p,q);
        return ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }

        boolean lStatus = dfs(root.left, p, q);
        boolean rStatus = dfs(root.right, p, q);
        if ((lStatus && rStatus) || ((root.val == p.val || root.val == q.val) && (lStatus || rStatus))) {
            ans = root;
        }

        return lStatus || rStatus || (root.val == p.val || root.val == q.val);
    }


    /**
     * 这种解法是证明了路径
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();
        boolean status1 = recursion(root, p, path1);
        boolean status2 = recursion(root, q, path2);

        TreeNode treeNode1P = path1.get(path1.size() - 1);
        TreeNode treeNode2P = path2.get(path2.size() - 1);
        // 倒序找到第一个不同的节点
        if (status1 && status2) {
            int len = Math.min(path1.size(), path2.size());

            TreeNode treeNode1, treeNode2;
            for (int i = 2; i <= len; i++) {
                treeNode1 = path1.get(path1.size() - i);
                treeNode2 = path2.get(path2.size() - i);
                if ((treeNode1.val != treeNode2.val) && (treeNode1P.val == treeNode2P.val)) {
                    return treeNode1P;
                }
                treeNode1P = treeNode1;
                treeNode2P = treeNode2;
            }
        }
        return treeNode1P;
    }


    public boolean recursion(TreeNode root, TreeNode value, List<TreeNode> path) {
        if (root.val == value.val) {
            path.add(root);
            return true;
        }

        // 先遍历左孩子
        Boolean status = false;
        if (root.left != null) {
            status = recursion(root.left, value, path);
        }
        if (status) {
            // 左子树可以达到目标节点
            path.add(root);
            return true;
        }
        //代表左子树无法达到目标节点
        if (root.right != null) {
            status = recursion(root.right, value, path);
        }
        //判断右子树是否可以达到目标节点
        if (status) {
            path.add(root);
        }
        return status;
    }


}
