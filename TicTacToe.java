import java.util.Random;
import java.util.Scanner;
 

public class TicTacToe {
	public char[][] gameBoard;
	public TicTacToe() {
		gameBoard = genNewBoard();
	}
	public String moveToString(String str) {
		str = str.replaceAll("-"," ");
		str = str.replace("top","0");
		str = str.replace("bottom","2");
		str = str.replace("middle","1");
		str = str.replace("center","1");
		str = str.replace("left","0");
		str = str.replace("right","2");
		return str;
	}
	public char[][] genNewBoard() {
		char[][] board = new char[3][3];
		for(int r=0;r<3;r++) {
			for(int c=0;c<3;c++) {
				board[r][c] = ' ';
			}
		}
		return board;
	}
	public static String getCurrentBoard(char[][] board) {
		String out = "";  
		out += board[0][0] + " | "+board[0][1] + " | "+board[0][2]+"<br>";
		out += "- + - + -<br>";
		out += board[1][0] + " | "+board[1][1] + " | "+board[1][2]+"<br>";
		out += "- + - + -<br>";
		out += board[2][0] + " | "+board[2][1] + " | "+board[2][2]+"<br>";
		return out;
	}
	public int playMove(String input) {
		int endGameStatus=0;
		if(TTTNode.isGameOver(gameBoard)==0&&TTTNode.movesRemaining(gameBoard)>0) {
			input = this.moveToString(input);
			String[] str = input.split(" ");
			int r = Integer.parseInt(str[0]);
			int c = Integer.parseInt(str[1]);
			gameBoard[r][c] = 'o';
			if(TTTNode.isGameOver(gameBoard)==0&&TTTNode.movesRemaining(gameBoard)>0) {
				gameBoard = this.solve();
			}
		}
		endGameStatus = TTTNode.isGameOver(gameBoard);
		if(TTTNode.isGameOver(gameBoard)==0&&TTTNode.movesRemaining(gameBoard)>0)
			endGameStatus=2;
		return endGameStatus;
	}
	public int playGame() {
		int endGameStatus=0;
		Scanner s = new Scanner(System.in);
		if(TTTNode.isGameOver(gameBoard)==0&&TTTNode.movesRemaining(gameBoard)>0) {
			System.out.println(getCurrentBoard(gameBoard));
			String input = s.nextLine();
			input = this.moveToString(input);
			String[] str = input.split(" ");
			int r = Integer.parseInt(str[0]);
			int c = Integer.parseInt(str[1]);
			
			gameBoard[r][c] = 'o';
			if(TTTNode.isGameOver(gameBoard)==0&&TTTNode.movesRemaining(gameBoard)>0) {
				gameBoard = this.solve();
			}
			
		}
		s.close();
		endGameStatus = TTTNode.isGameOver(gameBoard);
		return endGameStatus;
	}
	private char[][] solve() {
		TTTNode currentGame = new TTTNode(gameBoard,'x',1);
		System.out.println(TTTNode.movesRemaining(currentGame.currentGameState));
		Random r = new Random();
		int best = r.nextInt(currentGame.children.size());
		for(int i=0;i<currentGame.children.size();i++) {
			if(currentGame.children.get(best).weight<currentGame.children.get(i).weight||currentGame.children.get(i).weight==-1||currentGame.children.get(i).weight==1) {
				best = i;
			}
		}
		return currentGame.children.get(best).currentGameState;
	}
}
