package com.universe.origin.star.array.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 01.08. 零矩阵
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 *
 *
 *
 * 示例 1：
 *
 * 输入：
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出：
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2：
 *
 * 输入：
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出：
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 */
public class ZeroMatrix0108 {
    public static void main(String[] args) {

    }

    /**
     * 暴力法遍历二维数组
     * 然后找到横坐标和纵坐标
     * 根据坐标设置相应坐标的数据为0
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        Set<Integer> setX = new HashSet<>();
        Set<Integer> setY = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    setX.add(j);
                    setY.add(i);
                }
            }
        }

        //初始化0
        for (Integer index :setX) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][index] = 0;
            }
        }

        for (Integer index :setY) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[index][i] = 0;
            }
        }
    }
}
