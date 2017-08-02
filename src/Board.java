import java.util.ArrayList;

public class Board {
	char [][] grid = new char [3][3];
	int score;
	
	public char[][] get(){
		return grid;
	}
	
	Board (){
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				this.grid[i][j]='-';
			}
		}
	}
	
	public  Boolean move (int x , int y ,char a) {
		char [][] q =get();
		if (q[x][y]=='-') {
			q[x][y]=a;
			return true;
			
		}
		else {
			return false;
		}
	}

	public  void print () {
		char [][] q =get();
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				System.out.printf("%c ", q[i][j]);
			}
			System.out.printf("\n");
		}
	}

	public  int status () {
		char [][] q =get();
		for (int i=0 ;i<3;i++) {
			int temp1=q[i][0];
			int temp2=q[0][i];
			int count1=0;
			int count2=0;
			for (int j=0;j<3;j++) {
				if (q[i][j]==temp1 && q[i][j]!='-')
					count1++;
				if (q[j][i]==temp2 && q[j][i]!='-')
					count2++;
			}
			if (count1==3) {
				TicTacToe.setcolor(i,0,i,1,i,2);
				return 1;
			}	
			if (count2 == 3) {
				TicTacToe.setcolor(0,i,1,i,2,i);
				return 1;
			}
		}

		if (q[0][0] == q[1][1] && q[1][1]==q[2][2] && q[1][1]!='-'){
			TicTacToe.setcolor(0,0,1,1,2,2);
			return 1; // win
		}
		else if (q[0][2] == q[1][1] && q[1][1]==q[2][0] && q[1][1]!='-'){
			TicTacToe.setcolor(0,2,1,1,2,0);
			return 1;
		}
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				if (q[i][j]=='-')
					return 2; //continue
			}
		}
		return 3; // draw
	}

	public int[][] post() {
		
		int[][] arr = new int[100][2];
		
		int k=0;
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				if (grid[i][j]=='-') {
					arr[k][0]=i;
					arr[k][1]=j;
					k++;
				}
					
			}
		}
		return arr;
	}

	public ArrayList<Integer> minimax (char ch) {
		int []countx = new int [2];
		int []county = new int [2];
		ArrayList<Integer> cord =new ArrayList<>();
		// TO CHECK WIN
		for (int i=0;i<3;i++) {
			countx[0]=0;
			countx[1]=0;
			county[0]=0;
			county[1]=0;
			for (int j=0;j<3;j++){
				if (grid[i][j]=='X') {
					countx[0]++;
				}
				else if (grid[i][j]=='O') {
					countx[1]++;
				}
				if (grid[j][i]=='X') {
					county[0]++;
				}
				else if (grid[j][i]=='O') {
					county[1]++;
				}
			}
			if (countx[1]==2 && countx[0]==0){
				for (int k=0;k<3;k++) {
					if (grid[i][k]=='-') {
						move(i,k,'O');
						cord.add(i);
						cord.add(k);
						return cord;
					}
				}
				return null;
			}
			else if (county[1]==2 && county[0]==0){
				for (int k=0;k<3;k++){
					if (grid[k][i]=='-') {
						move(k,i,'O');
						cord.add(k);
						cord.add(i);
						return cord;
					}
				}
				return null;
			}
		}

		countx[0]=0;
		countx[1]=0;
		county[0]=0;
		county[1]=0;
		for (int i=0;i<3;i++) {
			if (grid[i][i]=='X') {
				countx[0]++;
			}
			else if (grid[i][i]=='O') {
				countx[1]++;
			}
			if (grid[i][2-i]=='X') {
				county[0]++;
			}
			else if (grid[i][2-i]=='O') {
				county[1]++;
			}
		}

		if ( countx[1]==2 && countx[0]==0) {
			for (int k=0;k<3;k++) {
				if (grid[k][k]=='-') {
					move(k,k,'O');
					cord.add(k);
					cord.add(k);
					return cord;
				}
			}
			return null;
		}
		else if (county[1]==2 && county[0]==0) {
			for (int k=0;k<3;k++) {
				if (grid[k][2-k]=='-') {
					move(k,2-k,'O');
					cord.add(k);
					cord.add(2-k);
					return cord;
				}
			}
			return null;
		}
		
		// TO BLOCK
		for (int i=0;i<3;i++) {
			countx[0]=0;
			countx[1]=0;
			county[0]=0;
			county[1]=0;
			for (int j=0;j<3;j++) {
				if (grid[i][j]=='X'){
					countx[0]++;
				}
				else if (grid[i][j]=='O') {
					countx[1]++;
				}
				if (grid[j][i]=='X') {
					county[0]++;
				}
				else if (grid[j][i]=='O') {
					county[1]++;
				}
			}

			if (countx[0]==2 && countx[1]==0) {
				for (int k=0;k<3;k++) {
					if (grid[i][k]=='-') {
						move(i,k,'O');
						cord.add(i);
						cord.add(k);
						return cord;
					}
				}
				return null;
			}
			else if (county[0]==2 && county[1]==0) {
				for (int k=0;k<3;k++) {
					if (grid[k][i]=='-') {
						move(k,i,'O');
						cord.add(k);
						cord.add(i);
						return cord;
					}
				}
				return null;
			}
		}

		countx[0]=0;
		countx[1]=0;
		county[0]=0;
		county[1]=0;
		for (int i=0;i<3;i++) {
			if (grid[i][i]=='X'){
				countx[0]++;
			}
			else if (grid[i][i]=='O') {
				countx[1]++;
			}
			if (grid[i][2-i]=='X') {
				county[0]++;
			}
			else if (grid[i][2-i]=='O') {
				county[1]++;
			}
		}

		if ( countx[0]==2 && countx[1]==0) {
			for (int k=0;k<3;k++) {
				if (grid[k][k]=='-') {
					move(k,k,'O');
					cord.add(k);
					cord.add(k);
					return cord;
				}
			}
			return null;
		}
		else if (county[0]==2 && county[1]==0) {
			for (int k=0;k<3;k++) {
				if (grid[k][2-k]=='-') {
					move(k,2-k,'O');
					cord.add(k);
					cord.add(2-k);
					return cord;
				}
			}
			return null;
		}
		// CENTER , CORNER , EDGE
		
		if (grid[1][1]=='-') {
			move(1,1,'O');
			cord.add(1);
			cord.add(1);
			return cord;
		}
		else if (grid[0][0]=='-') {
			move (0,0,'O');
			cord.add(0);
			cord.add(0);
			return cord;
		}
		else if (grid[0][2]=='-') {
			move (0,2,'O');
			cord.add(0);
			cord.add(2);
			return cord;
		}
		else if (grid[2][0]=='-') {
			move (2,0,'O');
			cord.add(2);
			cord.add(0);
			return cord;
		}
		else if (grid[2][2]=='-') {
			move (2,2,'O');
			cord.add(2);
			cord.add(2);
			return cord;
		}
		else if (grid[0][1]=='-') {
			move (0,1,'O');
			cord.add(0);
			cord.add(1);
			return cord;
		}
		else if (grid[2][1]=='-') {
			move (2,1,'O');
			cord.add(2);
			cord.add(1);
			return cord;
		}
		else if (grid[1][2]=='-') {
			move (1,2,'O');
			cord.add(1);
			cord.add(2);
			return cord;
		}
		else if (grid[1][0]=='-') {
			move (1,0,'O');
			cord.add(1);
			cord.add(0);
			return cord;
		}
		return null;
	}
}
