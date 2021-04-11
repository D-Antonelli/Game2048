package game2048;

import javafx.application.Application;
import javafx.stage.Stage;
import game2048.userinterface.UserInterfaceImpl;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        UserInterfaceImpl userInterface = new UserInterfaceImpl();

        Game2048.initialize(userInterface);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
