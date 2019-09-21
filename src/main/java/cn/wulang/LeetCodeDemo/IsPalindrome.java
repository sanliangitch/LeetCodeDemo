package cn.wulang.LeetCodeDemo;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 进阶:
 * 你能不将整数转为字符串来解决这个问题吗？
 * @author wulang
 * @create 2019/9/20/21:26
 */
public class IsPalindrome {
    public static boolean isPalindrome(int x) {
        String string = String.valueOf(x);
        for (int i = 0; i < string.length() / 2; i++) {
            int j = string.length() - i - 1;
            if (string.charAt(i) != string.charAt(j)){
                return false;
            }
        }
        return true;
    }

    /**
     *方法：反转一半数字
     *为了避免数字反转可能导致的溢出问题，为什么不考虑只反转 int 数字的一半？
     * 毕竟，如果该数字是回文，其后半部分反转后应该与原始数字的前半部分相同。
     *
     *现在的问题是，我们如何知道反转数字的位数已经达到原始数字位数的一半？
     *
     * 我们将原始数字除以 10，然后给反转后的数字乘上 10，所以，
     * 当原始数字小于反转后的数字时，就意味着我们已经处理了一半位数的数字。
     * @param x
     * @return
     */
    public static boolean isPalindrome1(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        /**
         * 这里的while是关键，
         * 理解回文的含义：
         * 原始数字小于反转后的数字时，就意味着我们已经处理了一半位数的数字。
         */
        while(x > revertedNumber) {
            int temp = revertedNumber * 10;
            int temp1 = x % 10;
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber/10;
    }

    public static void main(String[] args) {
        boolean palindrome = isPalindrome1(1221);
        System.out.println(palindrome);
    }
}
