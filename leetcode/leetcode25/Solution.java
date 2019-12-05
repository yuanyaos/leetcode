package leetcode25;

public class Solution {
	public ListNode reverseKGroup(ListNode head, int k) {        
        ListNode p0 = new ListNode(0);
		ListNode p1 = head;
		head = p0;
        ListNode p2 = p1;
        ListNode p3 = p1; 
        for(int i=0;i<k-1;i++){
        	p2 = p2.next;        	
        }
        p3 = p2.next;
        p0.next = p1;
        
        int flag = 0;
        ListNode subhead = null;
        while(p2!=null){
        	p2.next = null;
        	subhead = reverselinkedlist(p1);
        	p1.next = p3;
        	p0.next = p2;
        	p0 = subhead;
        	p1 = p3;
        	p2 = p3;
        	int ii = 0;
        	while(ii<k-1){
//        	for(ii=0;ii<k-1;ii++){        		
        		p2 = p2.next;  
        		ii = ii+1;
        		if(p2==null){
            		flag = 1;
            		break;
            	}
        	}
//            }        	
        	if(flag==1)
        		break;
        	p3 = p2.next;
        	
        }
        head = head.next;
        return head;
        
    }
	
	static public ListNode reverselinkedlist(ListNode lists){
		ListNode p0 = null;
		ListNode p1 = lists;
		ListNode p2 = p1.next;
		
		while(p1!=null){
			p1.next = p0;
			p0 = p1;
			p1 = p2;
			if(p1==null)
				break;
			p2 = p2.next;
		}
		
		return lists;
	}
}