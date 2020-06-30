public class Solution {
    public ListNode run(ListNode head) {
        ListNode slowCur = head;
        while (slowCur != null) {
            ListNode fastCur = slowCur;
            while (fastCur.next != null) {
                if (slowCur.val == fastCur.next.val) {
                    fastCur.next = fastCur.next.next;
                } else {
                    fastCur = fastCur.next;
                }
            }
            slowCur = slowCur.next;
        }
        return head;
    }
}