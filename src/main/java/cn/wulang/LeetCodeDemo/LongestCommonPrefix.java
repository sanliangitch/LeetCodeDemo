package cn.wulang.LeetCodeDemo;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * @author wulang
 * @create 2019/9/25/7:38
 */
public class LongestCommonPrefix {
    /**
     * 方法一：水平扫描法
     * 思路
     *
     * 首先，我们将描述一种查找一组字符串的最长公共前缀 LCP(S1…Sn) 的简单方法。
     * 我们将会用到这样的结论：LCP(S1…Sn=LCP(LCP(LCP(S1,S2,S3),…Sn)
     *
     * 算法
     * 为了运用这种思想，算法要依次遍历字符串 [S1…Sn]，当遍历到第 i 个字符串的时候，
     * 找到最长公共前缀 LCP(S1…Si)。当 LCP(S1…Si​) 是一个空串的时候，算法就结束了。
     * 否则，在执行了 n 次遍历之后，算法就会返回最终答案 LCP(S1…Sn)。
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++){
            //判断是否包含这个子串
            int indexOf = strs[i].indexOf(prefix);
            while (strs[i].indexOf(prefix) != 0) {
                //每次删除最后一个字符，不停递减
                prefix = prefix.substring(0, prefix.length() - 1);
                //若 prefix 为 null，无任何字符串匹配
                if (prefix.isEmpty()){
                    return "";
                }
            }
        }
        return prefix;
    }

    /**
     * 算法二：水平扫描
     * 算法
     *
     * 想象数组的末尾有一个非常短的字符串，使用上述方法依旧会进行 S​ 次比较。
     * 优化这类情况的一种方法就是水平扫描。
     * 我们从前往后枚举字符串的每一列，先比较每个字符串相同列上的字符（即不同字符串相同下标的字符）然后再进行对下一列的比较。
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        for (int i = 0; i < strs[0].length() ; i++){
            //最长公共前缀不可能超过第一个字符串的大小
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                //循环比较每个字符串的当前位置是否相等，不等直接返回
                if (i == strs[j].length() || strs[j].charAt(i) != c){
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 算法三：分治
     *为了应用上述的结论，我们使用分治的技巧，将原问题 LCP(Si⋯Sj) 分成两个子问题 LCP(Si⋯S mid) 与 LCP(Smid+1,S j) ，
     * 其中 mid = (i+j) / 2。 我们用子问题的解 lcpLeft 与 lcpRight 构造原问题的解 LCP(Si⋯Sj)。
     * 从头到尾挨个比较 lcpLeft 与 lcpRight 中的字符，直到不能再匹配为止。
     * 计算所得的 lcpLeft 与 lcpRight 最长公共前缀就是原问题的解 LCP(Si⋯Sj)。
     * @param strs
     * @return
     */
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        return longestCommonPrefix(strs, 0 , strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        }
        else {
            int mid = (l + r)/2;
            String lcpLeft =   longestCommonPrefix(strs, l , mid);
            String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    String commonPrefix(String left,String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if ( left.charAt(i) != right.charAt(i) ){
                return left.substring(0, i);
            }
        }
        return left.substring(0, min);
    }
//------------------------------------------------------------------------------------------------------------------

    /**
     * 方法四：二分查找法
     *
     * 这个想法是应用二分查找法找到所有字符串的公共前缀的最大长度 L。
     * 算法的查找区间是 (0…minLen)，其中 minLen 是输入数据中最短的字符串的长度，同时也是答案的最长可能长度。
     * 每一次将查找区间一分为二，然后丢弃一定不包含最终答案的那一个。算法进行的过程中一共会出现两种可能情况：
     *
     * S[1...mid] 不是所有串的公共前缀。 这表明对于所有的 j > i S[1..j] 也不是公共前缀，于是我们就可以丢弃后半个查找区间。
     *
     * S[1...mid] 是所有串的公共前缀。 这表示对于所有的 i < j S[1..i] 都是可行的公共前缀，因为我们要找最长的公共前缀，
     * 所以我们可以把前半个查找区间丢弃。
     * @param strs
     * @return
     */
    public String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        for (String str : strs){
            minLen = Math.min(minLen, str.length());
        }
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle)){
                low = middle + 1;
            }
            else{
                high = middle - 1;
            }
        }
        return strs[0].substring(0, (low + high) / 2);
    }

        private boolean isCommonPrefix(String[] strs, int len){
            String str1 = strs[0].substring(0,len);
            for (int i = 1; i < strs.length; i++){
                if (!strs[i].startsWith(str1)){
                    return false;
                }
            }
            return true;
        }

    /**
     * 思路
     * 标签：链表
     * 当字符串数组长度为 0 时则公共前缀为空，直接返回
     * 令最长公共前缀 ans 的值为第一个字符串，进行初始化
     * 遍历后面的字符串，依次将其与 ans 进行比较，两两找出公共前缀，最终结果即为最长公共前缀
     * 如果查找过程中出现了 ans 为空的情况，则公共前缀不存在直接返回
     * 时间复杂度：O(s)，s 为所有字符串的长度之和
     * @param strs
     * @return
     */
    public static String longestCommonPrefix5(String[] strs) {
        if(strs.length == 0)
            return "";
        String ans = strs[0];
        for(int i =1;i<strs.length;i++) {
            int j=0;
            for(;j<ans.length() && j < strs[i].length();j++) {
                if(ans.charAt(j) != strs[i].charAt(j))
                    break;
            }
            ans = ans.substring(0, j);
            if(ans.equals(""))
                return ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        String strings[] = {"flower","flow","flight"};
        String prefix = strings[0];
        int i = strings[1].indexOf(prefix);
        System.out.println(longestCommonPrefix2(strings));
    }
}
