package leetcode25;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode p = new ListNode(1);
		ListNode head = p;
		p.next = new ListNode(2);
		p = p.next;
		p.next = new ListNode(3);
		p = p.next;
		p.next = new ListNode(4);
		p = p.next;
		p.next = new ListNode(5);
		p = p.next;
		p.next = null;
		
		Solution test = new Solution();
		ListNode result = test.reverseKGroup(head, 1);
		
		p = result;
		for(int i=0;i<5;i++){
			System.out.println(p.val);
			p = p.next;
		}
	}

}
