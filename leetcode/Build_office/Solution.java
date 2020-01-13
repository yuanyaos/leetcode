import java.util.*;

public class Solution {
	private class Location{
		int x, y;
		public Location(int a, int b) {
			this.x = a;
			this.y = b;
		}
	}
	private void fillMatrix(int[][] matrix, int n) {
		for(int i=0;i<matrix.length;i++)
			for(int j=0;j<matrix[0].length;j++)
				matrix[i][j] = -1;
	}
	private void getdistanceBFS(int[][] matrix, int x, int y) {
		matrix[x][y] = 0;
		int r = matrix.length;
		int c = matrix[0].length;
		int[] dirx = new int[] {0,0,-1,1};
		int[] diry = new int[] {-1,1,0,0};
		Location start = new Location(x,y);
		
		Queue<Location> que = new LinkedList<Location>();
		que.add(start);
		int distance = 1;
		while(!que.isEmpty()) {
			int size = que.size();
			for(int s=0;s<size;s++) {
				Location cur = que.poll();
				for(int i=0;i<4;i++) {
					int next_x = cur.x+dirx[i];
					int next_y = cur.y+diry[i];
					if(next_x<0 || next_x>=r || next_x<0 || next_x>=c)
						continue;
					if(matrix[next_x][next_y]!=-1)
						continue;
					matrix[next_x][next_y] = distance;
					que.add(new Location(next_x,next_y));
				}
			}
			distance++;
		}
		return;
	}
	public int findMinDistance(int w,int h,int n) {
		int[][] matrix1 = new int[w][h];
		int[][] matrix2 = new int[w][h];
		
		for(int k=1;k<n;k++) {
			for(int i=0;i<w;i++) {
				for(int j=0;j<h;j++) {
					fillMatrix(matrix1,-1);
				}
			}
		}
	}
}
