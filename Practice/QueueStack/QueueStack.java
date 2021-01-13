280. Sort With 2 Stacks
public class Solution {
 public void sort(LinkedList<Integer> s1) {
   LinkedList<Integer> s2 = new LinkedList<Integer>();
   // [1 4 2 5 2 6 9
   // [9 6 2 5 2 4 1      min = 1
 
   // [4 2 5 2 6 9
   // [1
   //  |
 
   // [4 2 5 2 6 9
   // [1 9 6 2 5 4        min = 2
   //  |
 
   // [4 5 6 9
   // [1 2 2
   //      |
 
   // Time: n + (n - 1) + (n - 2) + ... + 1 = O(n^2)
   // Space: O(n)
 
   int index = 0;  // index labeling the end of the results in s2
   while (s1.size() != 0) {
     // Shuffle all elements from s1 to s2
     int min = Integer.MAX_VALUE;
     int count = 0;
     while (s1.size() != 0) {
       int elem = s1.pop();
       if (elem < min) {
         min = elem;
         count = 1;
       } else if (elem == min) {
         count++;
       }
       s2.push(elem);
     }
 
     // Move elements from s2 back to s1, remove min from the data
     while (s2.size() > index) {
       int elem = s2.pop();
       if (elem == min) {
         continue;
       } else {
         s1.push(elem);
       }
     }
 
 
     // Push min values in s2 and update index
     for (int i = 0; i < count; i++) {
       s2.push(min);
       index++;
     }
   }
 
   // Lastly, move all from s2 to s1
   while (s2.size() != 0) {
     s1.push(s2.pop());
   }
 }
}
 
 
31. Queue By Two Stacks
public class Solution {
 
 private Deque<Integer> sIn;
 private Deque<Integer> sOut;
 
 public Solution() {
   // Write your solution here.
   sIn = new ArrayDeque<Integer>();
   sOut = new ArrayDeque<Integer>();
 }
  private void shuffle() {
   if (!sOut.isEmpty()) {
     return;
   }
 
   while (!sIn.isEmpty()) {
     sOut.push(sIn.pop());
   }
 
   return;
 }
 
 public Integer poll() {
   shuffle();
 
   return sOut.isEmpty() ? null : sOut.pop();
 }
  public void offer(int element) {
   sIn.push(element);
 }
  public Integer peek() {
   shuffle();
  
   return sOut.isEmpty() ? null : sOut.peek();
 }
  public int size() {
   return sIn.size() + sOut.size();
 }
  public boolean isEmpty() {
   return sIn.isEmpty() && sOut.isEmpty();
 }
}
 
 
 
 
 
 
32. Stack With min()
public class Solution {
 private Deque<Integer> stack;
 private Deque<Integer> minStack;
 
 public Solution() {
   // Need a stack to record min values
   // stack:     [5 4 6 3 7 1 4
   // min stack: [5 4 4 3 3 1 1
   // Space: O(n)
   stack = new LinkedList<Integer>();
   minStack = new LinkedList<Integer>();
 }
  public int pop() {
   if (stack.isEmpty()) {
     return -1;
   }
 
   int result = stack.pop();
   if (result == minStack.peek()) {
     minStack.pop();
   }
   return result;
 }
  public void push(int element) {
   // need to judge if stack is empty. 如果是空的，则无论什么都要压入栈中
   stack.push(element);
   if (minStack.isEmpty() || element <= minStack.peek()) {
     minStack.push(element);
   }
 }
  public int top() {
   if (stack.isEmpty()) {
     return -1;
   } else {
     int result;
     result = stack.peek();
     return result;
   }
 }
  public int min() {
   if (minStack.isEmpty()) {
     return -1;
   }
   int result = minStack.peek();
   return result;
 }
}
 
 
634. Stack by Queue(s)
class Solution {
 
   private Queue<Integer> q1;
   private Queue<Integer> q2;
  
   /** Initialize your data structure here. */
   public Solution() {
      q1 = new ArrayDeque<Integer>();  // push
      q2 = new ArrayDeque<Integer>();  // buffer
   }
 
   /** Push element x onto stack. */
   public void push(int x) {
       q1.offer(x);
       return;
   }
 
   /** Removes the element on top of the stack and returns that element. */
   public Integer pop() {
       if (q1.isEmpty()) {
         return null;
       }
 
       while (q1.size() > 1) {
         q2.offer(q1.poll());
       }
       int result = q1.poll();
 
       // swap q1 and q2 to make sure q1 is always used for storing
       Queue<Integer> temp;
       temp = q1;
       q1 = q2;
       q2 = temp;
 
       return result;
   }
 
   /** Get the top element. */
   public Integer top() {
       if (q1.isEmpty()) {
         return null;
       }
 
       int result = pop();
       q1.offer(result);
 
       return result;
   }
 
   /** Returns whether the stack is empty. */
   public boolean isEmpty() {
      return q1.isEmpty() && q2.isEmpty();
   }
}

 
 
 
645. Deque By Three Stacks
public class Solution {
   private Deque<Integer> s1;
    private Deque<Integer> s2;
    private Deque<Integer> s3;
 
    public Solution() {
      // Write your solution here.
      s1 = new LinkedList<Integer>();  // First
      s2 = new LinkedList<Integer>();  // Last
      s3 = new LinkedList<Integer>();  // Buffer
    }
   
    private void shuffle(Deque<Integer> src, Deque<Integer> des) {
      if (!des.isEmpty()) {
        return;
      }
 
      // Push to buffer
      int sizeSrc = src.size();
      for (int i = 0; i < sizeSrc / 2; i++) {
        s3.push(src.pop());
      }
 
      // Pop from t2, push to t1
      while (!src.isEmpty()) {
        des.push(src.pop());
      }
 
      // Pop from buffer s3, push to s2
      while (!s3.isEmpty()) {
        src.push(s3.pop());
      }
    }
 
    public void offerFirst(int element) {
      // Time: O(1)
      s1.push(element);
    }
   
    public void offerLast(int element) {
      // Time: O(1)
      s2.push(element);
    }
   
    public Integer pollFirst() {
      // shuffle(src, des)
      // s1 is empty
      shuffle(s2, s1);
 
      return s1.isEmpty() ? null : s1.pop();
    }
   
    public Integer pollLast() {
      // shuffle(src, des)
      // s2 is des
      shuffle(s1, s2);
 
      return s2.isEmpty() ? null : s2.pop();
    }
   
    public Integer peekFirst() {
      // shuffle(src, des)
      // s1 is des
      shuffle(s2, s1);
 
      return s1.isEmpty() ? null : s1.peek();
    }
   
    public Integer peekLast() {
      // shuffle(src, des)
      // s2 is des
      shuffle(s1, s2);
 
      return s2.isEmpty() ? null : s2.peek();
    }
   
    public int size() {
      return s1.size() + s2.size();
    }
   
    public boolean isEmpty() {
      if (s1.isEmpty() && s2.isEmpty()) {
        return true;
      }
      return false;
    }
}

613. Design Circular Deque
class MyCircularDeque {
 
    // ************* % 在Java里面是remainder而不是modulo *************
    // 所以要用 headNew = (head - 1 + array.length) % array.length
    // 而不能只用 headNew = (head - 1) % array.length，否则会给负数
    private int[] array;
     private int head;
     private int tail;
 
      //** Initialize your data structure here. Set the size of the deque to be k. */
      public MyCircularDeque(int k) {
          // Must be k + 1 in order to differentiate empty and full
          array = new int[k + 1];
          head = 0;
          tail = 0;
      }
     
      //** Adds an item at the front of Deque. Return true if the operation is successful. */
      public boolean insertFront(int value) {
        // Time: O(1)
        // case 1: array is already full
        int headNew = (head - 1 + array.length) % array.length;
        if (headNew == tail) {
          return false;
        }
 
        // case 2: insert on the front
        array[head] = value;  // ************* 顺序要对，保证array[head] == 0 !!!!!! *************
        head = headNew;
        return true;
      }
     
      //** Adds an item at the rear of Deque. Return true if the operation is successful. */
      public boolean insertLast(int value) {
          // case 1: array is already full
          int tailNew = (tail + 1 + array.length) % array.length;
          if (tailNew == head) {
            return false;
          }
 
          // case 2: insert on the tail
          tail = tailNew;
          array[tail] = value;
          return true;
      }
     
      //** Deletes an item from the front of Deque. Return true if the operation is successful. */
      public boolean deleteFront() {
          if (head == tail) {
            return false;
          }
 
          head = (head - 1 + array.length) % array.length;
          return true;
      }
     
      //** Deletes an item from the rear of Deque. Return true if the operation is successful. */
      public boolean deleteLast() {
          if (head == tail) {
            return false;
          }
 
          tail = (tail + 1 + array.length) % array.length;
          return true;
      }
     
      //** Get the front item from the deque. */
      public int getFront() {
          if (head == tail) {
            return -1;
          }
 
          return array[(head + 1 + array.length) % array.length];  //****** array[head] is always 0 !!! ******
      }
     
      //** Get the last item from the deque. */
      public int getRear() {
          if (head == tail) {
            return -1;
          }
 
          return array[tail];
      }
     
      //** Checks whether the circular deque is empty or not. */
      public boolean isEmpty() {
          if (head == tail) {
            return true;
          } else {
            return false;
          }
      }
     
      //** Checks whether the circular deque is full or not. */
      public boolean isFull() {
          if ((tail + 1 + array.length) % array.length == head) {
            return true;
          } else {
            return false;
          }
      }
}
 
/**
* Your MyCircularDeque object will be instantiated and called as such:
* MyCircularDeque obj = new MyCircularDeque(k);
* boolean param_1 = obj.insertFront(value);
* boolean param_2 = obj.insertLast(value);
* boolean param_3 = obj.deleteFront();
* boolean param_4 = obj.deleteLast();
* int param_5 = obj.getFront();
* int param_6 = obj.getRear();
* boolean param_7 = obj.isEmpty();
* boolean param_8 = obj.isFull();
*/
 
Implement stack by linked list
// 头进头出
class Solution {

private ListNode head;
private int size;

public Solution() {
  head = null;
  size = 0;
}

public void push(int x) {
  curr = new ListNode(x);
  cur.next = head;
  head = cur;
  size++;
}

public Integer pop() {
  if (head == null) {
    return null;
  }

  int temp = head.value;
  ListNode prev = head;
  prev.next = null
  head = head.next;
  size--;

return prev.value;
}

}


Implement queue by linked list
class Solution {

private ListNode head;
private ListNode tail;
private int size;

public Solution() {
  head = null;
  tail = null;
  size = 0;
}

public void offer(int x) {
  tail = new ListNode(x);
  tail = tail.next;
  if (head == null) {
    head = tail;
  }
  size++;
}

public Integer poll() {
  if (head == null) {
    return null;
  }

  int temp = head.value;
  ListNode prev = head;
  prev.next = null
  head = head.next;
  size--;

  if (head == null) {
    tail = null;
  }

return prev.value;
}

}
423. Valid Parentheses
public class Solution {
   /**
    * @param s: A string
    * @return: whether the string is a valid parentheses
    */
   public boolean isValidParentheses(String s) {
       // write your code here
       // Use a stack
       if (s == null || s.length() == 0) {
           return true;
       }
      
       Deque<Character> stack = new ArrayDeque<>();
       for (int i = 0; i < s.length(); i++) {
           char cur = s.charAt(i);
           if (stack.isEmpty() && (cur == ')' || cur == '}' || cur == ']')) {
               return false;
           }
          
           if (cur == ')' || cur == '}' || cur == ']') {
               // right bracket
               // check the stack.peek();
               // pop
               if (!stack.isEmpty()) {
                   char topChar = stack.peek();
                   if (cur == ')') {
                       if (topChar == '(') {
                           stack.pop();
                       } else {
                           return false;
                       }
                   } else if (cur == '}') {
                       if (topChar == '{') {
                           stack.pop();
                       } else {
                           return false;
                       }
                   } else if (cur == ']') {
                       if (topChar == '[') {
                           stack.pop();
                       } else {
                           return false;
                       }
                   }
               }
           } else {
               // left bracket: push to the stack
               stack.push(cur);
           }
       }
       return stack.isEmpty() ? true : false;
   }
}


31. Queue By Two Stacks
public class Solution {
 Deque<Integer> stackIn;
 Deque<Integer> stackOut;
 
 public Solution() {
   // Write your solution here.
   stackIn = new LinkedList<Integer>();
   stackOut = new LinkedList<Integer>();
 }
  private void shuffleInToOut() {
   if (stackOut.isEmpty()) {
     while (!stackIn.isEmpty()) {
       stackOut.push(stackIn.poll());
     }
   }
 }
 
 public Integer poll() {
   shuffleInToOut();
   if (stackOut.isEmpty()) {
     return null;
   }
   return stackOut.poll();
 }
  public void offer(int element) {
   stackIn.push(element);
 }
  public Integer peek() {
   shuffleInToOut();
   if (stackOut.isEmpty()) {
     return null;
   }
   return stackOut.peek();
 }
  public int size() {
   return stackIn.size() + stackOut.size();
 }
  public boolean isEmpty() {
   return stackIn.isEmpty() && stackOut.isEmpty();
 }
}

32. Stack With min()
public class Solution {
 private Deque<Integer> stack;
 private Deque<Integer> minStack;
 
 public Solution() {
   // write your solution here
   stack = new LinkedList<Integer>();
   minStack = new LinkedList<Integer>();
 }
  public int pop() {
   if (stack.isEmpty()) {
     return -1;
   }
 
   int result = stack.pop();
   if (result == minStack.peek()) {
     minStack.pop();
   }
   return result;
 }
  public void push(int element) {
   // need to judge if stack is empty. 如果是空的，则无论什么都要压入栈中
   stack.push(element);
   if (minStack.isEmpty() || element <= minStack.peek()) {
     minStack.push(element);
   }
 }
  public int top() {
   if (stack.isEmpty()) {
     return -1;
   } else {
     int result;
     result = stack.peek();
     return result;
   }
 }
  public int min() {
   if (minStack.isEmpty()) {
     return -1;
   }
   int result = minStack.peek();
   return result;
 }
}