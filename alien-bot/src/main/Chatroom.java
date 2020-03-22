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
import javafx.scene.layout.RowConstraints;
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
	RowConstraints textRow;
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
		grid.setHgap(5);

		/*
		 * Form
		 * 
		 */
		TextField input = new TextField();
		GridPane.setConstraints(input, 20, 55, 90, 1);
		

		// Initialize buttons
		send = new Button();
		send.setText("Send");
		GridPane.setConstraints(send, 110, 55);
		
		clear = new Button();
		clear.setText("Clear");
		GridPane.setConstraints(clear, 111, 55);

		
		// Add the children to the window
		grid.getChildren().addAll(input, send, clear);

		
		// create a scene and set size of window
		Scene scene = new Scene(grid, 800, 600);
		// Set the scene to the primary stage
		primaryStage.setScene(scene);
		// Display stage
		primaryStage.show();
		
		textRow = new RowConstraints();

		//Method called when user hits send button
		send.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setLabels(input.getText());
				updateGrid();
				clicked ++;
				if (clicked > 1) {
					updateGrid();
				}
			}
		});
		
		
		//Method called when user hits clear button
		clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//clear text
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
		for(int n=labelList.size()-1; n>=0; n--) {
			Label l = labelList.get(n);
			//Remove the child
			if(grid.getChildren().contains(l)) {
				grid.getChildren().remove(l);
			} 
			
			GridPane.setConstraints(l, 20, height, 90, 1);
			grid.getChildren().add(l);
			height-=1;
		}
	}

}
