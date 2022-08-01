package com.company.algorithm;

public class QuickSort {
    public void sort(int[] arr, int left, int right) {
        if (arr == null || arr.length == 0)
            return;
        if (left >= right)
            return;

        int mid = left + (right - left);
        // pivot
        int pivot = arr[mid];
        int i = left, j = right;
        while (i <= j) {
            // left < pivot move to right
            while (arr[i] < pivot)
                i++;
            // right > pivot move to left
            while (arr[j] > pivot)
                j--;
            // swap i,j and move to left, right 1 step
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (left < j)
            sort(arr, left, j);
        if (right > i)
            sort(arr, i, right);
    }

    public void display(int[] arr) {
        System.out.println("=======================");
        for (int j : arr) System.out.print(j + " ");
        System.out.println();

    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 6, 1, 43, 23, -125, 412, 32, 4, 12, 31, 23, 123, 12};

        QuickSort quickSort = new QuickSort();
        quickSort.display(arr);
        quickSort.sort(arr, 0, arr.length - 1);
        quickSort.display(arr);

    }

}
