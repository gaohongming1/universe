package com.universe.origin.star.special.dynamic;

public class EditDistance {
    public static void main(String[] args) {
        char[] str1 = new char[]{'f','a','m','i','l','y'};
        char[] str2 = new char[]{'f','r','a','m','e'};
        System.out.println(editDistance(str1,str2));
    }

    /**
     * 求编辑距离
     * 先构造dp表 0-x递增
     * 状态转移方程
     * min（f[i-1][j] + 1 、f[i][j-1] + 1 、f[i-1][j-1] + diff[i][j]）
     * @param str1
     * @param str2
     * @return
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
