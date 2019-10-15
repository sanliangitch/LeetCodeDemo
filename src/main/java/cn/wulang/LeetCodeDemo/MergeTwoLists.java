package cn.wulang.LeetCodeDemo;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author wulang
 * @create 2019/10/15/9:35
 */
public class MergeTwoLists {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-100);
        ListNode head = result;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }
        //此时一定有一个是非空的，将非空的连接到链表的后面
        head.next = l1 == null ? l2 : l1;
        return result.next;
    }

    /**
     * 思路
     * 标签：链表、递归
     * 这道题可以使用递归实现，新链表也不需要构造新节点，我们下面列举递归三个要素
     * 1️⃣终止条件：两条链表分别名为 l1 和 l2，当 l1 为空或 l2 为空时结束
     * 2️⃣返回值：每一层调用都返回排序好的链表头
     * 3️⃣本级递归内容：如果 l1 的 val 值更小，则将 l1.next 与排序好的链表头相接，l2 同理
     * O(m+n)，m 为 l1的长度，n 为 l2 的长度
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        if(l1.val < l2.val) {
            l1.next = mergeTwoLists1(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists1(l1, l2.next);
            return l2;
        }
    }


    public static void main(String[] args) {

    }
}
