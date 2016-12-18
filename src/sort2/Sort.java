package sort2;

import java.util.Random;

/**
 * Created by ycqfeng on 2016/12/18.
 */
public class Sort {

    /**
     * 插入法排序
     * 原理：复杂度为n^2
     * @param list 需要排序的数组，需要实现接口SortInterfaceCompareTo
     */
    static public void sort_insertion(SortInterfaceCompareTo[] list){
        SortInterfaceCompareTo key;
        int i;
        for (int j = 1 ; j < list.length ; j++){
            key = list[j];
            i = j-1;
            while ((i > -1)&&(list[i].compareTo(key))){
                list[i+1] = list[i];
                i = i-1;
            }
            list[i+1] = key;
        }
    }

    /**
     * 分治法排序
     * 效率比插入法高很多
     * @param list 需要排序的数组，需要实现接口SortInterfaceCompareTo
     * @param p 开始位置
     * @param r 结束位置
     */
    static public void sort_merge(SortInterfaceCompareTo[] list, int p, int r){
        if (p < r){
            int q = (p+r)/2;
            sort_merge(list, p, q);
            sort_merge(list, q+1, r);
            merge(list, p, q, r);
        }
    }

    /**
     * 分治法排序合并函数
     * @param list 需要合并的数组
     * @param p 开始位置
     * @param q 中间位置
     * @param r 结束位置
     */
    static private void merge(SortInterfaceCompareTo[] list, int p, int q, int r){
        int n1 = q - p + 1;
        int n2 = r - q;
        SortInterfaceCompareTo[] L,R;
        L = new SortInterfaceCompareTo[n1];
        R = new SortInterfaceCompareTo[n2];
        for (int i = 0 ; i < n1 ; i++){
            L[i] = list[p+i];
        }
        for (int j = 0 ; j < n2 ; j++){
            R[j] = list[q+j+1];
        }
        int i = 0;
        int j = 0;
        for (int k = p ; k <= r ; k++){
            if (i == L.length){
                list[k] = R[j];
                j += 1;
            }
            else if (j == R.length){
                list[k] = L[i];
                i += 1;
            }
            else {
                if (L[i].compareTo(R[j])){
                    list[k] = L[i];
                    i += 1;
                }
                else{
                    list[k] = R[j];
                    j += 1;
                }
            }
        }

    }

    public static void main(String[] args) {
        class A implements SortInterfaceCompareTo{
            int a;

            public A(int a){
                this.a = a;
            }

            @Override
            public boolean compareTo(SortInterfaceCompareTo e) {
                boolean flag;
                    A a = (A) e;
                    if (this.a > a.a) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                return flag;
            }
        }



        Random random = new Random();
        A[] list = new A[1000000];
        //String str1 = "";
        for (int i = 0 ; i < list.length ; i++){
            list[i] = new A(random.nextInt(9999999));
            //str1 += list[i].a + " ";
        }
        //System.out.println(str1);
        long start = System.currentTimeMillis();
        //Sort.sort_insertion(list);
        Sort.sort_merge(list, 0, list.length-1);
        long end = System.currentTimeMillis();
        /*String str2 = "";
        for (int i = 0 ; i < list.length ; i++){
            str2 += list[i].a + " ";
        }
        System.out.println(str2);*/



        System.out.println("运行时间：" +(end-start)+"ms");

    }
}
