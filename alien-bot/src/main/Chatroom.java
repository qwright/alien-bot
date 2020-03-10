package main;
import java.util.Scanner;

/*
 * Main entry point for the user. Main loop and parsing occurs here
 */

public class Chatroom {
	
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		Alien al = new Alien("ET");
		System.out.println("You are walking when suddenly a friendly green alien approaches you. Why don't you say hello?");
		
		while(true) {
			// Reading input
			System.out.print("> ");
			String in = userInput.nextLine();
			String alias = al.getNameState() ? al.getName() : "Alien";
			
			if(in.toLowerCase().contains("bye") || in.toLowerCase().contains("goodbye"))  // 'bye' or 'goodbye' is exit statement
			{
				System.out.println(alias + ": " + al.parse(in));
				break;
			}
			else {
				try {
					Thread.sleep(500); 				// Small wait to make it seem more realistic
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// Send user input to string parser
				System.out.println(alias + ": " + al.parse(in));
			}
		}
		userInput.close();
	}	
}
