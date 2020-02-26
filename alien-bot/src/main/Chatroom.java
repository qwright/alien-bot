package main;
import java.util.Scanner;

public class Chatroom {
	
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		Alien al = new Alien("ET");
		
		while(true) {
			// Reading input
			System.out.print("> ");
			String in = userInput.nextLine();

			if(in.equalsIgnoreCase("bye") || in.equalsIgnoreCase("goodbye"))  // 'bye' or 'goodbye' is exit statement
				break;
			else {
				String alias = al.getNameState() ? al.getName() : "Alien";

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
