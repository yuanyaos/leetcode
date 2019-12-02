package leetcode21;
import leetcode21.ListNode;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1==null && l2==null)
			return null;
		else if(l1==null && l2!=null)
			return l2;
		else if(l2==null && l1!=null)
			return l1;
		
        ListNode newlist = new ListNode(0);
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode pn = newlist;
        ListNode head = newlist;
        
        if(p1.val<p2.val){
        	pn.val = p1.val;
        	p1 = p1.next;
        }else{
        	pn.val = p2.val;
        	p2 = p2.next;
        }
        
        while(p1!=null || p2!=null){
        	ListNode newnode = new ListNode(0);
        	pn.next = newnode;
        	pn = pn.next;
        	if(p1==null || p2==null){
	        	if(p1==null){
	        		pn.val = p2.val;
	            	p2 = p2.next;
	        	}else if(p2==null){
	        		pn.val = p1.val;
	            	p1 = p1.next;
	        	}
        	}
        	else{
        		if(p1.val<p2.val){
                	pn.val = p1.val;
                	p1 = p1.next;
                }else{
                	pn.val = p2.val;
                	p2 = p2.next;
                }
        	}
        }
        pn.next = null;
        
        return head;
    }
}
