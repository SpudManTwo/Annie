import java.util.ArrayList;
import java.util.Random;

public class Speaker {
	private String childName;
	private ArrayList<String> previousTalks;
	public Speaker(String child) {
		previousTalks = new ArrayList<String>();
		childName = child;
	} 
	public boolean gameRequested(String input) {
		return input.contains("game")||input.contains("tic tac toe")||input.contains("number guessing");
	}
	public String speak(String input) {
		input = input.toLowerCase();
		previousTalks.add(previousTalks.size(),input);
		String response="i didn't understand that my child. how about you tell me about your day?";
		if(input.contains("hi ")||input.contains("hey ")||input.contains("hello ")||input.contains("good morning")||input.contains("good afternoon")||input.contains("good evening")) {
			response = input.split(" ")[0];
			if(previousTalks.size()==1)
				response+=" "+childName+". how are you?";
			response = response.replaceAll("  "," ");
			previousTalks.add(previousTalks.size(),response);
			return response;
		}
		if(input.contains("how ")&&(input.contains("are")||input.contains("is"))) {
			response = "wonderful, because i got to see your bright sunshining face. how are you?";
			previousTalks.add(previousTalks.size(),response);
			return response;
		}
		if(previousTalks.size()==1) {
			response = "i don't know. what is new with you? how are you?";
			previousTalks.add(previousTalks.size(),response);
			return response;
		}
		if(input.equals("nothing")) {
			previousTalks.remove(previousTalks.size()-1);
			response = "are you sure?";
			previousTalks.add(previousTalks.size(),response);
			return response;
		}
		if(previousTalks.get(previousTalks.size()-2).contains("how are you?")||previousTalks.get(previousTalks.size()-2).contains("how about you tell me about your day?")) {
			if(input.contains("good")||(input.contains("not")&&input.contains("bad"))) {
				response = "thats good to hear. is there anything you want to talk about?";
				previousTalks.add(previousTalks.size(),response);
				return response;
			}
			if(input.contains("bad ")||(input.contains("not")&&input.contains("good"))||input.contains("bored")||input.contains("boring")) {
				response = "i'm sorry to hear that. is there anything you want to talk about?";
				previousTalks.add(previousTalks.size(),response);
				return response;
			}
			else {
				response = "why's that? is there anything you want to talk about?";
				previousTalks.add(previousTalks.size(),response);
				return response;
			}
		}
		if(previousTalks.get(previousTalks.size()-2).contains("is there anything you want to talk about?")) {
			if(input.contains("yes")||input.contains("yeah")||input.contains("sure")) {
				response = "go ahead and tell me then";
				previousTalks.add(previousTalks.size(),response);
				return response;
			}
			else if(input.contains("nah")||input.contains("not")||input.contains("no")) {
				response = "are you sure? i'm always here if you want to talk.";
				previousTalks.add(previousTalks.size(),response);
				return response;
			}
			else {
				response = "tell me more";
				previousTalks.add(previousTalks.size(),response);
				return response;
			}
		}
		if(previousTalks.get(previousTalks.size()-2).contains("are you sure?")) {
			if(input.contains("yes")||input.contains("yeah")||input.contains("sure")) {
				response = "alright then. what do you want to talk about?";
				previousTalks.add(previousTalks.size(),response);
				return response;
			}
			else if(input.contains("nah")||input.contains("not")||input.contains("no")) {
				response = "go ahead and tell me then";
				previousTalks.add(previousTalks.size(),response);
				return response;
			}
			else {
				response = "tell me more";
				previousTalks.add(previousTalks.size(),response);
				return response;
			} 
		}
		if(previousTalks.get(previousTalks.size()-2).contains("go ahead and tell me then")||previousTalks.get(previousTalks.size()-2).contains("tell me more")||previousTalks.get(previousTalks.size()-2).contains("go on")) {
			if(!(input.contains("that's")&&input.contains("it")||(input.contains("nah")||input.contains("not")||input.contains("no")))) {
				if(previousTalks.get(previousTalks.size()-2).contains("tell me more"))
					response = "go on";
				else
					response = "tell me more";
				previousTalks.add(previousTalks.size(),response);
				return response;
			}
			else {
				response="i'm glad you told me. remember that you can always tell me things. is there anything you want to talk about?";
				previousTalks.add(previousTalks.size(),response);
				return response;
			}
		}
		
		String[] pumpingList = response.split(" ");
		ArrayList<String> toBePumped = new ArrayList<String>();
		for(String s:pumpingList)
			toBePumped.add(s);
		while(!toBePumped.isEmpty()) {
			Random r = new Random();
			int rand = r.nextInt(toBePumped.size());
			String s = toBePumped.remove(rand);
			if(s.equals("be")||s.equals("am")||s.equals("is")||s.equals("are")||s.equals(" was ")||s.equals(" were ")||s.equals(" being ")) {
				//be or conjugation of be
				response="and how does that make you feel? ";
				previousTalks.add(previousTalks.size(),response);
				return response;
			}
			if(s.equals("have")||s.equals("had")||s.equals("having")) {
				response="tell me some more about your "+toBePumped.remove(rand-1);
				previousTalks.add(previousTalks.size(),response);
				return response;
			}
		}
		
		previousTalks.add(previousTalks.size(),response);
		return response;
	}
}
