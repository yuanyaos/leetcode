public class Solution {
    /**
     * @param s: a string which consists of lowercase or uppercase letters
     * @return: the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
		
		int sum = 0;
		int[] lowercase = new int[26];
		int[] uppercase = new int[26];
		
		int m = s.length();
		for (int i=0;i<m;i++) {
			int index = 0;
			char c = s.charAt(i);
			if(c>='a' && c<='z') {
				index = c-'a';
				lowercase[index] += 1;
			}
			else {
				index = c-'A';
				uppercase[index] += 1;
			}
		}
		
		for(int i=0;i<26;i++) {
			if(lowercase[i]%2==0) {
				sum += lowercase[i];
			}
			else if(lowercase[i]>1 && lowercase[i]%2==1) {
				sum += lowercase[i]-1;
			}
			
			if(uppercase[i]%2==0) {
				sum += uppercase[i];
			}
			else if(uppercase[i]>1 && uppercase[i]%2==1) {
				sum += uppercase[i]-1;
			}
		}
		
        for (int i = 0; i < 26; ++i) {
            if (lowercase[i] % 2 == 1) {
                sum++;
                break;
            }
            if (uppercase[i] % 2 == 1) {
                sum++;
                break;
            }
        }
		
		return sum;
    }
}