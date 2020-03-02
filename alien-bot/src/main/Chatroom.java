package main;
import main.Alien;
import java.util.Scanner;

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
				// Small wait to make it seem more realistic
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println(alias + ": " + al.parse(in));
			}
		}
		userInput.close();
	}	
}
