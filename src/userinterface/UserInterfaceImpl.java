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

    private final double WINDOW_Y = 700;
    private final double WINDOW_X = 600;

    private static final Color WINDOW_BACKGROUND_COLOR = Color.rgb(239, 195, 150);
    private static final Color BOARD_BACKGROUND_COLOR = Color.rgb(255, 232, 214);
    private static final Color TILE_COLOR = Color.rgb(171, 180, 164);

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
        drawBoard(group);
        drawTiles(group);
        stage.show();
    }

    private void drawBackground(Group group) {
        Scene scene = new Scene(group, WINDOW_X, WINDOW_Y);
        scene.setFill(WINDOW_BACKGROUND_COLOR);
        stage.setScene(scene);
    }

    private void drawBoard(Group group) {
        Rectangle board = new Rectangle();
        board.setWidth(BOARD_SIDES);
        board.setHeight(BOARD_SIDES);
        board.setX(BOARD_PADDING_X);
        board.setY(BOARD_PADDING_Y);
        board.setFill(BOARD_BACKGROUND_COLOR);
        group.getChildren().add(board);
    }

    private void drawTiles(Group group) {
        double TILE_X = BOARD_PADDING_X;
        for (int row = 0; row < TILES; row++) {
            TILE_X += TILE_PADDING;
            double TILE_Y = BOARD_PADDING_Y + TILE_PADDING;
                for(int col = 0; col < TILES; col++) {
                    line = getTile(TILE_COLOR, TILE_WIDTH_HEIGHT, TILE_WIDTH_HEIGHT, TILE_X, TILE_Y);
                    group.getChildren().add(line);
                    TILE_Y = TILE_WIDTH_HEIGHT + TILE_Y + TILE_PADDING;
                }
                TILE_X += TILE_WIDTH_HEIGHT;
        }
    }

    private Rectangle getTile(Color color, double width, double height, double x, double y) {
        line = new Rectangle();
        line.setFill(color);
        line.setWidth(width);
        line.setHeight(height);
        line.setX(x);
        line.setY(y);

        return line;
    }
}
