package main;
import java.util.Scanner;

public class Chatroom {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Alien al = new Alien("ET");
		
		while(true) {
			System.out.print("> ");
			String in = scan.nextLine();
			if(in.equalsIgnoreCase("bye")) {
				break;
			}
			else {
				String alias = al.getNameState() ? al.getName() : "Alien";
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(alias + ": " + al.parse(in));
			}
		}
		scan.close();
	}	
}
