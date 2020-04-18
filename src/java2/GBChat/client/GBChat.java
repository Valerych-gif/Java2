package java2.GBChat.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GBChat extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gbchat.fxml"));
        primaryStage.setTitle("GBChat");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
