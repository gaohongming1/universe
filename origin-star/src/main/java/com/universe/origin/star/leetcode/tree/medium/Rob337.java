package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.*;

/**
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 * 通过次数84,319提交次数137,912
 */
public class Rob337 {
    // 存放当前节点加入，子节点的最大权值和
    Map<TreeNode,Integer> f = new HashMap<>();
    // 存放当前节点不加入，子节点的最大权值和
    Map<TreeNode,Integer> g = new HashMap<>();
    public static void main(String[] args) {

    }


    /**
     * 动态规划加上dfs
     * @param root
     * @return
     */
    public int rob2(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root,0),g.getOrDefault(root,0));

    }


    public void dfs(TreeNode node){
        if (node == null){
            return;
        }
        dfs(node.left);
        dfs(node.right);
        f.put(node,node.val + g.getOrDefault(node.left,0)+g.getOrDefault(node.right,0));
        g.put(node,Math.max(f.getOrDefault(node.left,0),g.getOrDefault(node.left,0)) +
                Math.max(f.getOrDefault(node.right,0),g.getOrDefault(node.right,0)));

    }


    /**
     * 层序遍历不行 部分case通不过
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null){
            return 0;
        }

        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        boolean status = true;
        int sum1 = 0;
        int sum2 = 0;
        // 1代表奇数累加
        int countSum = 1;

        while (!deque.isEmpty()){
            Deque<TreeNode> child = new LinkedList();
            while (!deque.isEmpty()){
                TreeNode node = deque.pollFirst();
                if (node.left!=null){
                    child.addLast(node.left);
                }
                if (node.right!=null){
                    child.addLast(node.right);
                }
                // 偶数累加
                if (countSum%2 ==0){
                    sum2 += node.val;
                }else {
                    sum1 += node.val;
                }
            }
            countSum+=1;
            deque = child;
        }
        return Math.max(sum1,sum2);
    }
}
