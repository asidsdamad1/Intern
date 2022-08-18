package com.company.algorithm;

public class BinarySearch {

    public static int binarySearch(int[] arr, int left, int right, int x) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == x) return mid;
            if (arr[mid] < x)
                // value at mid < x search left
                return binarySearch(arr, mid + 1, right, x);
            // value at  mid > x search right
            return binarySearch(arr, left, mid - 1, x);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 6, 12, 14, 17, 24, 27, 67, 99, 565, 3442};
        int x = 0;int result = binarySearch(arr, 0, arr.length - 1, x) + 1;

        if (result == 0)
            System.out.println(x + " không có trong mảng");
        else
            System.out.println(x + " nằm ở vị trí thứ " + result );
    }
}
