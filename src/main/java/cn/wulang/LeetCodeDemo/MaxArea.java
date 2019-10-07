package cn.wulang.LeetCodeDemo;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 *  
 *
 * 示例:
 *
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 *
 * @author wulang
 * @create 2019/10/7/20:15
 */
public class MaxArea {

    /**
     *自己思路分析：
     *  面积为，两条边之间的距离与最短一条边的乘积
     * 会给出一组数组，用一个临时变量表示当前最大的面积
     *
     * 一个双指针，用来表示两个点之间的距离
     */

    /**
     * 方法一：暴力法
     * 复杂度分析
     *
     * 时间复杂度：O(n^2)，计算所有[n(n−1)] / 2 种高度组合的面积。
     * 空间复杂度：O(1)，使用恒定的额外空间。
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
            int length = height.length;
            int area = 0;
            for (int i = 0; i < length; i++) {
                for (int j = i + 1; j < length; j++) {
                    int min = Math.min(height[i], height[j]);
                    int temp = min * (j - i);
                    if (temp > area){
                        area = temp;
                    }
                }
            }
        return area;
    }

    /**
     *我们在由线段长度构成的数组中使用两个指针，一个放在开始，一个置于末尾。
     * 此外，我们会使用变量 maxarea 来持续存储到目前为止所获得的最大面积。 在
     * 每一步中，我们会找出指针所指向的两条线段形成的区域，更新 maxarea，
     * 并将指向较短线段的指针向较长线段那端移动一步。
     *
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n)，一次扫描。
     *
     * 空间复杂度：O(1)，使用恒定的空间。
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]){
                l++;
            } else{
                r--;
            }
        }
        return maxarea;
    }

    public static void main(String[] args) {

    }
}
