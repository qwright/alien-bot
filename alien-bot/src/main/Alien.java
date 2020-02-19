package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
	 * note: This requires 1-to-1 input (not robust)
	 */
	public void setPhrases() {
		String[] planets = {"I come from Mars",
				 "I come from what you call E131a-3H",
				 "My home is called Venus"};
		
		phrases.put("What planet are you from?", planets);
	}
	/*
	 * Returns random phrase from a Key string
	 */
	public String getPhrases(String in){
		Random rand = new Random();
		return phrases.get(in)[rand.nextInt(3)];
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
		 */
		if(!hasHumName) {
			if(input.toLowerCase().contentEquals("hello") || input.toLowerCase().contentEquals("hi")) {
				return "Hello Human, what is your name?";
			}
			else {
				setHumanName(input);
				return "Hi " + humanName;
			}
		}
		if(!askedName && input.toLowerCase().contains("name?")) {
			wasAskedName();
			return "My name is " + name;
		}
		/*
		 * Go to various phrases
		 */
		else {
			try{
				return getPhrases(input);
				}
			catch(Exception ex) {
				return "I don't understand that language";
			}
		}
	}
}
