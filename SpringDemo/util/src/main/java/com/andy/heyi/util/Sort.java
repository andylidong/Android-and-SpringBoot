package com.andy.heyi.util;

import java.util.Arrays;

/**
 * @ClassName Sort
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/9/25$ 9:31 AM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/9/25$ 9:31 AM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class Sort {

    private static int[] array = new int[]{2, 3, 5, 8, 9, 0, 7, 5, 1, 6, 8, 7};

    public static void main(String[] args) {

        System.out.println("冒泡法 -----");
        bubbleSort(array);
        array = new int[]{2, 3, 5, 8, 9, 0, 7, 5, 1, 6, 8, 7};
        System.out.println("选择法 -----");
        selectSort(array);
        array = new int[]{2, 3, 5, 8, 9, 0, 7, 5, 1, 6, 8, 7};
        System.out.println("插入法 -----");
        insertSort(array);
    }


    /**
     * 冒泡法
     *
     * @param array
     */
    public static void bubbleSort(int[] array) {
        int length = array.length;
        boolean flag;
        for (int i = 0; i < length - 1; i++) {
            flag = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
            Arrays.stream(array).forEach(item -> System.out.print(item + "\t"));
            System.out.println();
        }
    }

    /**
     * 选择法
     *
     * @param array
     */
    public static void selectSort(int array[]) {
        int length = array.length;
        int minIndex;
        boolean flag = false;
        int j;
        for (int i = 0; i < length - 1; i++) {
            minIndex = i;
            j = i + 1;
            // 找到最低的位置
            while (j < length) {
                if (array[minIndex] > array[j])
                    minIndex = j;
                j++;
            }
            if (minIndex != i) {
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
                flag = true;
            }
            if (!flag) {
                break;
            }
            Arrays.stream(array).forEach(item -> System.out.print(item + "\t"));
            System.out.println();
        }
    }

    /**
     * 插入法
     *
     * @param array
     */
    public static void insertSort(int[] array) {
        int length = array.length;
        int key;
        int j;
        for (int i = 1; i < length; i++) {
            // 确定数据
            key = array[i];
            // 确定位置
            j = i - 1;
            // 找位置
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            // 交换
            if (array[j + 1] > key) array[j + 1] = key;
            Arrays.stream(array).forEach(item -> System.out.print(item + "\t"));
            System.out.println();
        }
    }

}
