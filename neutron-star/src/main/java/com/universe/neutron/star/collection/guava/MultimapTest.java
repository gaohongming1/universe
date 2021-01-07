package com.universe.neutron.star.collection.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class MultimapTest {
    public static void main(String[] args) {
        Multimap<Integer,String> multimap = ArrayListMultimap.create();
        multimap.put(1,"a");
        multimap.put(1,"b");
        multimap.put(2,"c");
        multimap.put(2,"c");
    }
}
