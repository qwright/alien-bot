package main;
import main.Alien;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Scanner;

public class Chatroom extends Application{
	Button button;
	Stage window;
	Scene scene;
	TextField input;
	Label label;
	private Alien al;
	private String alias;
	
	public static void main(String[] args) {
		launch(args);
		Scanner userInput = new Scanner(System.in);
		System.out.println("You are walking when suddenly a friendly green alien approaches you. Why don't you say hello?");
		
		/*while(true) {
			// Reading input
			System.out.print("> ");
			String in = userInput.nextLine();

			if(in.equalsIgnoreCase("bye") || in.equalsIgnoreCase("goodbye"))  // 'bye' or 'goodbye' is exit statement
				break;
			else {
				//String alias = al.getNameState() ? al.getName() : "Alien";

				// Small wait to make it seem more realistic
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println(alias + ": " + al.parse(in));
			}
		}
		userInput.close();*/
	}
	@Override
	public void start(Stage primaryStage) throws Exception{
		//init alien 
		al = new Alien("ET");
		alias = al.getNameState() ? al.getName() : "Alien";
		
		window = primaryStage;
		//Set title of window
		primaryStage.setTitle("Alien Bot!");
		
		GridPane grid = new GridPane();
		//padding
		grid.setPadding(new Insets(20, 20, 20, 20));
		//vertical spacing
		grid.setVgap(8);
		//Horizontal spacing
		grid.setHgap(10);
		
		/*Form 
		 * 
		 */
		//Input field
		TextField input = new TextField();
		GridPane.setConstraints(input, 7, 40);
		
		//Set button object and set the text
		button = new Button();
		button.setText("Send");
		
		GridPane.setConstraints(button, 8, 40);
		//Add the children to the window
		grid.getChildren().addAll(input, button);
		
		//Label for message
		label = new Label("A friendly alien says hello!");
		GridPane.setConstraints(label, 7, 35);
		grid.getChildren().add(label);
	
		//create a scene and set size of window
		Scene scene = new Scene(grid, 400, 400);
		//Set the scene to the primary stage
		primaryStage.setScene(scene);
		//Display stage
		primaryStage.show();
		
	
	//method called when user hits button
	button.setOnAction(new EventHandler<ActionEvent>(){
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource()==button) {
			//label.setText(input.getText());
			//String response = al.parse(input.getText());
			label.setText(alias + ": " + al.parse(input.getText()));
		}
	}
	});
	}
	
}
