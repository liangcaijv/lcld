package org.lanqiao.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ButtonlnPane extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(new Button("ok"));
		
		Scene scene = new Scene(stackPane,200,200);
		primaryStage.setTitle("面板示例");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
