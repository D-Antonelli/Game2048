package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import userinterface.UserInterfaceImpl;


public class Main extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception{
        UserInterfaceImpl userInterface = new UserInterfaceImpl();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
