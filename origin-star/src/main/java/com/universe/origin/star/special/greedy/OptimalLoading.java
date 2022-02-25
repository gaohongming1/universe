package com.universe.origin.star.special.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 最优装载问题
 * 给定容量 装载最多物品
 */
public class OptimalLoading {

    public static void main(String[] args) {
        OptimalLoading optimalLoading = new OptimalLoading();
        int[] weight = new int[]{2, 4, 6, 1, 8, 2, 5};
        System.out.println(Arrays.toString(optimalLoading.bestLoading(10, weight)));

        Treasure t1 = new Treasure(2, 8);
        Treasure t2 = new Treasure(6, 1);
        Treasure t3 = new Treasure(7, 9);
        Treasure t4 = new Treasure(4, 3);
        Treasure t5 = new Treasure(10, 2);
        Treasure t6 = new Treasure(3, 4);
        Treasure[] Treasures = new Treasure[]{t1, t2, t3, t4, t5, t6};
        System.out.println(Arrays.toString(optimalLoading.backpackBestLoad(Treasures, 19)));

    }

    //给定容量背包 装载最大价值的物品 计算单价进行装载
    public Treasure[] backpackBestLoad(Treasure[] ware, int w) {
        for (int i = 0; i < ware.length; i++) {
            ware[i].unitPrice = (double)ware[i].value / (double) ware[i].weight;
        }
        Arrays.sort(ware, new Comparator<Treasure>() {
            @Override
            public int compare(Treasure o1, Treasure o2) {
                return o2.unitPrice.compareTo(o1.unitPrice);
            }
        });
        System.out.println(Arrays.toString(ware));

        int count = 0;
        List<Treasure> res = new ArrayList();
        for (int i = 0; i < ware.length; i++) {
            if (count + ware[i].weight < w) {
                count += ware[i].weight;
                res.add(ware[i]);
            }
        }
        Treasure[] fres = new Treasure[res.size()];
        return res.toArray(fres);
    }


    //最优装载   给定重量，加入最多的
    public Integer[] bestLoading(int w, int[] weight) {
        //先数组排序
        int count = 0;
        Arrays.sort(weight);
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < weight.length; i++) {
            if (count + weight[i] < w) {
                res.add(weight[i]);
                count += weight[i];
            }
        }
        Integer[] fres = new Integer[res.size()];
        return res.toArray(fres);

    }
}

class Treasure {
    public int weight;
    public int value;
    public Double unitPrice;

    public Treasure(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Treasure{" +
                "weight=" + weight +
                ", value=" + value +
                ", unitPrice=" + unitPrice +
                '}';
    }
}