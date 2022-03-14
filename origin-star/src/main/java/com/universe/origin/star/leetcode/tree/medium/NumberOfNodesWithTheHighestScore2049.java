package com.universe.origin.star.leetcode.tree.medium;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。
 * <p>
 * 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
 * <p>
 * 请你返回有 最高得分 节点的 数目 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-nodes-with-the-highest-score
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberOfNodesWithTheHighestScore2049 {

    int n;
    long maxScore = 0;
    int count = 0;
    List<Integer>[] children;


    public static void main(String[] args) {
        File file = new File("/Users/gaohongming3/input2.txt");
        String str = txt2String(file);
        String[] strings = str.split(",");
        int[] array = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            array[i] = Integer.parseInt(strings[i].trim());
        }

        NumberOfNodesWithTheHighestScore2049 numberOfNodesWithTheHighestScore2049 = new NumberOfNodesWithTheHighestScore2049();
        System.out.println(numberOfNodesWithTheHighestScore2049.countHighestScoreNodes(array));

    }

    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        children = new List[n];
        for (int i = 0; i < parents.length; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 1; i < parents.length; i++) {
            int p = parents[i];
            if (p != -1) {
                children[p].add(i);
            }
        }
        //先初始化缓存
        findChild(0);
        return count;
    }

    public long findChild(int index) {
//        long score = 1;
//        int size = n - 1;
//        for (int c : children[index]) {
//            int t = findChild(c);
//            score = score * t;
//            size = size - t;
//        }
//        if (index != 0) {
//            score = score * size;
//        }
//        if (score == maxScore) {
//            count++;
//        } else if (score > maxScore) {
//            maxScore = score;
//            count = 1;
//        }
//        return n - size;


        if (children[index].size() == 0) {
            long result = n - 1;
            if (result == maxScore) {
                count++;
            } else if (result > maxScore) {
                maxScore = result;
                count = 1;
            }
            return 1;
        } else if (children[index].size() == 1) {
            long childRes = findChild(children[index].get(0));
            long result = 0;
            if (index == 0) {
                result = childRes;
            } else {
                result = childRes * (n - 1 - childRes);
            }

            if (result == maxScore) {
                count++;
            } else if (result > maxScore) {
                maxScore = result;
                count = 1;
            }
            return childRes + 1;
        } else {
            long left = findChild(children[index].get(0));
            long right = findChild(children[index].get(1));
            long result = 0;
            if (index == 0) {
                result = left * right;
            } else {
                result = left * right * (n - 1 - left - right);
            }
            if (result == maxScore) {
                count++;
            } else if (result > maxScore) {
                maxScore = result;
                count = 1;
            }
            return left + right + 1;
        }
    }

    public static String txt2String(File file) {


        StringBuilder result = new StringBuilder();


        try {

            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件

            String s = null;

            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行

                result.append(System.lineSeparator() + s);

            }

            br.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
        return result.toString();
    }

}
