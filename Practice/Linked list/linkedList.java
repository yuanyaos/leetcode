34. Reverse Linked List (iterative)
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 public ListNode reverse(ListNode head) {
   // Write your solution here
   if (head == null) {
     return head;
   }
 
   ListNode p1 = null;
   ListNode p2 = head;
   ListNode p3 = p2;
 
   while (p2 != null) {
     p3 = p3.next;
     p2.next = p1;
     p1 = p2;
     p2 = p3;
   }
   return p1;
 }
}
 
653. Reversed Linked List (recursive)
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 public ListNode reverse(ListNode head) {
   // Assume at each layer we already have the reversed list on the RHS
 
 
   //              null
   //               /\
   //                |
   // 1->2->3->4->5->6<-7<-8
   //             h
   // Time: O(n), space: O(n)
   if (head == null || head.next == null) {
     return head;
   }
 
   ListNode newHead = reverse(head.next);
   head.next.next = head;
   head.next = null;
 
   return newHead;
 }
}
36. Middle Node Of Linked List
 
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 public ListNode middleNode(ListNode head) {
   // Write your solution here
   if (head == null) {
      return null;
   }
 
   ListNode p1 = head;
   ListNode p2 = head;
 
   // p2 proceeds with double the speed of p1, so p1 is the middle pointer
   while (p2.next != null && p2.next.next != null) {
     p1 = p1.next;
     p2 = p2.next.next;
   }
 
   return p1;
 }
}
C++:
//class ListNode {
// public:
//  int value;
//  ListNode* next;
//  ListNode(int v) : value(v), next(NULL) {}
//};
class Solution {
public:
 ListNode* middleNode(ListNode* head) {
   // write your solution here
   if (head == NULL) {
     return NULL;
   }
 
   ListNode* slow = head;
   ListNode* fast = head;
   while (fast->next != NULL && fast->next->next != NULL) {
     slow = slow->next;
     fast = fast->next->next;
   }
 
   return slow;
 }
};
 

39. Insert In Sorted Linked List
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 public ListNode insert(ListNode head, int value) {
   // Write your solution here
 
   if (head == null) {
     head = new ListNode(value);
     return head;
   }
 
   // if value is smaller than the first node
   if (value <= head.value) {
     ListNode p = new ListNode(value);
     p.next = head;
     return p;
   }
 
   ListNode p1 = head;
   ListNode p2 = head.next;
 
   while (p2 != null) {
     if (value < p2.value) {
       p1.next = new ListNode(value);
       p1 = p1.next;
       p1.next = p2;
 
       return head;
     }
     p1 = p1.next;
     p2 = p2.next;
   }
 
   // if value is larger than the last node
   p1.next = new ListNode(value);
   return head;
 }
}
 
C++
//class ListNode {
// public:
//  int value;
//  ListNode* next;
//  ListNode(int v) : value(v), next(NULL) {}
//};
class Solution {
public:
 ListNode* insert(ListNode* head, int value) {
   // write your solution here
   ListNode* newNode = new ListNode(value);
 
   // Case 1:
   if (head == NULL || value < head->value) {
     newNode->next = head;
     return newNode;
   }
 
   // Case 2:
   ListNode* p1 = head;
   ListNode* p2 = head->next;  // gaurantee head is not null
   while (p2 != NULL && value > p2->value) {
     p1 = p2;
     p2 = p2->next;
   }
 
   newNode->next = p2;
   p1->next = newNode;
 
   return head;
 }
};
 
 
37. Check If Linked List Has A Cycle
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 public boolean hasCycle(ListNode head) {
   // write your solution here
   ListNode slow = head;
   ListNode fast = head;
 
   while (fast != null && fast.next != null) {
     slow = slow.next;
     fast = fast.next.next;
     if (slow == fast) {
       return true;
     }
   }
 
   return false;
 }
}

C++:
//class ListNode {
// public:
//  int value;
//  ListNode* next;
//  ListNode(int v) : value(v), next(NULL) {
//}
 
class Solution {
public:
 bool hasCycle(ListNode* head) {
   if (head == NULL) {
     return false;
   }
 
   ListNode* slow = head;
   ListNode* fast = head;
   while (fast != NULL && fast->next != NULL) {
     slow = slow->next;
     fast = fast->next->next;
     if (slow == fast) {
       return true;
     }
   }
   return false;
 }
};

142. Linked List Cycle II (leetcode)
/**
* Definition for singly-linked list.
* class ListNode {
*     int val;
*     ListNode next;
*     ListNode(int x) {
*         val = x;
*         next = null;
*     }
* }
*/
public class Solution {
   public ListNode detectCycle(ListNode head) {
       if (head == null) {
           return null;
       }
      
       Set<ListNode> visited = new HashSet<>();
       ListNode cur = head;
       while (cur != null) {
           if (!visited.contains(cur)) {
               visited.add(cur);
               cur = cur.next;
           } else {
               return cur;
           }
       }
       return null;
   }
}
160. Intersection of Two Linked Lists
/**
* Definition for singly-linked list.
* public class ListNode {
*     int val;
*     ListNode next;
*     ListNode(int x) {
*         val = x;
*         next = null;
*     }
* }
*/
public class Solution {
   public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
       // store one list, then scan another list
       Set<ListNode> set = new HashSet<>();
      
       while (headA != null) {
           set.add(headA);
           headA = headA.next;
       }
      
       while (headB != null) {
           if (set.contains(headB)) {
               return headB;
           }
           headB = headB.next;
       }
      
       return null;
   }
}
40. Merge Two Sorted Linked Lists
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 public ListNode merge(ListNode one, ListNode two) {
   // Write your solution here
   if (one == null && two == null) {
     return null;
   }
 
   ListNode newNode = new ListNode(0);
   ListNode head = newNode;
   ListNode pOne = one;
   ListNode pTwo = two;
 
   while (pOne != null && pTwo != null) {
     if (pOne.value <= pTwo.value) {
       newNode.value = pOne.value;
       newNode.next = new ListNode(0);
       newNode = newNode.next;
       pOne = pOne.next;
     } else {
       newNode.value = pTwo.value;
       newNode.next = new ListNode(0);
       newNode = newNode.next;
       pTwo = pTwo.next;
     }
   }
 
   if (pOne == null) {
     newNode.value = pTwo.value;
     newNode.next = pTwo.next;
   } else {
     newNode.value = pOne.value;
     newNode.next = pOne.next;
   }
   /*if (pOne == null) {
     while (pTwo.next != null) {
       newNode.value = pTwo.value;
       newNode.next = new ListNode(0);
       newNode = newNode.next;
       pTwo = pTwo.next;
     }
     newNode.value = pTwo.value;
   } else {
     while (pOne.next != null) {
       newNode.value = pOne.value;
       newNode.next = new ListNode(0);
       newNode = newNode.next;
       pOne = pOne.next;
     }
     newNode.value = pOne.value;
   }*/
   return head;
 }
}
 
C++:
//class ListNode {
// public:
//  int value;
//  ListNode* next;
//  ListNode(int v) : value(v), next(NULL) {}
//};
class Solution {
public:
 ListNode* merge(ListNode* one, ListNode* two) {
   // write your solution here
   //        p1
   // 1->3->5
  
   //         p2
   // 2->3->4->6
 
   // dummy->1->2->3->3->4->5->6->null
 
   // Time: O(one.size() + two.size())
   // Space: O(1)
 
   if (one == NULL && two == NULL) {
     return NULL;
   }
 
   ListNode* dummy = new ListNode(0);
   ListNode* cur = dummy;
   ListNode* p1 = one;
   ListNode* p2 = two;
   while (p1 != NULL && p2 != NULL) {
     if (p1->value <= p2->value) {
       cur->next = p1;
       p1 = p1->next;
     } else {
       cur->next = p2;
       p2 = p2->next;
     }
     cur = cur->next;
     cur->next = NULL;
   }
 
   if (p1 != NULL) {
     cur->next = p1;
   }
 
   if (p2 != NULL) {
     cur->next = p2;
   }
 
   ListNode* newHead = dummy->next;
   delete dummy;
   return newHead;
 }
};
 
 
41. ReOrder Linked List
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 private ListNode reverse(ListNode head) {
   if (head == null || head.next == null) {
     return head;
   }
 
   ListNode prev = null;
   ListNode curr = head;
   ListNode next = null;
   while (curr != null) {
     next = curr.next;
     curr.next = prev;
     prev = curr;
     curr = next;
   }
 
   return prev;
 }
 
 private ListNode merge(ListNode one, ListNode two) {
   if (one == null && two == null) {
     return null;
   }
 
   ListNode p1 = one;
   ListNode p2 = two;
   ListNode dummy = new ListNode(0);
   ListNode cur = dummy;
   while (p1 != null && p2 != null) {
     cur.next = p1;
     p1 = p1.next;
     cur = cur.next;
 
     cur.next = p2;
     p2 = p2.next;
     cur = cur.next;
   }
   cur.next = null;
 
   if (p1 != null) {
     cur.next = p1;
   }
 
   if (p2 != null) {
     cur.next = p2;
   }
 
   return dummy.next;
 }
 
 public ListNode reorder(ListNode head) {
   // Write your solution here
 
   // Find middle
   // Reverse second list
   // Merge two lists
 
   if (head == null || head.next == null) {
     return head;
   }
 
   // Find middle
   ListNode slow = head;
   ListNode fast = head;
   while (fast.next != null && fast.next.next != null) {
     slow = slow.next;
     fast = fast.next.next;
   }
   ListNode head2 = slow.next;
   slow.next = null;
  
   // Reverse 2nd list
   head2 = reverse(head2);
 
   // Merge
   return merge(head, head2);
 }
}
 
 

42. Partition Linked List
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 public ListNode partition(ListNode head, int target) {
   // Write your solution here
   if (head == null) {
     return head;
   }
 
   ListNode dummy1 = new ListNode(0);
   ListNode dummy2 = new ListNode(1);
   ListNode p1 = dummy1;
   ListNode p2 = dummy2;
   ListNode cur = head;
   while (cur != null) {
     if (cur.value < target) {
       p1.next = cur;
       p1 = p1.next;
     } else {
       p2.next = cur;
       p2 = p2.next;
     }
     cur = cur.next;
   }
 
   p1.next = dummy2.next;
   p2.next = null;
   return dummy1.next;
  
 }
}
 
40. Merge Two Sorted Linked Lists
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 public ListNode merge(ListNode one, ListNode two) {
   // Write your solution here
   if (one == null && two == null) {
     return null;
   }
 
   ListNode dummy = new ListNode(0);
   ListNode p = dummy;
   ListNode cur1 = one;
   ListNode cur2 = two;
   while (cur1 != null && cur2 != null) {
     if (cur1.value < cur2.value) {
       p.next = cur1;
       p = p.next;
       cur1 = cur1.next;
     } else {
       p.next = cur2;
       p = p.next;
       cur2 = cur2.next;
     }
   }
 
   if (cur1 == null) {
     p.next = cur2;
   }
   if (cur2 == null) {
     p.next = cur1;
   }
 
   return dummy.next;
 }
}
 


29. Merge Sort Linked List
 
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 private ListNode merge(ListNode one, ListNode two) {
   if (one == null && two == null) {
     return null;
   }
 
   ListNode dummy = new ListNode(0);
   ListNode cur = dummy;
   ListNode p1 = one;
   ListNode p2 = two;
   while (p1 != null && p2 != null) {
     if (p1.value <= p2.value) {
       cur.next = p1;
       p1 = p1.next;
     } else {
       cur.next = p2;
       p2 = p2.next;
     }
     cur = cur.next;
   }
 
   if (p1 != null) {
     cur.next = p1;
   }
 
   if (p2 != null) {
     cur.next = p2;
   }
 
   return dummy.next;
 }
 public ListNode mergeSort(ListNode head) {
   // Write your solution here
   // Space: in-place sorting, log(n) stack frame: O(logn)
   if (head == null || head.next == null) {
     return head;
   }
 
   // Split list. Time: O(n)
   ListNode slow = head;
   ListNode fast = head;
   while (fast.next != null && fast.next.next != null) {
     slow = slow.next;
     fast = fast.next.next;
   }
   ListNode head2 = slow.next;
   slow.next = null;  // break the first link from the second link
 
   head = mergeSort(head);
   head2 = mergeSort(head2);
 
   // Merge two sorted linked list: O(n)
   ListNode mergeHead = merge(head, head2);
 
   return mergeHead;
 }
}
 
 
 
 
C++:
//class ListNode {
// public:
//  int value;
//  ListNode* next;
//  ListNode(int v) : value(v), next(NULL) {}
//};
class Solution {
private:
 ListNode* merge(ListNode* l1, ListNode* l2) {
   ListNode* dummy = new ListNode(0);
   ListNode* h = dummy;
   ListNode* p1 = l1;
   ListNode* p2 = l2;
 
   while (p1 != NULL && p2 != NULL) {
     if (p1->value < p2->value) {
       h->next = new ListNode(p1->value);
       h = h->next;
       p1 = p1->next;
     } else {
       h->next = new ListNode(p2->value);
       h = h->next;
       p2 = p2->next;
     }
   }
 
   if (p1 == NULL && p2 != NULL) {
     h->next = p2;
   } else if (p2 == NULL && p1 != NULL) {
     h->next = p1;
   }
 
   return dummy->next;
 }
 
 ListNode* getSortedList(ListNode* head) {
   if (head->next == NULL) {
     return head;
   }
 
   // get the middle node of the list
   ListNode* p1 = head;
   ListNode* p2 = head;
   while (p2->next != NULL && p2->next->next != NULL) {
     p1 = p1->next;
     p2 = p2->next->next;
   }
 
   // split into 2 sublists
   ListNode* head2 = p1->next;
   p1->next = NULL;
 
   // sort the 2 sublists recursively
   head = getSortedList(head);
   head2 = getSortedList(head2);
 
   // merge 2 sorted sublists
   ListNode* merged_list = merge(head, head2);
 
   return merged_list;
 }
public:
 ListNode* mergeSort(ListNode* head) {
   // write your solution here
   if (head == NULL || head->next == NULL) {
     return head;
   }
 
   // 1. find the middle point of the list and then split into 2 sublists
   // 2. sort the 2 sublists with recursion
   // 3. merge the 2 sorted sublists
   return getSortedList(head);
 
 }
};
 
 
223. Add Two Numbers
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      // Write your solution here
 
      ListNode p1 = l1;
      ListNode p2 = l2;
      ListNode dummy = new ListNode(0);
      ListNode p = dummy;
 
      int carrier = 0;
      int sum = 0;
      int digit = 0;
      while (p1 != null || p2 != null) {
        if (p1 == null) {
          sum = p2.value + carrier;
          p2 = p2.next;
        } else if (p2 == null) {
          sum = p1.value + carrier;
          p1 = p1.next;
        } else {
          sum = p1.value + p2.value + carrier;
          p1 = p1.next;
          p2 = p2.next;
        }
        digit = sum % 10;
        carrier = sum / 10;
 
        p.next = new ListNode(digit);
        p = p.next;
      }
 
      if (carrier != 0) {
        p.next = new ListNode(carrier);
      }
 
      return dummy.next;
    }
}
 
C++:
//class ListNode {
// public:
//  int value;
//  ListNode* next;
//  ListNode(int v) : value(v), next(NULL) {}
//};
class Solution {
public:
 ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
   // write your solution here
   if (l1 == NULL && l2 == NULL) {
     ListNode* newNode = new ListNode(0);
     return newNode;
   }
 
   // sum and carrier
   ListNode* p1 = l1;
   ListNode* p2 = l2;
   ListNode* head = new ListNode(0);
   ListNode* cur = head;
   int sum = 0, carrier = 0;
   while (p1 != NULL || p2 != NULL){
     if (p1 != NULL && p2 != NULL) {
       sum = p1->value + p2->value + carrier;
       p1 = p1->next;
       p2 = p2->next;
     } else if (p1 == NULL && p2 != NULL) {
       sum = p2->value + carrier;
       p2 = p2->next;
     } else if (p1 != NULL && p2 == NULL) {
       sum = p1->value + carrier;
       p1 = p1->next;
     }
     cur->next = new ListNode(sum % 10);
     cur = cur->next;
     carrier = sum / 10;
   }
 
   if (carrier != 0) {
     cur->next = new ListNode(carrier);
   }
 
   ListNode* newHead = head->next;
   delete head;
   return newHead;
 }
};
 


306. Check If Linked List Is Palindrome
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 private ListNode reverse(ListNode head) {
   if (head.next == null) {
     return head;
   }
 
   ListNode temp = reverse(head.next);
 
   head.next.next = head;
   head.next = null;
 
   return temp;
 }
 
 public boolean isPalindrome(ListNode head) {
   if (head == null || head.next == null) {
     return true;
   }
   // Write your solution here
   // 1. find middle node and split
   // 2. reverse the second sublist
   // 3. compare the first and second sublist
 
   // 1)
   ListNode p1 = head;
   ListNode p2 = head;
   while (p2.next != null && p2.next.next != null) {
     p1 = p1.next;
     p2 = p2.next.next;
   }
   ListNode head2 = p1.next;
   p1.next = null;
 
   // 2)
   head2 = reverse(head2);
 
   // 3)
   p1 = head;
   p2 = head2;
   while (p1 != null && p2 != null) {
     if (p1.value != p2.value) {
       return false;
     }
 
     p1 = p1.next;
     p2 = p2.next;
   }
 
   return true;
 }
}
 
C++:
//class ListNode {
// public:
//  int value;
//  ListNode* next;
//  ListNode(int v) : value(v), next(NULL) {}
//};
class Solution {
private:
 ListNode* reverse_linkedlist(ListNode* head) {
   if (head == NULL || head->next == NULL) {
     return head;
   }
 
   ListNode* prev = NULL;
   ListNode* curr = head;
   ListNode* next = NULL;
   while (curr != NULL) {
     next = curr->next;
     curr->next = prev;
     prev = curr;
     curr = next;
   }
 
   return prev;
 }
public:
 bool isPalindrome(ListNode* head) {
   // write your solution here
   // Find the middle first
   // Then reverse the second list
   // Compare two lists
 
   // Time: O(n)
   // Space: O(1)
 
   if (head == NULL || head->next == NULL) {
     return true;
   }
 
   ListNode* slow = head;
   ListNode* fast = head;
   while (fast->next != NULL || fast->next->next != NULL) {
     slow = slow->next;
     fast = fast->next->next;
   }
   ListNode* head2 = slow->next;
   slow->next = NULL;
 
   // Reverse
   head2 = reverse_linkedlist(head2);
  
   // Compare
   ListNode* p1 = head;
   ListNode* p2 = head2;
   while (p1 != NULL && p2 != NULL) {
     if (p1->value != p2->value) {
       return false;
     }
 
     p1 = p1->next;
     p2 = p2->next;
   }
 
   return true;
 
 }
};
 
 
414. Remove Linked List Elements
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 public ListNode removeElements(ListNode head, int val) {
   // Write your solution here
   if (head == null) {
     return head;
   }
 
   // create dummy node
   ListNode dummy = new ListNode(0);
   dummy.next = head;
 
   ListNode p1 = dummy;
   ListNode p2 = dummy.next;
 
   while (p2 != null) {
     if (p2.value == val) {
       p1.next = p2.next;
       p2 = p2.next;
     } else {
       p1 = p1.next;
       p2 = p2.next;
     }
   }
 
   return dummy.next;
 }
}
 
C++
//class ListNode {
// public:
//  int value;
//  ListNode* next;
//  ListNode(int v) : value(v), next(NULL) {}
//};
class Solution {
public:
 ListNode* removeElements(ListNode* head, int val) {
   // write your solution here
   ListNode* dummy = new ListNode(0);
   dummy->next = head;
 
   ListNode* prev = dummy;
   ListNode* curr = head;
 
   while (curr != NULL) {
     if (curr->value == val) {
       ListNode* temp = curr;
       prev->next = curr->next;
       curr = curr->next;
       delete temp;
       continue;
     }
     prev = curr;
     curr = curr->next;
   }
  
   ListNode* newHead = dummy->next;  // head may be CHANGED!
   delete dummy;
   return newHead;
 }
};
 
35. Reverse Linked List In Pairs
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 public ListNode reverseInPairs(ListNode head) {
   // Write your solution here
 
   // Use two pointers pointing the nodes before and after the reversed lists
   // Time: O(n)
   // Space: O(1)
   if (head == null || head.next == null) {
     return head;
   }
 
   // In total, we need four pointers
   ListNode dummy = new ListNode(0);
   ListNode p1 = dummy;
   p1.next = head;
   ListNode r1 = head;
   ListNode r2 = null;
   ListNode p2 = null;
   // r1 作为判断基准，因为r1离null有两个nodes，r2和p2的update要在判断r1之后
   while (r1 != null && r1.next != null) {
     r2 = r1.next;
     p2 = r2.next;
 
     r2.next = r1;
     r1.next = p2;
     p1.next = r2;
 
     r1 = r2;
      r2 = r2.next;
     p1 = p1.next.next;
     r1 = r1.next.next;
   }
   return dummy.next;
 }
}
 
35. Reverse Linked List In Pairs
/**
* class ListNode {
*   public int value;
*   public ListNode next;
*   public ListNode(int value) {
*     this.value = value;
*     next = null;
*   }
* }
*/
public class Solution {
 public ListNode reverseInPairs(ListNode head) {
   // Write your solution here
   // Time: O(n)
   // Space: O(n)
   if (head == null || head.next == null) {
     return head;
   }
  
   ListNode temp = head.next;
   head.next = reverseInPairs(temp.next);
   temp.next = head;
 
   return temp;
 }
}
