package leetcode24;

public class Solution {
	public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null)
        	return head;
        
        ListNode p0 = new ListNode(0);
        p0.next = head;
        ListNode p1 = head;
        ListNode p2 = p1.next;
        int count = 0;        
        while(p2!=null){
        	p0.next = p2;
        	p1.next = p2.next;
        	p2.next = p1;
        	if(count==0)
        		head = p2;
        	
        	p0 = p0.next.next;
        	if(p0.next==null)
        		break;
        	p1 = p0.next;
        	p2 = p1.next;
        	count = count+1;
        }
        
        return head;
    }
}
