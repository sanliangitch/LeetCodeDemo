package cn.wulang.LeetCodeDemo;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * @author wulang
 * @create 2019/10/7/20:55
 */
public class ThreeSum {

    /**
     *自己思路：
     *  先确定a，再拿到 b ， 通过 a ， b 确定剩余数组中是否存在 c ，且 c != a && c ！= b
     */

    /**
     * 标签：数组遍历
     * 首先对数组进行排序，排序后固定一个数 nums[i]，再使用左右指针指向 nums[i]后面的两端，数字分别为 nums[L] 和 nums[R]，
     * 计算三个数的和 sum 判断是否满足为 0，满足则添加进结果集
     * 如果 nums[i]大于 0，则三数之和必然无法等于 0，结束循环
     * 如果 nums[i] = nums[i−1]，则说明该数字重复，会导致结果重复，所以应该跳过
     * 当 sum = 0 时，nums[L] = nums[L+1] 则会导致结果重复，应该跳过，L++
     * 当 sum = 0 时，nums[R] = nums[R−1] 则会导致结果重复，应该跳过，R--
     * 时间复杂度：O(n^2)，n 为数组长度
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(nums == null || len < 3) return ans;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len ; i++) {
            if(nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            /**
             * 表示前面一个数已经被找过，后面一个数再找就是重复
             */
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] temp = new int[6];
        //[-1,0,1,2,-1,-4]
        temp[0] = -1;
        temp[1] = 0;
        temp[2] = 1;
        temp[3] = 2;
        temp[4] = -1;
        temp[5] = -4;
        System.out.println(threeSum(temp));
    }
}
