package userinterface;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import Game2048.Coordinates;

import java.util.HashMap;
import java.util.Map;

public class UserInterfaceImpl {

    private final Stage stage;
    private final Group group;
    private static Rectangle tile;

    private static final double WINDOW_Y = 700;
    private static final double WINDOW_X = 600;

    private static final Color WINDOW_BACKGROUND_COLOR = Color.rgb(239, 195, 150);
    private static final Color BOARD_BACKGROUND_COLOR = Color.rgb(255, 232, 214);
    private static final Color TILE_COLOR = Color.rgb(171, 180, 164);

    private static final double BOARD_SIDES = 500;
    private static final double BOARD_PADDING_X = 50;
    private static final double BOARD_PADDING_Y = 150;
    private static final double TILE_PADDING = 10;

    private static int TILE;
    private static double TILE_WIDTH_HEIGHT;

    private static final Map<Coordinates, Rectangle> tiles = new HashMap<>();

    public UserInterfaceImpl() {
        this.stage = new Stage();
        group = new Group();
        initializeUserInterface();
    }

    public void setTileLayout(int tiles) {
        UserInterfaceImpl.TILE = tiles;
        UserInterfaceImpl.TILE_WIDTH_HEIGHT = (BOARD_SIDES - (TILE_PADDING * (TILE + 1))) / TILE;
        drawTiles(group);
    }

    public void initializeUserInterface() {
        drawBackground(group);
        drawBoard(group);
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
        for (int row = 0; row < TILE; row++) {
            TILE_X += TILE_PADDING;
            double TILE_Y = BOARD_PADDING_Y + TILE_PADDING;
                for(int col = 0; col < TILE; col++) {
                    tile = getTile();
                    tile.setX(TILE_X);
                    tile.setY(TILE_Y);
                    group.getChildren().add(tile);
                    tiles.put(new Coordinates(row, col), tile);
                    TILE_Y = TILE_WIDTH_HEIGHT + TILE_Y + TILE_PADDING;
                }
                TILE_X += TILE_WIDTH_HEIGHT;
        }
    }


    private Rectangle getTile() {
        tile = new Rectangle();
        tile.setFill(TILE_COLOR);
        tile.setWidth(TILE_WIDTH_HEIGHT);
        tile.setHeight(TILE_WIDTH_HEIGHT);

        return tile;
    }

    public void setTileColor(int x, int y, Color newColor) {
        if(x < TILE -1 && y < TILE -1) {
            Rectangle tile = tiles.get(new Coordinates(x, y));
            tile.setFill(newColor);
        }
    }

}
