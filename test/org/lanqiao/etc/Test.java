package org.lanqiao.etc;

public class Test {
  int binarySearch(int[] A, int key) {
    int begin = 0;
    int end = A.length - 1;

    // 定位中间元素，并比较
    // 相同  返回
    while (end >= begin) {
      int mid = (begin + end) >> 1;
      if (key == A[mid])
        return mid;
      else if (key < A[mid])
        end = mid - 1;
      else
        begin = mid + 1;
    }
    return -1;
  }

  int binarySearch(int[] A, int key, int begin, int end) {
    if (begin > end)
      return -1;
    int mid = (begin + end) >> 1;
    if (A[mid] == key) {
      return mid;
    } else if (A[mid] < key) {
      return binarySearch(A, key, mid + 1, end);
    } else {
      return binarySearch(A, key, begin, mid - 1);
    }
  }


  public static void main(String[] args) {
    int index = new Test().binarySearch(new int[]{2, 3, 4, 5, 6, 7, 8}, 6, 0, 6);
    System.out.println(index);
  }

}
