# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        result = ListNode(0)
        p = result
        carry = 0
        
        while l1 or l2 or carry:
            v1 = l1.val if l1 else 0
            v2 = l2.val if l2 else 0
            carry, remainder = divmod(v1+v2+carry,10)
            
            p.next = ListNode(remainder)
            p = p.next
            
            l1 = l1.next if l1 else None
            l2 = l2.next if l2 else None
            
        return result.next