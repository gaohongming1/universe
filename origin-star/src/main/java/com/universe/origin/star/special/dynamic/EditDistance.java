package com.universe.origin.star.special.dynamic;

/**
 * 编辑距离
 */
public class EditDistance {
    public static void main(String[] args) {
        char[] str1 = new char[]{'f','a','m','i','l','y'};
        char[] str2 = new char[]{'q'};
        System.out.println(editDistance(str1,str2));
    }

    /**
     * 求编辑距离
     * 先构造dp表 0-x递增
     * 状态转移方程
     * min（f[i-1][j] + 1 、f[i][j-1] + 1 、f[i-1][j-1] + diff[i][j]）
     * f[i-1][j] + 1   代表 最后一位删除的情况下    x串的前 n-1 位和要比较的字符串的编辑距离 （长度不相等情况）
     *
     *f[i][j-1] + 1   代表 最后一位添加的情况下    x串的前 n-1 位和要比较的字符串的编辑距离（长度不相等情况）
     *
     * f[i-1][j-1] + diff[i][j]） 代表 长度相等情况下，最后一位 是改变 1还是不变 0 加上前面字符的编辑距离
     */
    public static int editDistance(char[] str1,char[] str2){
        int[][]dp = new int[str1.length+1][str2.length+1];
        for (int i = 0; i <= str2.length; i++) {
            dp[0][i] = i;
        }

        for (int i = 0; i <= str1.length; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                int diff = 0;
                //判断是否相等
                if (str1[i-1]!=str2[j-1]){
                    diff = 1;
                }

                int min = Math.min(dp[i-1][j]+1,dp[i][j-1]+1);

                dp[i][j] = Math.min(min,dp[i-1][j-1]+diff);
            }
        }
        return dp[str1.length][str2.length];
    }

}
