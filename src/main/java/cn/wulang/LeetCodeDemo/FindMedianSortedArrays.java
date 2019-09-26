package cn.wulang.LeetCodeDemo;

import java.text.DecimalFormat;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 *
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 标签：数组、二分查找、分治算法
 * @author wulang
 * @create 2019/9/21/10:26
 */
public class FindMedianSortedArrays {
    /**
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] temp = null;
        if (nums1.length == 0){
            temp = new int[nums2.length];
            System.arraycopy(nums2,0,temp,0,nums2.length);
            if (nums2.length == 1){
                return (double) nums2[0];
            }
        }
        if (nums2.length == 0){
            temp = new int[nums1.length];
            System.arraycopy(nums1,0,temp,0,nums1.length);
            if (nums1.length == 1){
                return (double)nums1[0];
            }
        }
        if (nums1.length > 0 && nums2.length > 0){
            temp = new int[nums1.length + nums2.length];
            System.arraycopy(nums1,0,temp,0,nums1.length);
            System.arraycopy(nums2,0,temp,nums1.length,nums2.length);
        }
        int[] dac = DAC(temp);
        //已经排序好的数组
        Double a;
        //判断数组长度是奇数还是偶数
        if (dac.length % 2 == 0){
            //偶数
            int i = dac[dac.length / 2];
            int j = dac[(dac.length / 2) - 1];
            int m = i + j;
            DecimalFormat df = new DecimalFormat("0.0");
            String format = df.format((double) m / 2);
            a = Double.valueOf(format);
        }else {
            //奇数
            int i = dac[dac.length / 2];
            a = Double.valueOf(dac[dac.length / 2]);
        }
        return a;
    }


    /**
     * 分治算法
     * @param nums
     * @return
     */
    public static int[] DAC(int[] nums){
        int[] temp = new int[nums.length];
        int[] ints = mergeSort(nums, 0, nums.length - 1, temp);
        return ints;
    }

    //分开
    public static int[] mergeSort(int[] arr,int left,int right,int[] temp){
        int[] merge = new int[temp.length];
        if (left < right){
            int mid = (left + right) / 2 ;
            //向左递归分解
            mergeSort(arr,left,mid,temp);
            //向右递归分解
            mergeSort(arr,mid + 1,right,temp);
            //合并
            merge = merge(arr, left, mid, right, temp);
        }
        return merge;
    }

    //合并
    /**
     *
     * @param arr  要排序的数组
     * @param left  第一个数组的左指针
     * @param rigth 最后一个指针
     * @param mid   当中一个指针，mid + 1 时为第二个数组的头指针
     * @param temp   临时数组
     */
    public static int[] merge(int[] arr,int left,int mid,int rigth,int[] temp){
        int i = left;
        int j = mid + 1 ;
        int t = 0;

        //第一步
        while (i <= mid && j <= rigth){
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                ++t;
                ++i;
            }else {
                temp[t] = arr[j];
                ++t;
                ++j;
            }
        }
        //第二步 如果数组还有剩余的全部填充到temp数组里面去
        while (i <= mid){
            temp[t] = arr[i];
            ++t;
            ++i;
        }
        while (j <= rigth){
            temp[t] = arr[j];
            ++t;
            ++j;
        }
        //第三步  将数组全部合并
        t = 0;
        int tempLeft = left;
        while (tempLeft <= rigth){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
        return arr;
    }

    /**
     * 二分查找
     * @param ints
     * @return
     */
    public static int binarySearch(int[] ints,int target){

        return 0;
    }

    /**
     * 
     * @param A
     * @param B
     * @return
     */
    public double findMedianSortedArrays1(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) {
            // 保证 m <= n
            return findMedianSortedArrays1(B,A);
        }
        int iMin = 0, iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = (m + n + 1) / 2 - i;
            if (j != 0 && i != m && B[j-1] > A[i]){
                // i 需要增大
                iMin = i + 1;
            }
            else if (i != 0 && j != n && A[i-1] > B[j]) {
                // i 需要减小
                iMax = i - 1;
            }
            else {
                // 达到要求，并且将边界条件列出来单独考虑
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }
                // 奇数的话不需要考虑右半部分
                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }
                //如果是偶数的话返回结果
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
//        int[] arr = {5,6,3,0,1,5,7,9,2};
//        int[] dac = DAC(arr);
        int[] a = {};
        int[] b = {1};
//        System.out.println(Arrays.toString(dac));
        System.out.println(findMedianSortedArrays(a, b));
    }
}
