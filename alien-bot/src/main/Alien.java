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
		phrases.put("planet?", planets);
		phrases.put("from?", planets);

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
		
		String[] well = {"I am tired. The days are much longer on earth.",
				"I could use some of what you humans call... food.",
				"I am missing my home planet."
		};
		phrases.put("doing?", well);
		phrases.put("feeling?", well);
		
		String[] spaceship = {"My spaceship is the fastest ship in my home planet. And it has heated seats!",
		};
		phrases.put("ship?", spaceship);
		phrases.put("spaceship?", spaceship);
		phrases.put("UFO", spaceship);
		//This returns a set of keywords to prompt the user on what words to ask the alien
		String[] keyWords = { "planet", 
				"food",
				"eat?",
				"here?",
				"doing?",
				"UFO",
				"testPhrases"
		};
		phrases.put("help", keyWords);	
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
		String ans = "What are you talking about earthling? Maybe you should ask for help.";
		//This is a mechanism that returns key words to try when the user asks for help
		for(String s: input.split(" "))
			 if(phrases.containsKey(s)) {
				 if(s.equals("help")) {
					StringBuilder sbHelp = new StringBuilder();
					sbHelp.append("*Alien noises* Try saying:");
					for (String i:phrases.get(s)) { 
						sbHelp.append("\n" + i);
					}
					return sbHelp.toString();
				 }
				 else {
				return phrases.get(s)[rand.nextInt(phrases.get(s).length)];
				 }
			}

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
