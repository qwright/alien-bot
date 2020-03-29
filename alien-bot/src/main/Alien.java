package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * Alien Class takes user inputs and gives outputs to the chatroom using very basic pattern matching.
 */

public class Alien {
	
	private String name = "Alien";
	private boolean askedName = false;
	private String humanName = "Human";
	private boolean hasHumName = false;
	private int sentiment = -1;

	Random rand = new Random();

	// A hashmap is used for quick lookup of phrases and keywords
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
		String[] planets = {"I come from Mars.",
							"I come from what you call E131a-3H.",
				 			"My home is called Venus."
				 };
		phrases.put("planet", planets);
		phrases.put("planet?", planets);
		phrases.put("from?", planets);

		String[] testPhrases = {"UFO sounds intensifies.",
								"Abducting human.",
								"Probing..."
		};
		phrases.put("testphrases", testPhrases);

		String[] food = {"don't require sustinence.",
				"eat cats, mostly.",
				"think you look pretty good. *licks mouth-appendage*"
				};
		phrases.put("food", food);
		phrases.put("eat", food);
		
		String[] drink = {"drink only water - 8 glasses a day!"};
		phrases.put("drink", drink);

		
		String[] politics = {"We are a serfdom"};
		phrases.put("polit", politics);
		phrases.put("societi", politics);

		String[] objectives = {"I want to destroy Earth.",
				"Ask the CIA.",
				"Just here for a good time, not a long time <3.",
				"I wish to speak with the lizard people's leader Mark Zuckerberg."
		};
		phrases.put("here", objectives);
		
		String[] well = {"tired. The days are much longer on earth.",
				"starved. I could use some of what you humans call... food.",
				"missing my home planet."
		};
		phrases.put("feel", well);
		
		String[] spaceship = {"My spaceship is the fastest ship in my home planet. And it has heated seats!",
				"This ship has done the Kessel Run in less than 10 parsecs!"
		};
		phrases.put("ship", spaceship);
		phrases.put("spaceship", spaceship);
		phrases.put("ufo", spaceship);
		
		String[] sports = {"I am not familiar with earthling sports. In my home planet we lift asteroids with our mouth appendages.",
				"I am interested in the one you call 'croquet'."	
		};
		phrases.put("sport", sports);

		String[] appearance = {"I have 6 eyes.",
				"I have 8 arms.",
				"I don't have any teeth!"
		};
		phrases.put("appear", appearance);
		phrases.put("look", appearance);
		
		String[] exit = {"Bye dull earth dweller.",
				"Goodbye earthling.",
				"Goodbye, all information from this conversation will be erased from your memory."
		};
		phrases.put("goodby", exit);
		phrases.put("bye", exit);

		String[] earth = { "It is dull and everything is bland, I hate it here",
				"It is 'cute', far less advanced than my home planet though", 
				"The country Italy is nice"
		};
		phrases.put("earth", earth);
		
		String[] language = { "I don't, I'm speaking my native tongue E*lW1*, it's just very similar", 
				"We have to learn 250 languages in my home planet",
				"I've adjusted your brain to translate my native tongue to your language"
		};
		phrases.put("understand", language);
		phrases.put("english", language);
		
		
		//This returns a set of keywords to prompt the user on what words to ask the alien
		String[] keyWords = { "planet", 
				"food",
				"eat",
				"drink",
				"here",
				"feel",
				"UFO",
				"sports",
				"appearance",
				"earth",
				"English",
				"politics",
				"society",
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
		sentiment = SentimentAnalyzer.getSentiment(input);
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
		Stemmer stemmer = new Stemmer();

		String ans = "What are you talking about earthling? Maybe you should ask for help.";

		// Search for phrases in the user input
		for(String s: input.split(" ")) {
			s = stemmer.run(s);  // stem the word

			if (phrases.containsKey(s)) {
				//This is a mechanism that returns key words to try when the user asks for help
				if (s.equals("help")) {

					// Create a string of all 'help' phrases for the user
					StringBuilder sbHelp = new StringBuilder();
					sbHelp.append("*Alien noises* Try saying:");

					// Add all possible phrases
					for (String i : phrases.get(s))
						sbHelp.append("\n" + i);
					return sbHelp.toString();
				}

				// If not help and a matching key phrase, return first possible response
				else {
					return phrases.get(s)[rand.nextInt(phrases.get(s).length)];
				}
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
	
	public int getSentiment()
	{
		return sentiment;
	}

}
