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
public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(1);
		ListNode head1 = l1;
		ListNode head2 = l2;
		ListNode p1 = head1;
		ListNode p2 = head2;
		
		int[] array1 = {1,2,4};
		int[] array2 = {1,3,4};
		for(int i=1;i<3;i++){
			ListNode t1 = new ListNode(array1[i]);
			ListNode t2 = new ListNode(array2[i]);
			p1.next = t1;
			p2.next = t2;
			p1 = p1.next;
			p2 = p2.next;
		}
		
//		p1.next = null;
//		p2.next = null;
//		p1 = head1;
//		p2 = head2;
//		while(p1!=null && p2!=null){
//			System.out.println(p1.val);
//			System.out.println(" ");
//			p1 = p1.next;
//			
//			System.out.println(p2.val);
//			System.out.println(" ");
//			p2 = p2.next;
//		}
		
		Solution test=new Solution();
		ListNode result = test.mergeTwoLists(head1, head2);
		while(result!=null){
			System.out.println(result.val);
			System.out.println(" ");
			result = result.next;
		}
	}

}
