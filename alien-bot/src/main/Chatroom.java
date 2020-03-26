package main;

import main.Alien;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Chatroom extends Application {
	Button send, clear;
	Stage window;
	Scene scene;
	TextField input;
	private Alien al;
	private String alias;
	private String humanName;
	static int clicked;
	RowConstraints textRow;
	Label current;
	List<Label> labelList;
	ScrollPane container;
	VBox root, chat;
	HBox inputArea;
	Image alienHappy, alienDis, alienAngry, alienSmile, alienNeutral;
	ImageView imageView;

	int index;



	public static void main(String[] args) {
		launch(args);
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
		//chat.setAlignment(BOTTOM);

		loadImages();
		imageView = new ImageView(alienNeutral);
		imageView.setFitHeight(100); 
		imageView.setFitWidth(100); 


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
		inputArea.getChildren().addAll(imageView, input, send);
		//set vbox as scrollbox content
		container.setContent(chat);
		container.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		container.setHbarPolicy(ScrollBarPolicy.NEVER);
		//
		root.getChildren().addAll(container, inputArea);
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();

		send.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {	
				setLabels(input.getText());
				//set human immediately
				updateChat();
				//wait for alien for more realistic response
				input.clear();
				input.requestFocus();
			}
		});
		input.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(oldValue.contains("") && index%2!=0) {
					Task<Void> sleeper = new Task<Void>() {
						@Override
						protected Void call() throws Exception{
							try {
								Thread.sleep(1000);
							}catch(InterruptedException e) {
							}
							return null;
						}

					};
					sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
						@Override
						public void handle(WorkerStateEvent e) {
							updateChat();
						}
					});
					new Thread(sleeper).start();
				}


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
		if(index%2==0) {
			//human msg
			current = labelList.get(index);
			chat.getChildren().add(current);
			index++;
		}else {
			//alien msg
			current = labelList.get(index);
			getAlienSentiment(current.getText());
			current.getText();
			labelList.get(index).setAlignment(Pos.CENTER_RIGHT);
			chat.getChildren().add(labelList.get(index));
			index++;
			//This drops scrollbar
			//container.setVvalue(Double.MAX_VALUE);
		}

	}

	private void getAlienSentiment(String text) {
		//Sentiment analysis here, updates image
	}

	public void loadImages()
	{
		try {
			alienNeutral = new Image(new FileInputStream("res/alien_neutral.png"));
			alienAngry = new Image(new FileInputStream("res/alien_angry.png"));
			alienHappy = new Image(new FileInputStream("res/alien_veryhappy.png"));
			alienDis = new Image(new FileInputStream("res/alien_annoyed.png"));
			alienSmile = new Image(new FileInputStream("res/alien_happy.png"));

		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

}