package com.universe.origin.star.leetcode.stack.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BracketGenerating22 {
    public static void main(String[] args) {
        BracketGenerating22 bracketGenerating22 = new BracketGenerating22();
        bracketGenerating22.generateParenthesis(3);
    }

    List<String> res;
    int len;


    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        len = n;
        dfs(new StringBuffer(), 0, 1,0);
        return res;
    }


    public void dfs(StringBuffer curr, int leftDeep, int index, int leftSum) {
        // 当栈空的时候代表组合完成
        if (index > len * 2) {
            res.add(curr.toString());
            return;
        }

        // 当前选择有三种情况  只能选左边、只能选右边(最后一个位置 或者当前度=len)、两个都能选
        // 可以使用栈是不是空来判断
        if (curr.length() == 0) {
            curr.append('(');
            dfs(curr, leftDeep + 1, index + 1, leftSum + 1);
            return;
        } else if (curr.length() == 2 * len - 1) {
            curr.append(')');
            dfs(curr, leftDeep - 1, index + 1, leftSum);
            curr.deleteCharAt(curr.length() - 1);
            return;
        }

        // 走到这代表两个都能选
        if (leftDeep < len && leftSum < len) {
            curr.append('(');
            dfs(curr, leftDeep + 1, index + 1, leftSum + 1);
            curr.deleteCharAt(curr.length() - 1);
        }
        if (leftDeep > 0) {
            curr.append(')');
            dfs(curr, leftDeep - 1, index + 1, leftSum);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}
