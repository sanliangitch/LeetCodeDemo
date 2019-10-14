package cn.wulang.LeetCodeDemo;

import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * @author wulang
 * @create 2019/10/8/7:34
 */
public class LetterCombinations {


    static Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    static List<String> output = new ArrayList<String>();

    public static void backtrack(String combination, String next_digits) {
        // if there is no more digits to check
        //如果没有更多的数字要检查
        if (next_digits.length() == 0) {
            // the combination is done
            //组合完成了
            output.add(combination);
        }
        // if there are still digits to check
        //如果还有数字需要核对
        else {
            // iterate over all letters which map
            //遍历所有映射的字母
            // the next available digit
            //下一个可用数字
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                // append the current letter to the combination
                //将当前字母追加到组合中
                // and proceed to the next digits
                //进入下一个数字
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }

    public static List<String> letterCombinations(String digits) {
        if (digits.length() != 0){
            backtrack("", digits);
        }
        return output;
    }

    /*

     */
    public static List<String> letterCombinations2(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()){
            return ans;
        }
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
//            int length = ans.peek().length();
            while(ans.peek().length()==i){
//                int length1 = ans.peek().length();
                String t = ans.remove();
//                char[] chars = mapping[x].toCharArray();
                for(char s : mapping[x].toCharArray()){
                    ans.add(t+s);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "23";
        System.out.println(letterCombinations2(s));
    }
}
