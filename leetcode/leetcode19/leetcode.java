package leetcode19;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution test=new Solution();
		
		ListNode t=new ListNode(1);
		t.next=null;
		
		ListNode result=new ListNode(0);
		result = test.removeNthFromEnd(t, 1);
		
		System.out.println(result.val);
	}

}