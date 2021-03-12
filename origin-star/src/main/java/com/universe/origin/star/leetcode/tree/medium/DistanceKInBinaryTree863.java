package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.*;

/**
 * 863. 二叉树中所有距离为 K 的结点
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 * <p>
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 * <p>
 * <p>
 * <p>
 * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * 上面的输入仅仅是对这些对象进行了序列化描述。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 给定的树是非空的。
 * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
 * 目标结点 target 是树上的结点。
 * 0 <= K <= 1000.
 */
public class DistanceKInBinaryTree863 {
    public Map<TreeNode, TreeNode> map = new HashMap<>();

    public Integer sum;

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(6);

        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(8);
        TreeNode node8 = new TreeNode(7);
        TreeNode node9 = new TreeNode(4);
        node1.left = node2;
        node2.left = node4;
        node2.right = node5;
        node5.left = node8;
        node5.right = node9;
        node1.right = node3;
        node3.left = node6;
        node3.right = node7;
        DistanceKInBinaryTree863 distanceKInBinaryTree863 = new DistanceKInBinaryTree863();
        List<Integer> list = new ArrayList<>();
        distanceKInBinaryTree863.distanceK(node1, node2, 2);
    }


    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        dfs2(root, null);

        Queue<TreeNode> deque = new LinkedList<>();
        deque.offer(null);
        deque.add(target);
        // 这里插入null的目的是表示这一层遍历完了

        // 记录已经到访的节点
        Set<TreeNode> seen = new HashSet<>();
        seen.add(null);
        seen.add(target);

        int dist = 0;

        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();

            // 代表+1
            if (node == null) {
                if (dist == K) {
                    List<Integer> list = new ArrayList<>();
                    for (TreeNode treeNode : deque) {
                        list.add(treeNode.val);
                    }
                    return list;
                }
                deque.offer(null);
                dist += 1;
            } else {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    deque.offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    deque.offer(node.right);
                }
                TreeNode par = map.get(node);

                if (!seen.contains(par)) {
                    seen.add(par);
                    deque.offer(par);
                }
            }
        }
        return new ArrayList<>();
    }

    public void dfs2(TreeNode child, TreeNode parent) {
        if (child != null) {
            map.put(child, parent);
            dfs2(child.left, child);
            dfs2(child.right, child);
        }
    }

    /**
     * 这种情况对于 回溯过程中  已经访问过的节点不会再访问，所以会有问题  判断过程复杂
     *
     * @param root
     * @param target
     * @param k
     * @param result
     */
    public void dfs(TreeNode root, TreeNode target, int k, List<Integer> result) {

        if (root == null) {
            return;
        }
        if (root == target) {
            sum = 0;
        }
        // 左边节点递归
        if (root.left != null) {
            // 代表已经开始统计
            if (sum != null) {
                sum += 1;
                if (sum == k) {
                    if (!result.contains(root.left.val)) {
                        result.add(root.left.val);
                    }
                } else {
                    dfs(root.left, target, k, result);
                }
                sum -= 1;
            } else {
                dfs(root.left, target, k, result);
                // 代表向上回溯的过程
                if (sum != null) {
                    sum += 1;
                    if (sum == k) {
                        if (!result.contains(root.val)) {
                            result.add(root.val);
                        }
                    }
                }
            }
        }
        if (root.right != null) {
            if (sum != null) {
                sum += 1;
                if (sum == k) {
                    if (!result.contains(root.right.val)) {
                        result.add(root.right.val);
                    }
                } else {
                    dfs(root.right, target, k, result);
                }
                sum -= 1;
            } else {
                dfs(root.right, target, k, result);
                // 判断sum
                if (sum != null) {
                    sum += 1;
                    if (sum == k) {
                        if (!result.contains(root.val)) {
                            result.add(root.val);
                        }
                    } else {
                        // 再次访问当前节点的左子树
                        if (sum < k) {

                            dfs(root, target, k, result);
                        }
                    }
                }
            }
        }
    }
}
