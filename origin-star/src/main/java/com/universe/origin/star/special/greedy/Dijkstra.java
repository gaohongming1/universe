package com.universe.origin.star.special.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 贪心算法
 * 将输入存储在临界矩阵中
 * 两个集合已到集合 v  未到集合s
 * 最短距离数组dist  和前驱数组p
 */
public class Dijkstra {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Integer input = scanner.nextInt();
        Integer temp = 1;
        List<Integer> v = new ArrayList();
        List<Integer> s = new ArrayList();
        List<Integer> dist = new ArrayList();
        List<Integer> p = new ArrayList();
        List<Integer> startList = new ArrayList<>();
        List<Integer> endList = new ArrayList<>();
        List<Integer> weightList = new ArrayList<>();
        startList.add(0);
        endList.add(1);
        weightList.add(2);

        startList.add(0);
        endList.add(2);
        weightList.add(5);

        startList.add(1);
        endList.add(2);
        weightList.add(2);

        startList.add(1);
        endList.add(3);
        weightList.add(6);

        startList.add(2);
        endList.add(3);
        weightList.add(7);

        startList.add(2);
        endList.add(4);
        weightList.add(1);

        startList.add(3);
        endList.add(2);
        weightList.add(2);

        startList.add(3);
        endList.add(4);
        weightList.add(4);
        List<Integer> point = new ArrayList<>();
        List<List<Integer>> map = new ArrayList<>();

//        while (temp<=input) {
//            System.out.println("输入起点");
//            Integer start = scanner.nextInt();
//            System.out.println("输入终点");
//            Integer end = scanner.nextInt();
//            System.out.println("输入权值");
//            Integer weight = scanner.nextInt();
//            startList.add(start);
//            endList.add(end);
//            weightList.add(weight);
//            temp++;
//        }
        //节点去重

        for (int i = 0; i < startList.size(); i++) {
            if (!point.contains(startList.get(i))) {
                point.add(startList.get(i));
            }
            if (!point.contains(endList.get(i))) {
                point.add(endList.get(i));
            }
        }
        Collections.sort(point);

        //初始化邻接矩阵
        for (int i = 0; i < point.size(); i++) {
            List<Integer> weight = new ArrayList<>();
            //都初始化为-1
            for (int j = 0; j < point.size(); j++) {
                weight.add(-1);
            }
            map.add(weight);
        }

        for (int i = 0; i < startList.size(); i++) {
            List<Integer> weight = map.get(startList.get(i));
            weight.set(endList.get(i), weightList.get(i));
        }
        System.out.println("临接矩阵初始化完成");

        //选择起点
        Integer startPoint = scanner.nextInt();
        //初始化 v S DIST   P
        v.add(startPoint);

        for (int i = 0; i < point.size(); i++) {
            if (!point.get(i).equals(startPoint)) {
                s.add(point.get(i));
                Integer len = map.get(startPoint).get(point.get(i));
                dist.add(len);
                if (len != -1) {
                    p.add(1);
                } else {
                    p.add(-1);
                }
                continue;
            }

            p.add(-1);
            dist.add(0);

        }
        System.out.println("开始寻找路径");
        //遍历未到数组，找到最小的
        Boolean allReceive = false;
        while (!allReceive) {
            Integer len = Integer.MAX_VALUE;
            Integer index = -1;
            for (int i = 0; i < s.size(); i++) {
                if (dist.get(s.get(i)) != -1 && dist.get(s.get(i)) < len) {
                    len = dist.get(s.get(i));
                    index = s.get(i);
                }
            }
            s.remove(index);
            System.out.println("第一个点:" + index + "距离" + len);
            dist.set(index,len);

            //更新路径信息循环 判断len+ index->当前点距离是否 小于dist[]小于就更新
            for (int i = 0; i < s.size(); i++) {
                if (map.get(index).get(s.get(i))!=-1){
                    if (dist.get(s.get(i))==-1 || dist.get(s.get(i)) > len + map.get(index).get(s.get(i))){
                        dist.set(s.get(i),len + map.get(index).get(s.get(i)));
                    }
                }
            }
            System.out.println("路径更新完毕");
            if (s.size()==0){
                allReceive=true;
            }

        }


    }
}
