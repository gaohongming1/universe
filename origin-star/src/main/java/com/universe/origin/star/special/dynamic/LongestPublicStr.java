package com.universe.origin.star.special.dynamic;

public class LongestPublicStr {
    public static void main(String[] args) {
        char[] str1 = new char[]{'a','b','c','a','d','a','b'};
        char[] str2 = new char[]{'b','a','c','d','b','a'};
        System.out.println(dynamicLongestPublicStr(str1,str2));

    }

    /**
     * x,y     1
     * x-1,y   2
     * x,y-1   3
     * @param str1
     * @param str2
     * @return
     */
    public static char[] dynamicLongestPublicStr(char[]str1,char[]str2){
        //初始化dp数组
        int[][]dpSource = new int[str1.length+1][str2.length+1];
        int[][]dpValue = new int[str1.length+1][str2.length+1];

        for (int i = 0; i <= str1.length; i++) {
            dpSource[i][0] = 0;
            dpValue[i][0] = 0;
        }
        for (int i = 0; i <= str2.length; i++) {
            dpSource[0][i] = 0;
            dpValue[0][i] = 0;
        }

        //循环数组str1
        for (int i = 0; i < str1.length; i++) {
            //循环数组str2
            int x = i+1;
            for (int j = 0; j < str2.length; j++) {
                int y = j+1;

                //判断是否相等
                if (str1[i] == str2[j]){
                    dpSource[x][y] = 1;
                    dpValue[x][y] = dpValue[x-1][y-1] +1;

                }else {
                    if (dpValue[x-1][y]>dpValue[x][y-1]){
                        dpSource[x][y] = 2;
                        dpValue[x][y] = dpValue[x-1][y];
                    }else {
                        dpSource[x][y] = 3;
                        dpValue[x][y] = dpValue[x][y-1];
                    }
                }
            }
        }

        //输出最长公共子序列
        //循环数组str1
        int x = str1.length;
        int y = str2.length;

        char[] s = new char[dpValue[x][y]];
        int index = dpValue[x][y];

        while(x>0 && y>0){
            //x=y情况
            if (dpSource[x][y]==1){
                s[index-1] = str1[x-1];
                x-=1;
                y-=1;
                index--;
                continue;
            }

            //x-1,y   2
            if (dpSource[x][y]==2){
                x-=1;
                continue;
            }

            //x,y-1   3
            if (dpSource[x][y]==3){
                y-=1;
                continue;
            }
        }
        return s;
    }
}
