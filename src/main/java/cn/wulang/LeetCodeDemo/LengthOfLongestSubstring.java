package cn.wulang.LeetCodeDemo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * @author wulang
 * @create 2019/9/16/9:21
 */
public class LengthOfLongestSubstring {
    //方法一：暴力法
    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                if (repetition(s,i,j)){
                    count = Math.max(count,j - i);
                }
            }
        }
        return count;
    }
    public static boolean repetition(String s, int start, int end){
        HashSet<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character c = s.charAt(i);
            if (set.contains(c)) {return false;}
                set.add(c);
        }
        return true;
    }

    //方法二：滑动窗口
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                /**
                 * 下一次的开始节点就是为 i + 1，仍然不能解决下次开始节点为 j + 1 开始
                 */
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    //方法三：优化的滑动窗口

    /**
     * 如果 s[j]在 [i, j)范围内有与 j′重复的字符，我们不需要逐渐增加 i 。 我们可以直接跳过 [i，j′]
     * 范围内的所有元素，并将 i 变为 j′+1。
     * @param
     * @return
     */
    public static int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                /**
                 * 拿出 map 中 存在的值，并换到 i = （j + 1）为起始点
                 */
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            /**
             * 注意此处 j + 1，为了方便下次起始点i 从数组中拿出来时为 j 后面一个，避免再遍历前面已经遍历过的是元素
             */
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
    public static void main(String[] args) {
        String s = "pwwkew";
        int length = lengthOfLongestSubstring3(s);
        System.out.println(length);
    }
}
