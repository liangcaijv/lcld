package org.lanqiao.javafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

@SuppressWarnings("restriction")
public class ClockApp extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		ClockPane root = new ClockPane();
		Scene scene = new Scene(root, 300, 300);

		primaryStage.setScene(scene);
		primaryStage.show();
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.setCurrentTime();
			}
		}));
		timeline.play();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
