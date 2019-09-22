package cn.wulang.LeetCodeDemo;

/**
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串
 * 出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 *
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 *
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * @author wulang
 * @create 2019/9/23/7:07
 */
public class StrStr {
    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()){
            return 0;
        }
        char[] haystackChars = haystack.toCharArray();
        char[] needleChars = needle.toCharArray();

        int s1Len = haystack.length();
        int s2Len = needle.length();

        int i = 0;
        int j = 0;
        while (i < s1Len && j < s2Len){
            if (haystackChars[i] == needleChars[j]){
                i++;
                j++;
            }else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == s2Len){
            return i - j;
        }
        return -1;
    }

    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        System.out.println(strStr(haystack, needle));
    }
}
