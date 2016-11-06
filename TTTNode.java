import java.util.ArrayList;
public class TTTNode {
	public double weight;
	public ArrayList<TTTNode> children;
	public char[][] currentGameState;
	public TTTNode(char[][] gameState,char turn,double depth) {
		currentGameState = gameState;
		children = new ArrayList<TTTNode>();
		if(isGameOver(currentGameState)!=0||movesRemaining(currentGameState)==0) {
			weight = isGameOver(currentGameState)/depth;
		}
		else {
			for(int r=0;r<3;r++) {
				for(int c=0;c<3;c++) {
					if(currentGameState[r][c]==' ') {
						
						char[][] childState = new char[3][3];
						for(int y=0;y<3;y++) {
							for(int x=0;x<3;x++) {
								childState[x][y] = currentGameState[x][y];
							} 
						}
						childState[r][c] = turn;
						char childTurn;
						if(turn=='x')
							childTurn='o';
						else
							childTurn='x';
						TTTNode child = new TTTNode(childState,childTurn,depth+1);
						
						if(child.weight!=-1)
							children.add(child);
					}
				}
			}
			weight = 0;
			for(TTTNode n:children) {
				weight+=n.children.size(); 
			}
		}
	}
	public static int movesRemaining(char[][] gameState) {
		int remaining=0;
		for(int r1=0;r1<3;r1++) {
			for(int c1=0;c1<3;c1++) {
				if(gameState[r1][c1]==' ') {
					remaining++;
				}
			}
		}
		return remaining;
	}
	public static int isGameOver(char[][] gameState) {
		int winner = 0;
		if((gameState[0][0]=='x'&&gameState[0][1]=='x'&&gameState[0][2]=='x')||(gameState[0][0]=='o'&&gameState[0][1]=='o'&&gameState[0][2]=='o')) {
			//Win by top row
			if(gameState[0][0]=='x') {
				//Player Victory
				winner=1;
			}
			else if(gameState[0][0]=='o') {
				//CPU Victory
				winner=-1;
			}
		}
		if((gameState[1][0]=='x'&&gameState[1][1]=='x'&&gameState[1][2]=='x')||(gameState[1][0]=='o'&&gameState[1][1]=='o'&&gameState[1][2]=='o')) {
			//Win by middle row
			if(gameState[1][0]=='x') {
				//Player Victory
				winner=1;
			}
			else if(gameState[1][0]=='o') {
				//CPU Victory
				winner=-1;
			}
		}
		if((gameState[2][0]=='x'&&gameState[2][1]=='x'&&gameState[2][2]=='x')||(gameState[2][0]=='o'&&gameState[2][1]=='o'&&gameState[2][2]=='o')) {
			//Win by low row
			if(gameState[2][0]=='x') {
				//Player Victory
				winner=1;
			}
			else if(gameState[2][0]=='o') {
				//CPU Victory
				winner=-1;
			}
		}
		if((gameState[0][0]=='x'&&gameState[1][0]=='x'&&gameState[2][0]=='x')||(gameState[0][0]=='o'&&gameState[1][0]=='o'&&gameState[2][0]=='o')) {
			//Win by left column
			if(gameState[0][0]=='x') {
				//Player Victory
				winner=1;
			}
			else if(gameState[0][0]=='o') {
				//CPU Victory
				winner=-1;
			}
		}
		if((gameState[0][1]=='x'&&gameState[1][1]=='x'&&gameState[2][1]=='x')||(gameState[0][1]=='o'&&gameState[1][1]=='o'&&gameState[2][1]=='o')) {
			//Win by middle column
			if(gameState[0][1]=='x') {
				//Player Victory
				winner=1;
			}
			else if(gameState[0][1]=='o') {
				//CPU Victory
				winner=-1;
			}
		}
		if((gameState[0][2]=='x'&&gameState[1][2]=='x'&&gameState[2][2]=='x')||(gameState[0][2]=='o'&&gameState[1][2]=='o'&&gameState[2][2]=='o')) {
			//Win by right column
			if(gameState[0][1]=='x') {
				//Player Victory
				winner=1;
			}
			else if(gameState[0][1]=='o') {
				//CPU Victory
				winner=-1;
			}
		}
		if((gameState[0][0]=='x'&&gameState[1][1]=='x'&&gameState[2][2]=='x')||(gameState[0][0]=='o'&&gameState[1][1]=='o'&&gameState[2][2]=='o')) {
			//Win by forward slash
			if(gameState[0][0]=='x') {
				//Player Victory
				winner=1;
			}
			else if(gameState[0][0]=='o') {
				//CPU Victory
				winner=-1;
			}
		}
		if((gameState[0][2]=='x'&&gameState[1][1]=='x'&&gameState[2][0]=='x')||(gameState[0][2]=='o'&&gameState[1][1]=='o'&&gameState[2][0]=='o')) {
			//Win by back slash
			if(gameState[0][2]=='x') {
				//Player Victory
				winner=1;
			}
			else if(gameState[0][2]=='o') {
				//CPU Victory
				winner=-1;
			}
		}
		return winner;
	}
}