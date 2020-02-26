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
	
	Random rand = new Random();
	
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
				 "My home is called Venus"
				 };
		phrases.put("planet", planets);

		String[] testPhrases = {"UFO sounds intensifies",
				"Abducting human",
				"Probing..."
		};
		phrases.put("testphrases", testPhrases);
		
		String[] food = {"I don't require sustinence",
				"Cats, mostly",
				"You look pretty good *licks mouth-appendage*"
				};
		phrases.put("food", food);
		phrases.put("eat?", food);
		
		String[] objectives = {"I want to destroy Earth",
				"Ask the CIA",
				"Just here for a good time, not a long time <3",
				"I wish to speak with the lizard people's leader Mark Zuckerberg"
		};
		phrases.put("here?", objectives);

	}

	/*
	 * Input: User input
	 * Output: Matching alien phrase
	 */
	public String parse(String input) {
		/*
		 * Greeting and Name are explicit
		 * Name is last word of input after greeting
		 */
		String handler = input.toLowerCase();
		if(!hasHumName) {
			if(handler.contentEquals("hello") || handler.contentEquals("hi")) {
				return "Hello Human, what is your name?";
			}
			else {
				//Keep capitalization
				String[] parse = input.split(" ");
				setHumanName(parse[parse.length-1]);
				return "Hi " + humanName;
			}
		}
		if(!askedName && ( handler.contains("name?") || handler.contains("name"))) {
			wasAskedName();
			return "My name is " + name;
		}
		// Go to various phrases
		else
			return getPhrases(handler);
	}

	/*
	 * Input: Lowercase user input
	 * Output: Returns random phrase from a key string, else returns default answer
	 */
	public String getPhrases(String input){
		String ans = "What are you talking about human?";

		for(String s: input.split(" "))
			if(phrases.containsKey(s))
				return phrases.get(s)[rand.nextInt(phrases.get(s).length)];

		return ans;
	}

	// Helper methods
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

}
