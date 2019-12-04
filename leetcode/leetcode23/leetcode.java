package leetcode23;

public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Map<String, Object>[] myArray = (Map<String, Object>[]) new Map[10];
//		ArrayList<ArrayList<ListNode>> init =  new ArrayList<ArrayList<ListNode>>();
//		
//		ArrayList<ListNode> l1 = new ArrayList<ListNode>() {
//		    {
//		        add(new ListNode(1));
//		        add(new ListNode(4));
//		        add(new ListNode(5));
//		    }
//		};
//		ArrayList<ListNode> l2 = new ArrayList<ListNode>() {
//		    {
//		        add(new ListNode(1));
//		        add(new ListNode(3));
//		        add(new ListNode(4));
//		    }
//		};
//		ArrayList<ListNode> l3 = new ArrayList<ListNode>() {
//		    {
//		        add(new ListNode(2));
//		        add(new ListNode(6));
//		    }
//		};
//		
//		init.add(l1);
//		init.add(l2);
//		init.add(l3);
//		
//		Solution test = new Solution();
//		ListNode result = test.mergeKLists(init.get(0).get(0));
		
		ListNode l1 = new ListNode(1);
		ListNode pn = l1;
		pn.next = new ListNode(4);
		pn = pn.next;
		pn.next = new ListNode(5);
		pn = pn.next;
		pn.next = null;
		
		ListNode l2 = new ListNode(1);
		pn = l2;
		pn.next = new ListNode(3);
		pn = pn.next;
		pn.next = new ListNode(4);
		pn = pn.next;
		pn.next = null;
		
		ListNode l3 = new ListNode(2);
		pn = l3;
		pn.next = new ListNode(6);
		pn = pn.next;
		pn.next = null;
		
		ListNode[] lists = new ListNode[3];
		lists[0] = l1;
		lists[1] = l2;
		lists[2] = l3;
		
		Solution test = new Solution();
		ListNode result = test.mergeKLists(lists);
		pn = result;
		for(int i=0;i<8;i++){
			System.out.println(pn.val);
			pn = pn.next;
		}			
	}

}