package leetcode206;

public class Solution {
	public ListNode reverseList(ListNode head) {
		if(head==null)
			return head;
		
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head.next;
        
        while(cur!=null) {
        	cur.next=pre;
        	pre = cur;
        	cur = next;
        	if(next==null)
        		break;
        	next = next.next;
        }
        return pre;
    }
}
