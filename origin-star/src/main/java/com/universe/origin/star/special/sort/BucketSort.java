package com.universe.origin.star.special.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class BucketSort {

    public static Integer[] bucketSort(Integer[] arr,int bucketNum) {
        Integer max = arr[0];
        Integer min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        //每个桶决定存放的元素范围
        double split = (max - min) /((bucketNum-1)*1.0);
        List<List<Integer>> allBucket = new ArrayList<>();

        for (int i = 0; i < bucketNum; i++) {
            allBucket.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            int bucketIndex = (int)Math.floor((val-min)/split);
            insertSort(allBucket.get(bucketIndex),arr[i]);
        }
        return null;
    }

    /**
     * 我们选择插入排序作为桶内元素排序的方法 每当有一个新元素到来时，我们都调用该方法将其插入到恰当的位置
     * 链表思想
     */
    public static void insertSort(List<Integer> bucket, Integer data) {
        ListIterator<Integer> it = bucket.listIterator();
        boolean insertFlag = true;
        while (it.hasNext()) {
            if (data <= it.next()) {
                it.previous(); // 把迭代器的位置偏移回上一个位置
                it.add(data); // 把数据插入到迭代器的当前位置
                insertFlag = false;
                break;
            }
        }
        if (insertFlag) {
            bucket.add(data); // 否则把数据插入到链表末端
        }
    }


    public static void main(String[] args) {
        Integer[] arr = new Integer[]{4,3,1,6,8,9,4,3,7,2,7};
        bucketSort(arr,11);
    }

}
