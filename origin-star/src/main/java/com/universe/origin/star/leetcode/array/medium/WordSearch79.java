package com.universe.origin.star.leetcode.array.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *  
 * <p>
 * 提示：
 * <p>
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */
public class WordSearch79 {
    public static void main(String[] args) {
        WordSearch79 wordSearch79 = new WordSearch79();
        char[][] board = new char[][]{
                {'a','a'}
        };
        System.out.println(wordSearch79.exist(board,"aaa"));

    }

    //使用回溯法
    public boolean exist(char[][] board, String word) {
        char[] str = word.toCharArray();
        boolean status = false;
        int[][] visited = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == str[0]){
                    visited[i][j] = 1;
                    status = calc(board,str,getCanVisitChar(board,i,j,visited,true),1,visited,status);
                    visited[i][j] = 0;
                }
                if(status){
                    return status;
                }
            }
        }
        return status;
    }

    public boolean calc(char[][] board, char[] str, List<Integer[]> canVisitChar, int index, int[][] visited,boolean status) {

        //回溯终点 代表找到一个解
        if (index == str.length){
            status = true;
            return status;
        }

        // 开始遍历
        for (int i = 0; i < canVisitChar.size(); i++) {
            Integer[] current = canVisitChar.get(i);
            // 判断是否和index位置的字符一致
            if (str[index] == board[current[0]][current[1]]) {
                //记录当前元素已经被访问
                visited[current[0]][current[1]] = 1;
                // 找到当前元素的下个可访问的数组
                status = calc(board, str, getCanVisitChar(board, current[0], current[1], visited,false), index + 1, visited,status);
                //回退
                visited[current[0]][current[1]] = 0;
            }else {
                continue;
            }
            if (status){
                return status;
            }
        }
        return status;
    }

    private List<Integer[]> getCanVisitChar(char[][] board, int y, int x, int[][] visited,boolean isFirst) {
        List<Integer[]> list = new ArrayList<>();
        int[][] result;
        //上
        if (y - 1 >= 0 && visited[y - 1][x] == 0) {
            list.add(new Integer[]{y - 1, x});
        }
        //下
        if (y + 1 < board.length && visited[y + 1][x] == 0) {
            list.add(new Integer[]{y + 1, x});
        }
        //左
        if (x + 1 < board[0].length && visited[y][x + 1] == 0) {
            list.add(new Integer[]{y, x + 1});
        }
        //右
        if (x - 1 >= 0 && visited[y][x - 1] == 0) {
            list.add(new Integer[]{y, x - 1});
        }

        return list;
    }
}
