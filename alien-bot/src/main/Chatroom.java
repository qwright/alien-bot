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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chatroom extends Application {
	Button send, clear;
	Stage window;
	Scene scene;
	TextField input;
	//Label alienMsg, humanMsg, alienMsg1, humanMsg1;
	private Alien al;
	private String alias;
	private String humanName;
	static int clicked;
	final private int LOWEST_LABEL_HEIGHT=50;
	private GridPane grid;
	List<Label> labelList;

	public static void main(String[] args) {
		launch(args);
		//Scanner userInput = new Scanner(System.in);
		clicked = 0;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Initialize alien
		al = new Alien("ET");
		alias = al.getNameState() ? al.getName() : "Alien";
		humanName = al.getNameState() ? al.getHumanName() : "Human";
		
		labelList = new ArrayList();

		window = primaryStage;
		// Set title of window
		primaryStage.setTitle("Alien Bot!");

		// Set up grid
		grid = new GridPane();
		// padding
		grid.setPadding(new Insets(15, 15, 15, 15));
		// vertical spacing
		grid.setVgap(8);
		// Horizontal spacing
		grid.setHgap(10);

		/*
		 * Form
		 * 
		 */
		// Input field
		TextField input = new TextField();
		GridPane.setConstraints(input, 20, 55);

		// Initialize buttons
		send = new Button();
		send.setText("Send");
		GridPane.setConstraints(send, 21, 55);
		
		clear = new Button();
		clear.setText("Clear");
		GridPane.setConstraints(clear, 22, 55);

		
		// Add the children to the window
		grid.getChildren().addAll(input, send, clear);

		// Initialize Labels
		//Label alienMsg = new Label("A friendly alien says hello!");
		//GridPane.setConstraints(alienMsg, 20, 50);
		//grid.getChildren().add(alienMsg);

		//Label humanMsg = new Label();
		//GridPane.setConstraints(humanMsg, 20, 45);
		//grid.getChildren().add(humanMsg);
		
		// create a scene and set size of window
		Scene scene = new Scene(grid, 800, 600);
		// Set the scene to the primary stage
		primaryStage.setScene(scene);
		// Display stage
		primaryStage.show();

		//Method called when user hits send button
		send.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//humanMsg.setText(humanName + ": " + input.getText());
				//alienMsg.setText(alias + ": " + al.parse(input.getText()));
				setLabels(input.getText());
				updateGrid();
				clicked ++;
				if (clicked > 1) {
					updateGrid();
					//GridPane.setConstraints(alienMsg, 20, 35);
					//GridPane.setConstraints(humanMsg, 20, 40);
				}
			}
		});
		
		
		//Method called when user hits clear button
		clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//humanMsg.setText("");
				//alienMsg.setText("");
			}
		});
	}
	
	public void setLabels(String in)
	{
		labelList.add(new Label(humanName + ": " + in));
		labelList.add(new Label(alias + ": " + al.parse(in)));
	}
	
	public void updateGrid()
	{
		int height = LOWEST_LABEL_HEIGHT;
		for(int n=labelList.size()-1; n>=0; n-- ) {
			Label l = labelList.get(n);
			if(grid.getChildren().contains(l)) {
				grid.getChildren().remove(l);
			}
			GridPane.setConstraints(l, 20, height);
			grid.getChildren().add(l);
			height-=5;
		}
	}

}
