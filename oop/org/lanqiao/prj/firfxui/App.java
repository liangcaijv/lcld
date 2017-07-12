package org.lanqiao.prj.firfxui;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;

@SuppressWarnings("restriction")
public class App extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    HBox parent = new HBox();
    Scene scene = new Scene(parent, 400, 400);
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
  }

}
