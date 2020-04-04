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
	private String response = "";
	private int badResponse = 0;
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
				"I come from the best Planet E1K-LL9Z.",
				"My horrible home is called Venus."
		};
		phrases.put("planet", planets);
		phrases.put("planet?", planets);
		phrases.put("from?", planets);

		String[] testPhrases = {"UFO sounds intensifies.",
				"Abducting human.",
				"Probing...",
				"I think you are cute"
		};
		phrases.put("testphrases", testPhrases);

		String[] food = {"don't require sustinence.",
				"eat cats, mostly. Irritating nasty creatures.",
				"think you look pretty good. *licks mouth-appendage*"
		};
		phrases.put("food", food);
		phrases.put("eat", food);
		phrases.put("eaten", food);

		String[] drink = {"drink only water - 8 glasses a day!"};
		phrases.put("drink", drink);


		String[] politics = {"We are a serfdom"};
		phrases.put("polit", politics);
		phrases.put("societi", politics);

		String[] objectives = {"I want to destroy Earth.",
				"Ask the CIA.",
				"Just here for a pretty good time, not a long time.",
				"I wish to speak with the lizard people's leader Mark Zuckerberg."
		};
		phrases.put("here", objectives);

		String[] well = {"tired. These days are like... much longer on Earth.",
				"starved! I could use some of what you humans call... food.",
				"doing really well thank you!"
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
				"I have 8 strong, muscular arms.",
				"Well human, I have disgusting and unbearable rows of sharp teeth that grow in wrong!"
		};
		phrases.put("appear", appearance);
		phrases.put("look", appearance);

		String[] exit = {"Bye dull earth dweller.",
				"Goodbye earthling.",
				"Goodbye, all information from this conversation will be erased from your memory."
		};
		phrases.put("goodbye", exit);
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

		//New topic 
		String[] animals = {"We do not have animals on my home planet. They were a bother so we exterminated them.",
				"I fear animals far more than I fear humans. I think they are all interesting.",
				"Animals are so cute !!!! and delish"
		};
		
		phrases.put("anim", animals);

		//New topic #2
		String[] home = {"I still have unfinished buisness. I cannot leave unless I take Trump with me",
				"I will not leave until I try PIZZA. You humans never stop talking about it. I must know."
		};
		
		phrases.put("home", home);
		phrases.put("back home", home);
		
		//New topic #3
		String[] trump = {"*Alien Noises* Do you know of my plan? Where might I find him?"
				
		};
		
		phrases.put("trump", trump);
		phrases.put("donald", trump);
		//New topic #4
		String[] job = {"go to work at Mcdonalds, ba da ba ba ba 'I'm Lovin'It!'",
			"act on the hit tv show 'Westworld'",
			"report to the Apple store, human."
		};
		phrases.put("work",job);
		
		//New topic #5
		String[] tv = {"watch Game of Thrones, the last season sucks",
				"watch Parks and Recreation. #Treatyoself"
		};
		phrases.put("watch",tv);
		
		//New topic #6
		String[] books = {"read Margaret Atwoods' 'The Handmaid's Tale'",
				"read the Game of Thrones series, WINTER IS COMING *Alien noises*"	
		};
		phrases.put("read",books);
		
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
				"animals",
				"read",
				"watch",
				"work"
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
		String answer = "";
		if(!hasHumName) {
			if(handler.contentEquals("hello") || handler.contentEquals("hi")) {
				answer = "Hello cute human flesh, what is your name?";
				this.response = answer;
				return answer;
			}
			else {
				//Keep capitalization
				String[] parse = input.split(" ");
				setHumanName(parse[parse.length-1]);
				answer ="It is lovely to meet you, " + humanName;
				this.response = answer;
				return answer;
			}
		}
		if(!askedName && ( handler.contains("name?") || handler.contains("name"))) {
			wasAskedName();
			answer = "My name is " + name;
			this.response = answer;
			return answer;
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
					String ans = phrases.get(s)[rand.nextInt(phrases.get(s).length)];
					this.response = ans;
					return ans;
				}
			}

		}
		return badInput();
	}
	
	public String badInput()
	{
		String ans = "";
		switch(badResponse) {
		case 0:
			badResponse++;
			ans = "You're funny!";
			break;
		case 1:
			ans = "I dont think understand you! Ask for help?";
			badResponse++;
			break;
		case 2:
			ans = "Try to speak my language";
			badResponse++;
			break;
		case 3:
			ans = "You're boring.";
			badResponse++;
			break;
		case 4:
			ans = "I WILL ANNIHILATE YOUR ABOMINATION OF A PLANET!";
			badResponse++;
			break;
		default:
			ans = "I WILL DEVOUR YOU. YOU ARE A WORM UPON THIS UNIVERSE. FACE MY WRATH";
			break;
		}
		this.response = ans;
		return ans;
	}

	// Helper methods
	public String getName() {
		if(askedName) {
			return name;
		}
		else {
			return "Alien";
		}
		
	}

	public String getHumanName() {
		return humanName;
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
		System.out.println(response);
		sentiment = SentimentAnalyzer.getSentiment(response);
		return sentiment;
	}

}
