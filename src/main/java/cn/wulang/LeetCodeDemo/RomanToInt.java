package cn.wulang.LeetCodeDemo;

import java.util.HashMap;
import java.util.Map;

/**
 *罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。
 *          12 写做 XII ，即为 X + II 。
 *          27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，
 * 例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 *
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 *
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * @author wulang
 * @create 2019/9/17/7:07
 */
public class RomanToInt {
    public static int romanToInt(String s) {
        int count = 0;
        char[] chars = s.toCharArray();
        int[] ints = new int[chars.length * 2];
        int j = 0;
        int mm = 0;
        //得到特殊的情况是否存在，下标位置放在 ints 里面
        while (mm<chars.length){
            if (mm + 2 > chars.length){
                break;
            }
            String substring = s.substring(mm, mm + 2);
            if (special(substring)){
                //存在特殊字符串，下一次 mm 的起点为，mm + 2
                ints[j++] = mm;
                ints[j++] = mm + 1;
                ints[j++] = mm + 1;
                mm += 2;
            }else {
                //不存在特殊字符，下次起点为 mm + 1
                mm++;
            }
        }
        //拿到特殊位置的下标，并返回对应的值
        //如果 ints 中不存在特殊值，则不进行下面的操作
        if (ints[1] == 0){
            for (int a = 0 ; a < s.length(); a++){
                int swath = swath(s.substring(a, a + 1));
                count += swath;
            }
        }else {
            //先拿特殊值
            for (int q = 0 ; q < ints.length ; q += 3){
                //要判断删除 ints 中，后面填充的0
                if ((q == 0 && ints[1] > 0) || (q != 0 && ints[q] != 0) ){
                    String substring = s.substring(ints[q], ints[q + 1]);
                    int calculate = calculate(s.substring(ints[q], ints[q + 2] + 1));
                    count += calculate;
                    if (q != 0 && ints[q] == 0){
                        break;
                    }
                }
            }
            for (int p = 0 ; p < s.length(); p++){
                //如果存在就跳过
                boolean exist = exist(ints, p);
                if ((p > 0 && exist(ints,p)) || (ints[0] ==0 && exist(ints,p))){
                    /**
                     * 注意是跳过 本次 循环
                     */
                    continue;
                }
                String substring = s.substring(p, p + 1);
                int swath = swath(substring);
                count += swath;
            }
        }
        return count;
    }

    private static boolean exist(int[] ints, int p) {
        for (Integer integer: ints) {
            if (integer == p){
                return true;
            }
        }
        return false;
    }

    private static int calculate(String substring) {
        int i = 0;
        switch (substring){
            case "IV" :
                i = 4;
                break;
            case "IX" :
                i = 9;
                break;
            case "XL" :
                i = 40;
                break;
            case "XC" :
                i = 90;
                break;
            case "CD" :
                i = 400;
                break;
            case "CM" :
                i = 900;
                break;
        }
        return i;
    }

    private static boolean special(String substring) {
        if (substring.equals("IV") || substring.equals("IX") || substring.equals("XL")||
                substring.equals("XC")|| substring.equals("CD")|| substring.equals("CM")){
            return true;
        }
        return false;
    }

    public static int swath(String c){
        int i = 0;
        switch (c){
            case "I" :
                i = 1;
                break;
            case "V" :
                i = 5;
                break;
            case "X" :
                i = 10;
                break;
            case "L" :
                i = 50;
                break;
            case "C" :
                i = 100;
                break;
            case "D" :
                i = 500;
                break;
            case "M" :
                i = 1000;
                break;
        }
        return i;
    }

    /**
     * 官方解法：
     * 1️⃣ 首先将所有的组合可能性列出并添加到哈希表中
     * 2️⃣ 然后对字符串进行遍历，由于组合只有两种，一种是 1 个字符，一种是 2 个字符，其中 2 个字符优先于 1 个字符
     * 3️⃣ 先判断两个字符的组合在哈希表中是否存在，存在则将值取出加到结果 ans 中，
     *      并向后移2个字符。不存在则将判断当前 1 个字符是否存在，存在则将值取出加到结果 ans 中，并向后移 1 个字符
     * ④  遍历结束返回结果 ans
     * @return
     */
    public int romanToInt1(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int sum = 0;
        for (int i = 0 ; i < s.length();){
            if (i + 1 < s.length() && map.containsKey(s.substring(i,i+2))){
                Integer integer = map.get(s.substring(i, i + 2));
                sum += integer;
                i += 2;
            }else {
                Integer integer = map.get(s.substring(i, i + 1));
                sum += integer;
                i++;
            }
        }
        return sum;
    }

    /**
     * 把基础的罗马数字添加到HashMap中，遍历字符串，同时分别取出后面两位的对应整数，
     * 判断如果后面位数的值比前面的值小，那么就在总数上加上前面的数值，否则就减去前面的数值
     *
     * @param s
     */
    public static int romanToInt2(String s) {
        // 算法一：判断后面数值是否大于前面
        Map<Character, Integer> romaNumber = new HashMap<>();
        romaNumber.put('I', 1);
        romaNumber.put('V', 5);
        romaNumber.put('X', 10);
        romaNumber.put('L', 50);
        romaNumber.put('C', 100);
        romaNumber.put('D', 500);
        romaNumber.put('M', 1000);

        int firstValue = 0;
        int nextValue = 0;
        int sum = 0;

        for (int i = 0; i < s.length(); i++){
            firstValue = romaNumber.get(s.charAt(i));
            if (i == s.length()-1){
                sum += firstValue;
            }else {
                nextValue = romaNumber.get(s.charAt(i+1));
                if (firstValue >= nextValue){
                    sum += firstValue;
                }else{
                    sum -= firstValue;
                }
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        //解释: M = 1000, CM = 900, XC = 90, IV = 4.
        //"CM"  0 1 1
        String s = "IXLVIII";
        int i = romanToInt2(s);
//        String substring = s.substring(1, 3);
//        System.out.println(substring);
        System.out.println(i);
    }
}
