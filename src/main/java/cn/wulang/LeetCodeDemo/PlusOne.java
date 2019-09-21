package cn.wulang.LeetCodeDemo;

import java.util.Arrays;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 *示例 1:
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 *
 * 示例 2:
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 * @author wulang
 * @create 2019/9/21/9:42
 */
public class PlusOne {
    public static int[] PlusOne(int[] digits) {
        int[] temp = digits[digits.length - 1] == 9 ? new int[digits.length + 1] : new int[digits.length];
        digits[digits.length - 1] = digits[digits.length - 1] + 1;

        for (int i = 0; i < digits.length; i++) {
            temp[i] = digits[i];
        }
        if (digits[digits.length - 1] > 9){
            int x = temp[digits.length - 1];
            temp[digits.length - 1] = temp[digits.length - 1] / 10;
            temp[digits.length] = x % 10;
        }
        return temp;
    }

    /**
     * 1️⃣：末位无进位，则末位加一即可，因为末位无进位，前面也不可能产生进位，比如 45 => 46
     * 2️⃣：末位有进位，在中间位置进位停止，则需要找到进位的典型标志，即为当前位 %10后为 0，则前一位加 1，直到不为 0 为止，比如 499 => 500
     * 3️⃣：末位有进位，并且一直进位到最前方导致结果多出一位，对于这种情况，需要在第 2 种情况遍历结束的基础上，进行单独处理，比如 999 => 1000
     * @param digits
     * @return
     */
    public static int[] plusOne1(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0){
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        int[] temp = {4,9,9};
        int[] ints = plusOne1(temp);
        System.out.println(Arrays.toString(ints));
    }
}
