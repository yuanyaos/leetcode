package leetcode24;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ListNode swapPairs(ListNode head)
		ListNode head = new ListNode(1);
		ListNode p = head;
		
		p.next = new ListNode(2);
		p = p.next;
		p.next = new ListNode(3);
		p = p.next;
		p.next = new ListNode(4);
		p = p.next;
		p.next = null;
		
		Solution test = new Solution();
		ListNode result = test.swapPairs(head);
		
		p = result;
		for(int i=0;i<4;i++){
			System.out.println(p.val);
			p = p.next;
		}
	}

}
