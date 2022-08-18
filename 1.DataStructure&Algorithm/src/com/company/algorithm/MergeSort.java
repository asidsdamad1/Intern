package com.company.algorithm;


public class MergeSort {
    public void merge(int arr[], int left, int mid, int right) {
        int sizeArrLeft = mid - left + 1;
        int sizeArrRight = right - mid;

        int[] leftArr = new int[sizeArrLeft];
        int[] rightArr = new int[sizeArrRight];

        for (int i = 0; i < sizeArrLeft; i++) {
            leftArr[i] = arr[left + i];
        }

        for (int i = 0; i < sizeArrRight; i++) {
            rightArr[i] = arr[mid + i + 1];
        }

        int indexLeft = 0, indexRight = 0, indexMerged = left;

        while (indexLeft < sizeArrLeft && indexRight < sizeArrRight) {
            if(leftArr[indexLeft] <= rightArr[indexRight])
                arr[indexMerged++] =  leftArr[indexLeft++];
            else
                arr[indexMerged++] =  rightArr[indexRight++];
        }

        while (indexLeft < sizeArrLeft)
            arr[indexMerged++] = leftArr[indexLeft++];

        while (indexRight < sizeArrRight)
            arr[indexMerged++] = rightArr[indexRight++];


    }

    public void sort(int arr[], int left, int right) {
        int mid;

        if (left < right) {
            mid = left + (right - left) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, mid, right);

        }
    }

    public void display(int[] arr) {
        System.out.println("=======================");
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        // khoi tao mang arr
        int[] arr = {6, 7, 0, 2, 8, 1, 3, 9, 4, 5};

        MergeSort mergeSort = new MergeSort();
        mergeSort.display(arr);
        mergeSort.sort(arr, 0, arr.length - 1);
        mergeSort.display(arr);

    }
}
