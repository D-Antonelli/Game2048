package Game2048;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import userinterface.UserInterfaceImpl;


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
