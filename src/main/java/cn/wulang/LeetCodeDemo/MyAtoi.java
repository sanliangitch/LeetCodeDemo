package cn.wulang.LeetCodeDemo;

import java.util.regex.Pattern;

/**
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 *
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，
 * 作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 *
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 *
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，
 * 则你的函数不需要进行转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^(31 − 1)]。
 * 如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 *示例 1:
 * 输入: "42"
 * 输出: 42
 *
 * 示例 2:
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 *
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。

 * 示例 4:
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 *      因此无法执行有效的转换。
 *
 * 示例 5:
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−2^31) 。
 *
 *
 * @author wulang
 * @create 2019/9/19/22:17
 */
public class MyAtoi {
    public static Pattern pattern = Pattern.compile("[0-9]*");
    public static int myAtoi(String str) {
        //用作记录字符串中可能存在的负号位数
        int zheng = 0;
        int fu = 0;
        int zxc = 0;
        StringBuffer stringBuffer1 = new StringBuffer();
        //用来记录 字符串中为数字的起始点
        int a= 0;
        //将字符串中的空格全部删除
        String s = str.replaceAll(" ", "");
        if (str.equals("") || str.equals("-") || s.length() == 0 ||str.equals("+")){
            return 0;
        }
        if (s.length() > 1 && !isNumber(String.valueOf(s.charAt(0))) && !isNumber(String.valueOf(s.charAt(1)))){
            return 0;
        }
        //判断开头是不是数字或者是等号
        if (!isNumber(String.valueOf(s.charAt(0))) && s.charAt(0) != '-' && s.charAt(0) != '+'){
            //不是数字 也不是 正负号 直接返回0
            return 0;
        }
        //先循环出需要的长度
        if (!isNumber(String.valueOf(s.charAt(0) ))){
            //在这里要拿出有效的字符串，如 "-+1" 要去掉
            for (int i = 0; i < s.length(); i++) {
                if (!isNumber(String.valueOf(s.charAt(i)))){
                    //不是数字
                    if (s.charAt(i) == '-'){
                        fu++;
                    }
                    if (s.charAt(i) == '+'){
                        zheng++;
                    }
                    a++;
                }
            }
        }
        int qq = 0;
        boolean flag = true;

        String substring = s;
        StringBuffer start = start(substring);
        if (fu % 2 != 0){
            //判断了里面负号的位数，应该表示为负号
            for (int i = 0; i < start.length(); i++) {
                if (isNumber(String.valueOf(start.charAt(i)))){
                     zxc = i;
                     break;
                }else {
                    continue;
                }
            }
            stringBuffer1.append("-");
            for (int j = zxc ; j < start.length();j++){
                stringBuffer1.append(start.charAt(j));
            }
            flag = false;
        }
        //到这里得到为正数，或为负数的字符串  stringBuffer
        if (flag){
            //为正整数
            try {
                qq = Integer.valueOf(start.toString());
            }catch (Exception r){
                qq = Integer.MAX_VALUE;
            }
        }else {
            //为负数
            try {
                qq = Integer.valueOf(start.toString());
            }catch (Exception e){
                qq = Integer.MIN_VALUE;
            }
        }
        return qq;
    }
    //进来的字符串，开始为数字
    public static StringBuffer start(String string){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            if (isNumber(String.valueOf(string.charAt(i))) || string.charAt(i) == '-' || string.charAt(i) == '+'){
                stringBuffer.append(string.charAt(i));
            }else {
                break;
            }
        }
        return stringBuffer;
    }

    public static boolean isNumber(String str){
        for(int i=str.length();--i>=0;){
            int chr=str.charAt(i);
            if(chr<48 || chr>57)
                return false;
        }
        return true;
//        return pattern.matcher(s).matches();
//        return NumberUtils.isNumber(s) ? true : false;
    }

    /**
     * 此题解用到了64位环境变量，面试官如果未要求环境变量，这种题解是一种不错的解法，可以借鉴来看一下。
     *
     * 1.首先去除字符串左右空格,不符合条件的直接return 0;
     *
     * 2.sign是符号位,start指针指向第一个数字的位置,如果第一位为数字,则sign=1,start=0,否则firstChar接收字符串第一个字符,
     * 若为“+”、“-”,sign分别赋值1、-1,start自增1,
     *
     * 3.从字符串第一个为数字的位置开始遍历,res为无符号结果,如果res大于Integer最大值且sign=1,输出Integer的最大值,
     * 反之输出Integer最小值,如果遍历到不为数字的字符,则直接返回res*sign;
     *
     * 4.如果遍历时该字符串未超范围,且无非数字字符,则返回res * sign;
     *
     * 5.完结。
     * @param str
     * @return
     */
    public static int myAtoi1(String str) {
        //去除左右两边的空行
        str = str.trim();
        if (str == null || str.length() == 0){
            return 0;
        }

        char firstChar = str.charAt(0);
        //符号位
        int sign = 1;
        //start指针指向第一个数字的位置
        int start = 0;
        //res为无符号结果
        long res = 0;
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }

        for (int i = start; i < str.length(); i++) {
            //如果不是数字，直接返回前面的结果
            if (!Character.isDigit(str.charAt(i))) {
                return (int) res * sign;
            }
            //将字符串中的数字变为long类型
            // res是long类型。比如：0123, res = 0 + 0；res = 0 * 10 + 1；res = 1 * 10 + 2；res = 12 * 10 + 3； res = 123 。
            int aaa = str.charAt(i) - '0';
            res = res * 10 + str.charAt(i) - '0';
            if (sign == 1 && res > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (sign == -1 && res > Integer.MAX_VALUE)
                return Integer.MIN_VALUE;
        }
        return (int) res * sign;
    }

    public static void main(String[] args) {
        String str = "   he  ll  o";
        String s = str.replaceAll(" ", "");
        System.out.println(s.substring(1,s.length()));
        String sss = "-123";
        System.out.println(Integer.valueOf(sss));
        String test = "   +10 123";
        System.out.println(test.trim());
        System.out.println(myAtoi1(test));
    }
}
