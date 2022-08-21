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

        int iLeft = 0, iRight = 0, indexMerged = left;

        while (iLeft < sizeArrLeft && iRight < sizeArrRight) {
            if(leftArr[iLeft] <= rightArr[iRight])
                arr[indexMerged++] =  leftArr[iLeft++];
            else
                arr[indexMerged++] =  rightArr[iRight++];
        }

        while (iLeft < sizeArrLeft)
            arr[indexMerged++] = leftArr[iLeft++];

        while (iRight < sizeArrRight)
            arr[indexMerged++] = rightArr[iRight++];


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
