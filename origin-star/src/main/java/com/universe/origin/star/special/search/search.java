package com.universe.origin.star.special.search;

import java.util.Arrays;

public class search {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer index = interpolationSearch(arr, 7);
        System.out.println(index);
        Integer index2 = FibonacciSearch(arr, 7);
        System.out.println(index2);

    }

    public static Integer FibonacciSearch(Integer[] arr, Integer num) {
        //根据斐波那契额数组找到要补充的长度
        Integer[] f = Fibonacci();
        Integer tempLength = 0;
        Integer fiboIndex = 0;
        for (int i = 1; i < f.length; i++) {
            if (arr.length < f[i] && arr.length > f[i - 1]) {
                tempLength = f[i];
                fiboIndex = i;
                break;
            }
        }

        //补充数组
        Integer[] bakArr = Arrays.copyOf(arr, tempLength);
        for (int i = arr.length; i < bakArr.length; i++) {
            bakArr[i] = arr[arr.length - 1];
        }
        Integer low = 0;
        Integer hight = bakArr.length - 1;

        //进行查找
        while (low < hight) {
            Integer mid = low + f[fiboIndex - 1] - 1;
            if (num < bakArr[mid]) {
                hight = mid - 1;
                fiboIndex--;

            } else if (num > bakArr[mid]) {
                low = mid + 1;
                fiboIndex -= 2;
            } else {
                if (mid <= hight) {
                    return mid;
                } else {
                    return hight;
                }
            }
        }
        return -1;
    }

    public static Integer[] Fibonacci() {
        Integer[] f = new Integer[20];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < 20; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static Integer interpolationSearch(Integer[] arr, Integer num) {
        Integer hight = arr.length - 1;
        Integer low = 0;
        if (hight.equals(low) && !low.equals(num)) {
            ;
            return -1;
        }
        Integer mid = low + (num - arr[low]) * (hight - low) / (arr[hight] - arr[low]);
        while (low < hight) {
            if (num < arr[mid]) {
                hight = mid - 1;
            } else if (num > arr[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
