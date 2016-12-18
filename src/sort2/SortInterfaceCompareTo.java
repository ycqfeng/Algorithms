package sort2;

/**
 * Created by ycqfeng on 2016/12/18.
 */
public interface SortInterfaceCompareTo {


    /*
    * 借口作用：返回比较值
    * 大于e - 返回 1
    * 小于e - 返回 -1
    * 等于e = 返回 0
    */
    boolean compareTo(SortInterfaceCompareTo e);
}
