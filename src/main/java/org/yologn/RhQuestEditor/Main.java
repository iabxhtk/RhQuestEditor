package org.yologn.RhQuestEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Parent root = FXMLLoader.load(Main.class.getResource("views/quest.fxml"));
        Scene scene = new Scene(root, 920, 600);
        //scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        primaryStage.setTitle("kthxbai2 RH quest/title editor v.2.1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
