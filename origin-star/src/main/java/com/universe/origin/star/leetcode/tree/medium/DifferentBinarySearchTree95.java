package com.universe.origin.star.leetcode.tree.medium;

import com.universe.origin.star.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：3
 * 输出：
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 8
 */
public class DifferentBinarySearchTree95 {
    public static void main(String[] args) {

    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return recursion(1,n);
    }

    public List<TreeNode> recursion(int start, int end){
        List<TreeNode> allList = new ArrayList<>();
        if (start>end){
            allList.add(null);
            return allList;
        }
        
        // 从start 到end 循环
        for (int i = start; i <=end ; i++) {
            List<TreeNode> leftList = recursion(start,i-1);
            List<TreeNode> rightList = recursion(i+1,end);
            // 合并
            for (TreeNode lNode:leftList) {
                for (TreeNode rNode : rightList) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = lNode;
                    treeNode.right = rNode;
                    allList.add(treeNode);
                }
            }
        }
        return allList;
    }





    /*************************96题解法  96. 不同的二叉搜索树************************************/
    private HashMap<Integer, Integer> map = new HashMap<>();
    public int numTrees2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        /**
         * 有多少个点
         */
        for (int i = 2; i <= n; i++) {
            /**
             * 相当于j 当做顶点的时候
             */
            for (int j = 1; j <=i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }


    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += numTrees(i - 1) * numTrees(n - i);

        }
        if (!map.containsKey(n)) {
            map.put(n, sum);
        }
        return sum;
    }


}
