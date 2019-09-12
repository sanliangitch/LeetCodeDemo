package cn.wulang.LeetCodeDemo;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * @author wulang
 * @create 2019/9/12/15:06
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int target = 8;
        int[] num = twoSum(nums,target);
        for (int i = 0 ; i < num.length ; i++){
            System.out.println(num[i]);
        }
    }
    public static int[] twoSum(int[] nums, int target) {
        int[] num = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    num[0] = j;
                    num[1] = i;
                }
            }
        }
        return num;
    }
}
