package cn.wulang.LeetCodeDemo;

import java.util.HashMap;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 * 输入: "()"
 * 输出: true
 *
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 *
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 *
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 *
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 * @author wulang
 * @create 2019/9/19/7:36
 */
public class IsValid {
    public static boolean isValid(String s) {
        //不为 偶数 表示绝对是无效的
        if (s.length() % 2 != 0){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> hashMap = new HashMap<>();
        hashMap.put('(',')');
        hashMap.put('{','}');
        hashMap.put('[',']');
        for (int i = 0; i < s.length() ; i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '['){
                stack.push(s.charAt(i));
            }else {
                if (stack.empty()){
                    return false;
                }
                char charAt = s.charAt(i);
                Character character = hashMap.get(stack.pop());
                if (charAt != character){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 官方解答：
     *
     * 实现的更优雅，自己用的是代码硬编程
     */
    // Hash table that takes care of the mappings.
    private HashMap<Character, Character> mappings;
    // Initialize hash map with mappings. This simply makes the code easier to read.
    public IsValid() {
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }

    public boolean isValid2(String s) {
        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // If the current character is a closing bracket.
            if (this.mappings.containsKey(c)) {
                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();
                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != this.mappings.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }
        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "]";
        boolean valid = isValid(s);
        System.out.println(valid);
    }
}
