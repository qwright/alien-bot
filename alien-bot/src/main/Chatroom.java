package main;

import main.Alien;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

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
	ScrollPane container;
	VBox root, chat;
	HBox inputArea;
	int index;

	public static void main(String[] args) {
		launch(args);
		//Scanner userInput = new Scanner(System.in);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Initialize alien
		al = new Alien("ET");
		alias = al.getNameState() ? al.getName() : "Alien";
		humanName = al.getNameState() ? al.getHumanName() : "Human";
		//root holds scrollpane and hbox textarea
		root = new VBox();
		window = primaryStage;
		primaryStage.setTitle("Alien Bot");
		chat = new VBox();
		chat.setPrefSize(500, 600);
		
		inputArea = new HBox();
		container = new ScrollPane();
		//labels are used to hold messages
		labelList = new ArrayList<Label>();
		//init label index
		index = 0;
		
		TextField input = new TextField();
		send = new Button();
		send.setText("Send");
		//hbox for input
		inputArea.getChildren().addAll(input, send);
		//set vbox as scrollbox content
		container.setContent(chat);
		container.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		container.setHbarPolicy(ScrollBarPolicy.NEVER);
		//
		root.getChildren().addAll(container, inputArea);
		Scene scene = new Scene(root, 500, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		send.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				setLabels(input.getText());
				updateChat();
				input.clear();
			}
		});
	}
	
	public void setLabels(String in)
	{
		labelList.add(new Label(humanName + ": " + in));
		labelList.add(new Label(alias + ": " + al.parse(in)));
	}
	
	public void updateChat()
	{

			//human msg
			labelList.get(index).setAlignment(Pos.CENTER_LEFT);
			chat.getChildren().add(labelList.get(index));
			index++;

			//alien msg
			labelList.get(index).setAlignment(Pos.CENTER_RIGHT);
			chat.getChildren().add(labelList.get(index));
			index++;
			container.setVvalue(Double.MAX_VALUE);
	}
	
}