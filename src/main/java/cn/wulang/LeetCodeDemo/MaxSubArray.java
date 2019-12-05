package cn.wulang.LeetCodeDemo;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * @author wulang
 * @create 2019/12/3/17:54
 */
public class MaxSubArray {
    //【-2，3，-1，1，-3】
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
             //如果大于0表示有用,加入到结果中去
            if (sum > 0){
                sum += num;
            } else{
                //小于0表示没用，不要加到结果集中去
                sum = num;
            }
            /**
             * 解题时，多考虑用到 Java 自带的集合类，而不要一味的单做数学题目
             */
            res = Math.max(res, sum);
        }
        return res;
    }
}
