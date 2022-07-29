package com.company.algorithm;

public class QuickSort {
    public static void quickSort(int[] arr, int left, int right) {
        if (arr == null || arr.length == 0)
            return;
        if (left >= right)
            return;

        int mid = left + (right - left);
        // giá trị chốt
        int pivot = arr[mid];
        int i = left, j = right;
        while (i <= j) {
            // giá trị bên trái < chốt thì tiến sang phải
            while (arr[i] < pivot)
                i++;
            // giá trị bên phải > chốt thì tiến sang trái
            while (arr[j] > pivot)
                j--;
            // swap giá trị tại i,j và tiến 1 bước 2 bên trái phải
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if(left < j)
            quickSort(arr, left, j);
        if(right > i)
            quickSort(arr, i, right);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 6, 1, 43, 23, -125, 412, 32, 4, 12, 31, 23, 123, 12};

        quickSort(arr, 0, arr.length - 1);

        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }

}
