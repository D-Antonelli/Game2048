package userinterface;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class UserInterfaceImpl {

    private final Stage stage;
    private final Group group;
    private Rectangle line;

    private static final double BOARD_SIDES = 500;
    private static double BOARD_PADDING_X = 50;
    private static double BOARD_PADDING_Y = 150;


    private static final double TILE_PADDING = 10;
    private static final double TILES = 4;
    private static final double TILE_WIDTH_HEIGHT = (BOARD_SIDES - (TILE_PADDING * (TILES + 1))) / TILES;


    public UserInterfaceImpl() {
        this.stage = new Stage();
        group = new Group();
        initializeUserInterface();
    }

    public void initializeUserInterface() {
        drawBackground(group);
        Rectangle board = new Rectangle(BOARD_PADDING_X, BOARD_PADDING_Y, BOARD_SIDES, BOARD_SIDES);
        board.setFill(Color.BLACK);
        group.getChildren().add(board);

        for (int row = 0; row < TILES; row++) {
            double TILE_Y = BOARD_PADDING_Y;
            line = new Rectangle();
            line.setFill(Color.RED);
            line.setWidth(TILE_WIDTH_HEIGHT);
            line.setHeight(TILE_WIDTH_HEIGHT);
            line.setX(BOARD_PADDING_X + TILE_PADDING);
            line.setY(TILE_Y + TILE_PADDING);
            group.getChildren().add(line);

            for(int col = 0; col < TILES; col++) {
                line = new Rectangle();
                line.setFill(Color.RED);
                line.setWidth(TILE_WIDTH_HEIGHT);
                line.setHeight(TILE_WIDTH_HEIGHT);
                line.setX(BOARD_PADDING_X + TILE_PADDING);
                line.setY(TILE_Y + TILE_PADDING);
                group.getChildren().add(line);
                TILE_Y = TILE_WIDTH_HEIGHT + TILE_Y + TILE_PADDING;
            }
            BOARD_PADDING_X = TILE_WIDTH_HEIGHT + BOARD_PADDING_X + TILE_PADDING;
        }

        stage.show();
    }

    public void drawBackground(Group group) {
        Scene scene = new Scene(group, 600, 700);
        scene.setFill(Color.ALICEBLUE);
        stage.setTitle("2048");
        stage.setScene(scene);
    }
}
