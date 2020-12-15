package 牛客网刷题;

import java.util.ArrayList;

/**
 * @author guoshoujing
 * @create 2020-12-11 10:17 上午
 */
public class Top_K {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        int adjustIndex = k / 2;

        //建立大顶堆
        while(adjustIndex-- > 0){
            adjustHeap(input, adjustIndex, k);
        }

        int begin = k, end = input.length - 1;
        for(; begin <= end; begin++){
            if(input[begin] < input[0]){
                swap(0, begin, input);
                adjustHeap(input, 0, k);
            }
        }

        ArrayList<Integer> result = new ArrayList<>(k);
        for(int i = k - 1; i >= 0; i--){
            result.add(input[i]);
        }
        return result;
    }

    private void adjustHeap(int arr[], int index, int k){
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int max;
        if(left >= k){
            return;
        }
        if(right == k){
            max = arr[index] > arr[left] ? index : left;
        } else {
            max = arr[left] > arr[right] ? left : right;
            max = arr[max] > arr[index] ? max : index;
        }
        if(swap(max, index, arr)){
            adjustHeap(arr, max, k);
        }
    }

    private boolean swap(int i, int j, int[] arr){
        if(i == j){
            return false;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {6,23,12,52,15,23,22,21,3,10};
        System.out.println(new Top_K().GetLeastNumbers_Solution(arr, 3));
    }
}
