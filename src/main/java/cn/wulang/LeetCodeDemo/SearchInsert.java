package cn.wulang.LeetCodeDemo;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 *
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 *
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 *
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * @author wulang
 * @create 2019/12/11/17:13
 */
public class SearchInsert {
    //[1,3,5,6]
    //5
    /**
     * 时间复杂度为：O(n)
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int i = 0;
        for (int temp : nums) {
            if (temp < target){
                i++;
            }
        }
        return i;
    }

    /**
     * 在一个有序数组中查找一个数的经典算法，二分查找法
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert2(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        /**
         * int mid = (left + right) / 2
         * 这行代码是有问题的，在 left 和 right 都比较大的时候，left + right 很有可能超过 int 类型能表示的最大值，即整型溢出
         */
        //int mid = (left + right) / 2 ;
        /**
         * 事实上，int mid = left + (right - left) / 2 在 right 很大、 left 是负数且很小的时候， right - left 也有可能超过 int
         * 类型能表示的最大值，只不过一般情况下 left 和 right 表示的是数组索引值，left 是非负数，因此 right - left 溢出的可能性很小。
         */
        //int mid = left + (right - left) / 2 ;
        /**
         * 使用“左边界索引 + 右边界索引”，然后“无符号右移 11 位”是推荐的写法。
         */
        int mid = (left + right) >>> 1 ;
        if (nums[mid] < target){
            //在右边

        }else {
            //在左边
        }
        return 0;
    }

    /**
     * 传统二分查找法模板，当退出 while 循环的时候，在返回左边界还是右边界这个问题上，比较容易出错。
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert3(int[] nums, int target) {
        int len = nums.length;
        if (nums[len - 1] < target) {
            return len;
        }

        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            // 等于的情况最简单，我们应该放在第 1 个分支进行判断
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // 题目要我们返回大于或者等于目标值的第 1 个数的索引
                // 此时 mid 一定不是所求的左边界，
                // 此时左边界更新为 mid + 1
                left = mid + 1;
            } else {
                // 既然不会等于，此时 nums[mid] > target
                // mid 也一定不是所求的右边界
                // 此时右边界更新为 mid - 1
                right = mid - 1;
            }
        }
        // 注意：一定得返回左边界 left，
        // 如果返回右边界 right 提交代码不会通过
        // 【注意】下面我尝试说明一下理由，如果你不太理解下面我说的，那是我表达的问题
        // 但我建议你不要纠结这个问题，因为我将要介绍的二分查找法模板，可以避免对返回 left 和 right 的讨论

        // 理由是对于 [1,3,5,6]，target = 2，返回大于等于 target 的第 1 个数的索引，此时应该返回 1
        // 在上面的 while (left <= right) 退出循环以后，right < left，right = 0 ，left = 1
        // 根据题意应该返回 left，
        // 如果题目要求你返回小于等于 target 的所有数里最大的那个索引值，应该返回 right

        return left;
    }

    public static void main(String[] args) {
    }
}
