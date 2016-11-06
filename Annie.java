import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Annie")
public class Annie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String chatLog;
	private boolean first;
	private Speaker s;
	private TicTacToe tttgame;
	private int guessingNum;
	private int inGame=0;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		chatLog="what's your name little one?\n";
		first = true;
	}
	private String buildDoc() {
		String doc =       "<!DOCTYPE html> \n" +
					"  <html>\n"+
					"    <head>\n"+
					"      <title>Annie</title>\n"+
					"    </head>\n"+
					"    <body>\n"+
					"      <form method=\"post\">\n"+
					"        <input type=\"text\" value=\"Matt\" name=\"input\">\n"+
					"        <input type=\"submit\" value=\"Send\">\n"+
					"      </form>\n";
		String[] lines = chatLog.split("\n");
		boolean spokenByUser=false;
		for(int i=lines.length-1;i>=0;i--) {
			if(spokenByUser) {
				doc+= "        <p ";
				doc+=" align=right ";
				doc+= ">"+lines[i]+"</p><br>\n";
			}
			else {
				doc+= "        <pre ";
				doc+= ">"+lines[i]+"</pre><br>\n";
			}
			spokenByUser=!spokenByUser;
		}	
		doc+=		"    </body>\n"+
					"  </html>\n";
		return doc;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=\"UTF-8\"");
		PrintWriter servletOut = response.getWriter();
        servletOut.print(buildDoc());
        servletOut.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("input");
		chatLog+=input+"\n";
		if(first) {
			s = new Speaker(input);
			chatLog+="thank you. my name is miss annie, but you can just call me annie.\n";
			first = false;
		}
		else {
			if(inGame<0) {
				//Asked to play again
				if(input.contains("yes")||input.contains("yeah")||input.contains("sure")) {
					if(inGame==-2) {
						Random temp = new Random();
						guessingNum = 1+temp.nextInt(10);
						chatLog+="okay, i just thought of a number between 1-10. try and guess what it is.\n";
			 			inGame=2;
					}
					else {
						tttgame = new TicTacToe();
						chatLog+=TicTacToe.getCurrentBoard(tttgame.gameBoard);
						chatLog+="okay, you go first again. \n";
						inGame=1;
					}
				}
				else if(input.contains("nah")||input.contains("not")||input.contains("no")) {
					inGame=0;
					chatLog+="back to talking then. unless you want play to a game of number guessing\n";
				}
			}
			else if(inGame==1) {
				//Currently in a game of tic tac toe
				int result = tttgame.playMove(input);
				chatLog+=TicTacToe.getCurrentBoard(tttgame.gameBoard);
				if(result==2) {
					chatLog+="okay, your turn again.\n";
				}
				else if(result==0) {
					chatLog+="its a tie. would you like to play another?\n";
					inGame=-1;
				}
				else if(result==-1) {
					chatLog+="good job. i'm really glad you won. would you like to play another?\n";
					inGame=-1;
				}
				else {
					chatLog+="better luck next time. would you like to play another?\n";
					inGame=-1;
				}
			}
			else if(inGame==2) {
				//Currently in a game of guess the number
				int guessed = Integer.parseInt(input);
				if(guessed==guessingNum) {
					chatLog+="you guessed it. would you like to play again?\n";
					inGame=-2;
				}
				else if(guessed<guessingNum) {
					chatLog+="nope, try guessing a larger number\n";
				}
				else {
					chatLog+="nope, try guessing a smaller number\n";
				}
			}
			else if(s.gameRequested(input)) {
				if(input.contains("tic tac toe")) {
					inGame = 1;
					tttgame = new TicTacToe();
					chatLog+=TicTacToe.getCurrentBoard(tttgame.gameBoard);
					chatLog+="okay, you go first.\n";
				}
				else if(input.contains("number guessing")) {
					inGame = 2;
					Random temp = new Random();
					guessingNum = 1+temp.nextInt(10);
					chatLog+="okay, i just thought of a number between 1-10. try and guess what it is.\n";
				}
				else {
					chatLog+="i'm sorry i don't know that game yet. you'll have to teach me one day. you can pick a game of tic tac toe or a number guessing game with me for the time being.\n";
				}
			}
			else {
				chatLog+=s.speak(input)+"\n";
			}
		}
		response.setContentType("text/html; charset=\"UTF-8\"");
		PrintWriter servletOut = response.getWriter();
		servletOut.print(buildDoc());
        servletOut.close();
	}
}
