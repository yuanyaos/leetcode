# -*- coding: utf-8 -*-
"""
Created on Sun Oct 20 17:24:20 2019

@author: lenovo
"""

class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        
        hash = {};
        
        for i,elem in enumerate(nums):
            comp = target-elem
            if comp in hash:
                return [hash[comp],i]
            hash[elem] = i
        return []
    
x = Solution()
nums = [2, 8, 9, 12, 7, 11, 15]
pair = x.twoSum(nums,9)
print(pair)