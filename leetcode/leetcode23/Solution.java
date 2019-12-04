package leetcode23;

import java.util.*; 

public class Solution {
	public ListNode mergeKLists(ListNode[] lists) {
		if(lists==null || lists.length==0)
			return null;
		
		PriorityQueue<ListNode> que = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>(){
			public int compare(ListNode a, ListNode b) 
		    { 
		        return a.val-b.val; 
		    }
		});
		
		for(ListNode l:lists){
			if(l!=null)
				que.offer(l);
		}
		
		ListNode head = new ListNode(0);
		ListNode p = head;
		
		while(!que.isEmpty()){
			ListNode qout = que.poll();
			p.next = qout;
			p = p.next;
			
			if(qout.next!=null)
				que.offer(qout.next);
		}
		
		return head.next;
    }
}
