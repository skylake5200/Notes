import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Multsort {
    /**
     * Multsort class contains a variety of sorting methods:
     * 1.bubbleSort
     * 2.simpleSelectionSort
     * 3.straightInsertionSort
     * 4.shellSort
     * 5.heapSort
     * 6.mergingSort
     * 7.quickSort
     * 8.radixSort
     * From the simplicity of the algorithm, we divide above seven algorithms into
     * two categories.
     * Simple algorithms : bubbleSort simpleSelectionSort straightInsertionSort
     * Improve algorithms : shellSort heapSort mergingSort quickSort
     */

    // 1.bubbleSort
    public static void bubbleSort(int[] arrays){

        boolean flag = true;
        for (int i = 1; i <= arrays.length - 1 && flag; i++) {

            flag = false;
            for (int j = 0; j < arrays.length - i; j++) {

                if (arrays[j] > arrays[j + 1]){

                    swap(arrays, j);
                    flag = true;
                    System.out.println("输出提示");
                }
            }
        }
    }
    private static void swap(int[] arrays, int k){

        int temp = arrays[k];
        arrays[k] = arrays[k + 1];
        arrays[k + 1] = temp;
    }


    private static void swap(int[] arrays, int i, int min){

        int temp = arrays[i];
        arrays[i] = arrays[min];
        arrays[min] = temp;
    }

    // 2.simpleSelectionSort
    public static void simpleSelectionSort(int[] arrays){

        int min = 0;
        for (int i = 0; i < arrays.length - 1; i++) { // the number of comparisons

            min = i;
            for (int j = i + 1; j < arrays.length; j++) { // find the subscript of minimum

                if (arrays[min] > arrays[j]){

                    min = j;
                }
            }
            if (i != min){

                swap(arrays, i, min);
            }
        }
    }
    // 3.straightInsertionSort
    public static void straightInsertionSort(int[] arrays){

        int i, j, temp = 0;
        for (i = 1; i < arrays.length; i++) {

            if (arrays[i - 1] > arrays[i]){

                temp = arrays[i];

                for (j = i - 1; j >= 0 && arrays[j] > temp; j--) {

                    arrays[j + 1] = arrays[j];
                }
                arrays[j + 1] = temp;
            }


        }
    }
    // 4.shellSort
    public static void shellSort(int[] arrays){
        int gap = 1, i, j, len = arrays.length;
        int temp;
        while (gap < len / 3) {
            gap = gap * 3 + 1;
            System.out.println("gap = " + gap);
        }

        for (; gap > 0; gap /= 3)

            for (i = gap; i < len; i++) {

                temp = arrays[i];
                for (j = i - gap; j >= 0 && arrays[j] > temp; j -= gap)

                    arrays[j + gap] = arrays[j];

                arrays[j + gap] = temp;
            }
    }
    // 5.heapSort
    public static void heapSort(int[] arrays){
        /**
         * 1. build heap
         * 2. adjustment heap
         * 3. heap sort
         */
        int i, temp;
        int length = arrays.length;
        for (i = length / 2 - 1; i >= 0; i--) {

            System.out.println("i = " + i);
            buildMaxHeap(arrays, i, length - 1);
        }

        // adjustment heap and heap sort
        for (i = length - 1; i > 0; i--) {

            temp = arrays[0];
            arrays[0] = arrays[i];
            arrays[i] = temp;

            buildMaxHeap(arrays, 0, i - 1);
        }
    }

    /**
     * build max heap
     */
    private static void buildMaxHeap(int[] arrays, int start, int end){

        int temp, j;
        temp = arrays[start];

        for (j = 2 * start + 1 ; j <= end; j = 2 * j + 1) {

            if (j < end && arrays[j] < arrays[j + 1]){

                ++j;
            }
            if (temp >= arrays[j]){

                break;
            }
            arrays[start] = arrays[j];
            start = j;
        }
        arrays[start] = temp;

    }

    // 6.mergingSort
    public static void mergingSort(int[] arrays){

        divideAndConquer(arrays, 0, arrays.length - 1);

    }
    private static void divideAndConquer(int[] arrays, int L, int R){

        int M = (L + R) / 2;
        if (L == R){

            return;
        }else{

            divideAndConquer(arrays, L, M);
            System.out.println("M = " + M);
            divideAndConquer(arrays, M + 1, R);
            mergingSort_Result(arrays, L, M, R);
        }

    }
    private static void mergingSort_Result(int[] arrays, int L, int M, int R){

        // 把两个数组分开
        int LEFT_SIZE = M - L + 1;
        int RIGHT_SIZE = R - M;

        int[] arrLeft = new int[LEFT_SIZE];
        int[] arrRight = new int[RIGHT_SIZE];

        int i, j, k;

        for (i = L; i <= M; i++){

            System.out.println("M = * " + M);
            arrLeft[i - L] = arrays[i];
        }

        for (j = M + 1; j <= R; j++){

            arrRight[j - M - 1] = arrays[j];
        }

        for (int l = 0; l < LEFT_SIZE; l++) {

            System.out.print(" " + arrLeft[l]);
        }

        for (int l = 0; l < RIGHT_SIZE; l++) {

            System.out.print(" " + arrRight[l]);
        }

        System.out.println("\n");
        i = 0; j = 0; k = L;
        while (i < LEFT_SIZE && j < RIGHT_SIZE){

            if (arrLeft[i] > arrRight[j]){
                // 从小到大排序
                arrays[k] = arrRight[j];
                j++;
                k++;
            }else {

                arrays[k] = arrLeft[i];
                i++;
                k++;
            }
        }
        while (i < LEFT_SIZE){

            arrays[k] = arrLeft[i];
            k++;
            i++;
        }
        while (j < RIGHT_SIZE){

            arrays[k] = arrRight[j];
            j++;
            k++;
        }
    }

    // 7.quickSort
    public static void quickSort(int[] arrays){

        qSort(arrays, 0, arrays.length - 1);

    }

    private static void qSort(int[] arrays, int L, int R){

        // 对L到R之间的区间元素进行快速排序
        if (L >= R){

            return;
        }
        // 以最左边的元素作为基准点
        int pivot = arrays[L];
        int i = L, j = R;
        // 循环中做的是 如何令小于pivot的元素排在其左边，大于pivot的元素排在右边
        while (i < j){

            while (arrays[j] >= pivot && i < j){

                j--;
            }
            while (arrays[i] <= pivot && i < j){

                i++;
            }

            if (i < j){

                int temp = arrays[j];
                arrays[j] = arrays[i];
                arrays[i] = temp;
            }
        }
        // 将基准点与i位置元素进行交换
        arrays[L] = arrays[i];
        arrays[i] = pivot;

        qSort(arrays, L, i - 1);
        qSort(arrays, i + 1, R);

    }

    // 8.radixSort
    public static void radixSort(int[] arrays){
        // 基数排序是桶排序的扩展，他的基本思想是：将整数按数位切割成不同的数字，然后按每个位数分别比较
        // int arrays[] = {232,159,656,30,5822,1,0,999,304,205,851,152};

        // count保存最大元素的位数
        int count = getCount(getMaxValue(arrays));
        System.out.println("count = " + count);



    }

    // 返回给定整数的最大位数，例如： 999,则返回 3
    private static int getCount(int num){

        int count = 0;
        if (num == 0){

            return 1;
        }
        while (num != 0){
            // 999
            count++;
            num = num / 10;
        }
        return  count;
    }

    private static int getMaxValue(int[] arrays){

        int max = arrays[0];
        for (int i = 1; i < arrays.length; i++){

            if (arrays[i] > max){

                max = arrays[i];
            }
        }
        return max;
    }

}