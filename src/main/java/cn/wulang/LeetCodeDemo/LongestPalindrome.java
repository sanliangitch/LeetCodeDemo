package cn.wulang.LeetCodeDemo;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 提示1️⃣：如何重用以前计算过的回文来计算更大的回文?
 * 提示2️⃣：如果“aba”是回文，那么“xabax”是回文吗?同样，“xabay”是回文吗?
 * 提示3️⃣：基于复杂性的提示:
 *          如果我们使用蛮力检查子字符串的每个开始和结束位置是否为回文，
 *          我们有O(n²)开始-结束对和O(n)回文检查。我们能否通过重用以前的一些计算将回文检查的时间缩短到O(1)。
 * @author wulang
 * @create 2019/9/29/7:48
 */
public class LongestPalindrome {
    /**
     * 中心扩展算法
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1){
            return "";
        }
        int start = 0, end = 0;
        /**
         * 先来解释一下为什么中心是2n-1而不是n
         * 比如有字符串abcba，这时回文子串是abcda，中心是c；又有字符串adccda，这时回文子串是adccda，中心是cc。
         * 由此可见中心点既有可能是一个字符，也有可能是两个字符，
         * 当中心为一个字符的时候有n个中心，当中心为两个字符的时候有n-1个中心，所以一共有2n-1个中心。
         * 然后for循环开始从左到右遍历，为什么会有两次expandAroundCenter，
         * 一次是i和i本身，一次是i和i+1，这就是上面说到的一个中心与两个中心。
         * 而后会去判断这两种情况下谁的回文子串最长，并标记出这个子串在原字符串中的定位，即start和end。
         */
        //回文的两种情况：
        //  aaaaa
        //  aabaa
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                /**
                 * -1主要是为了处理“两个字符”为中心的情况，i始终是指向左半中心的，-1可以理解为把右半中心这个字符占用的长度去掉，当成单字符中心处理。
                 */
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }


    public boolean isPalindromic(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    // 暴力解法
    public String longestPalindrome1(String s) {
        String ans = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j <= len; j++) {
                String test = s.substring(i, j);
                if (isPalindromic(test) && test.length() > max) {
                    ans = s.substring(i, j);
                    max = Math.max(max, ans.length());
                }
            }
        return ans;
    }

    /**
     * Manacher's Algorithm 马拉车算法
     * 这是一个经典的算法
     */

    public static void main(String[] args) {
        String string = "babad";
        System.out.println(longestPalindrome(string));
    }
}
