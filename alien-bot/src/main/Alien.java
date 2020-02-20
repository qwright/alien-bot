package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

public class Alien {
	
	private String name = "Alien";
	private boolean askedName = false;
	private String humanName = "Human";
	private boolean hasHumName = false;
	
	private Map <String, String[]> phrases = new HashMap<String, String[]>();
	
	public Alien(String name) {
		this.name = name;
		setPhrases();
	}
	
	/*
	 * This is where we can set specific phrases and various options to vary the conversations.
	 * Keys should contain keywords in lowercase
	 */
	public void setPhrases() {
		String[] planets = {"I come from Mars",
				 "I come from what you call E131a-3H",
				 "My home is called Venus"};
		
		phrases.put(".*planet.*", planets);
	}
	/*
	 * Returns random phrase from a Key string
	 */
	public String getPhrases(String in){
		Random rand = new Random();
		//String pattern = ".*planet.*";
		String ans = "What are you talking about human?";
		for(String s : phrases.keySet()) {
			if(Pattern.matches(s, in)) {
				ans = phrases.get(s)[rand.nextInt(3)];
				break;
			}
		}
		return ans;
	}
	
	public String getName() {
		return name;
	}
	
	public String getHumanName() {
		return "humanName";
	}
	
	public void setHumanName(String name) {
		this.humanName = name;
		hasHumName = true;
	}
	
	public void wasAskedName() {
		this.askedName = true;
	}
	
	public boolean getNameState() {
		return askedName;
	}
	
	public String parse(String input) {
		/*
		 * Greeting and Name are explicit
		 * We can keep this as is and use as "limitations" as the project wants or make it more robust - Q
		 */
		String handler = input.toLowerCase();
		if(!hasHumName) {
			if(handler.contentEquals("hello") || handler.contentEquals("hi")) {
				return "Hello Human, what is your name?";
			}
			else {
				//Keep capitalization
				setHumanName(input);
				return "Hi " + humanName;
			}
		}
		if(!askedName && ( handler.contains("name?") || handler.contains("name"))) {
			wasAskedName();
			return "My name is " + name;
		}
		/*
		 * Go to various phrases
		 */
		else {
			return getPhrases(handler);
		}
	}
}
