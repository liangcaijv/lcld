package chapter06;

/**
 * 插入排序算法实现
 */
public class InsertSort {
	public static void main(String[] args) {
		int[] arr = new int[] { 1, 5, 3, 6, 9, 2, 4, 7, 5, 8 };
		// 把数组分割成有序与无序两部分
		int temp;// 待插入数字
		int j; // 有序序列 最后一个元素的下标
		// 循环获取每一个待插入的元素
		for (int i = 1; i < arr.length; i++) {
			temp = arr[i];// a[i]即为待插入的数字
			// 从无序数列的第一项开始，此次与有序数列比较，为插入数组 留出位置 ，惊醒位移
			for (j = i - 1; j >= 0; j--) {
				System.out.println("待插入元素" + temp + " ,有序序列到第" + j);
				if (temp < arr[j]) {
					// 向后移位，给temp移出空间
					arr[j + 1] = arr[j];
				} else {
					break;// 无需在比
				}
			}
			arr[j + 1] = temp;// 此时temp已经找到自己的位置
		}
	}
}
