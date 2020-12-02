package com.universe.origin.star.special.sort;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 1, 6, 8, 9, 4, 3, 7, 2, 7};
        heapSort(arr);
    }

    public static int[] heapSort(int[] arr) {
        //构造大顶堆  倒数第二层第一个非叶节点为length/2 -1
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            rebuidHeap(arr, i);
        }

        //调整堆结构交换
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            rebuidHeap(arr, 0);//重新对堆进行调整
        }
        System.out.println("排序完成");


        return null;
    }

    //调整堆
    public static void rebuidHeap(int[] arr, int position) {
        int temp = arr[position];

        int left = position * 2 + 1;
        int right = position * 2 + 2;
        int largest = position;

        if (left < arr.length && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right > arr.length && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != position) {
            swap(arr, largest, position);
            rebuidHeap(arr, largest);
        }

    }


    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
