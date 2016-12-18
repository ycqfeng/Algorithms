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

    static public void sort_merge(SortInterfaceCompareTo[] list, int p, int r){
        if (p < r){
            int q = (p+r)/2;
            sort_merge(list, p, q);
            sort_merge(list, q+1, r);
            merge(list, p, q, r);
        }
    }

    static private void merge(SortInterfaceCompareTo[] list, int p, int q, int r){
        int n1 = q - p + 1;
        int n2 = r - q;
        SortInterfaceCompareTo[] L,R;
        L = new SortInterfaceCompareTo[n1+1];
        R = new SortInterfaceCompareTo[n2+1];
        for (int i = 0 ; i < n1 ; i++){
            L[i] = list[p+i];
        }
        for (int j = 0 ; j < n2 ; j++){
            R[j] = list[q+j+1];
        }
        boolean flag;
        int i = 0;
        int j = 0;
        for (int k = p ; k <= r ; k++){
            if (L[i] == null){
                flag = !R[j].compareTo(L[i]);
            }
            else {
                flag = L[i].compareTo(R[j]);
            }
            if (flag){
                list[k] = L[i];
                i += 1;
            }
            else {
                list[k] = R[j];
                j += 1;
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
                if (e == null){
                    flag = true;
                }
                else {
                    A a = (A) e;
                    if (this.a > a.a) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                }
                return flag;
            }
        }

        Random random = new Random();
        A[] list = new A[10];
        String str1 = "";
        for (int i = 0 ; i < list.length ; i++){
            list[i] = new A(random.nextInt(1000));
            str1 += list[i].a + " ";
        }
        System.out.println(str1);
        //Sort.sort_insertion(list);
        Sort.sort_merge(list, 0, list.length-1);
        String str2 = "";
        for (int i = 0 ; i < list.length ; i++){
            str2 += list[i].a + " ";
        }
        System.out.println(str2);



    }
}
