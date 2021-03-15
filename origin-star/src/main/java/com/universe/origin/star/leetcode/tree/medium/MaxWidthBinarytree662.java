package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 662. 二叉树最大宽度
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * <p>
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * <p>
 * 1
 * /   \
 * 3     2
 * / \     \
 * 5   3     9
 * <p>
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 * 示例 2:
 * <p>
 * 输入:
 * <p>
 * 1
 * /
 * 3
 * / \
 * 5   3
 * <p>
 * 输出: 2
 * 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
 * 示例 3:
 * <p>
 * 输入:
 * <p>
 * 1
 * / \
 * 3   2
 * /
 * 5
 * <p>
 * 输出: 2
 * 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
 * 示例 4:
 * <p>
 * 输入:
 * <p>
 * 1
 * / \
 * 3   2
 * /     \
 * 5       9
 * /         \
 * 6           7
 * 输出: 8
 * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
 * 注意: 答案在32位有符号整数的表示范围内。
 */
public class MaxWidthBinarytree662 {
    /**
     * map
     */
    public Map<Integer, Integer> map = new HashMap<>();

    public int ans=0;


    /**
     * bfs 也可以不过要维护每个几点的position
     */


    /**
     * 利用完全二叉树的性质
     * 第i层节点数量是第i-1层节点数量的两倍
     * 第一个节点位置是 2 （i-2）次方 +1    最后一个节点是2i（i-2） 次方加 2的（i-1）次方
     * 深度优先搜索
     *
     * @param root
     * @return
     */
    public int widthOfBinaryTree2(TreeNode root) {
        dfs(root, 0, 0);
        return ans;
    }

    public void dfs(TreeNode node, int dep, int pos){
        if (node == null) {
            return;
        }
        map.computeIfAbsent(dep, x -> pos);
        ans = Math.max(ans, pos - map.get(dep) + 1);
        dfs(node.left,dep+1,pos * 2);
        dfs(node.right, dep+1, pos * 2 + 1);
    }


    /**
     * 层序遍历部分 超时
     *
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        int max = 1;
        while (!deque.isEmpty()) {
            int temp = 0;
            Deque<TreeNode> flow = new LinkedList<>();

            while (deque.size() > 1) {
                TreeNode node = deque.pollFirst();
                if (node != null) {
                    if (temp == 0) {
                        if (node.left != null) {
                            flow.addFirst(node.left);
                            flow.addLast(node.right);
                            temp += 2;
                        } else if (node.right != null) {
                            flow.addLast(node.right);
                            temp += 1;
                        }
                    } else {
                        flow.addLast(node.left);
                        flow.addLast(node.right);
                        temp += 2;
                    }
                } else {
                    // 判断temp是否已经初始化
                    if (temp != 0) {
                        flow.addLast(null);
                        flow.addLast(null);
                        temp += 2;
                    }
                }
            }
            // 处理尾部
            TreeNode last = deque.pollLast();
            if (last != null) {
                if (last.left != null) {
                    temp += 1;
                    flow.addLast(last.left);
                    if (last.right != null) {
                        temp += 1;
                        flow.addLast(last.right);
                    }
                } else if (last.right != null) {
                    if (temp != 0) {
                        flow.addLast(null);
                        flow.addLast(last.right);
                        temp += 2;
                    }else {
                        flow.addLast(last.right);
                        temp += 1;
                    }
                }
            }

            if (temp > max) {
                max = temp;
            }

            // flow 减头减尾
            while (flow.size() > 0) {
                TreeNode head = flow.peekFirst();
                if (head == null || (head.left == null && head.right == null)) {
                    flow.pollFirst();
                } else {
                    break;
                }
            }
            while (flow.size() > 0) {
                TreeNode tail = flow.peekLast();
                if (tail == null || (tail.left == null && tail.right == null)) {
                    flow.pollLast();
                } else {
                    break;
                }
            }

            deque = flow;
        }
        return max;
    }
}
