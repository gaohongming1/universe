package com.universe.origin.star.leetcode.array.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinCombarin599 {
    public static void main(String[] args) {
        String[] s1 = new String[]{"Shogun","Tapioca Express","Burger King","KFC"};
        String[] s2 = new String[]{"Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"};
        MinCombarin599 minCombarin599 = new MinCombarin599();
        minCombarin599.findRestaurant(s1, s2);

    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        Map<String,Integer> map2 = new HashMap<String,Integer>();


        for(int i=0;i<list1.length;i++){
            map1.put(list1[i],i);
        }

        for(int i=0;i<list2.length;i++){
            if(map1.containsKey(list2[i])){
                map2.put(list2[i],i+map1.get(list2[i]));
            }
        }

        int min = Integer.MAX_VALUE;
        List<String> res = new ArrayList<String>();
        for(String key:map2.keySet()){
            if(map2.get(key)<min){
                min = map2.get(key);
                res = new ArrayList();
                res.add(key);
            }

            if(map2.get(key) == min){
                res.add(key);
            }
        }


        return res.toArray(new String[res.size()]);
    }
}
