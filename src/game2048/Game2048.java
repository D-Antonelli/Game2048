package game2048;


import game2048.userinterface.UserInterfaceImpl;
import game2048.computationlogic.Utilities;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class Game2048 {

    private static final int SIDE = 4;
    private static final int[][] gameField = new int[SIDE][SIDE];
    private static UserInterfaceImpl UI;
    private static Stage stage;

    public static void initialize(UserInterfaceImpl newUI) {
        UI = newUI;
        UI.setTileLayout(SIDE);
        stage = UI.getStage();
        createGame();
        drawScene();
        handleKeyEvent(stage);
    }

    public static void handleKeyEvent(Stage stage) {
        stage.addEventHandler(KeyEvent.KEY_PRESSED, Game2048::onKeyPress);
    }

    private static void onKeyPress(KeyEvent e) {
        String key = e.getCode().getName().toUpperCase();

        switch (key) {
            case "LEFT":
                moveLeft();
                break;

            case "RIGHT":
                moveRight();
                break;

            case "UP":
                moveUp();
                break;

            case "DOWN":
                moveDown();
                break;

            default:
                break;
        }

        drawScene();

    }

    private static void createGame() {
        createNewNumber();
        createNewNumber();
    }

    private static void drawScene() {
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                setCellColoredNumber(x, y, gameField[y][x]);
            }
        }
    }

    private static void moveLeft() {
        boolean isNewNumberNeeded = false;
        for (int[] row : gameField) {
            boolean wasCompressed = compressRow(row);
            boolean wasMerged = mergeRow(row);
            if (wasMerged) {
                compressRow(row);
            }
            if (wasCompressed || wasMerged) {
                isNewNumberNeeded = true;
            }
        }

        if (isNewNumberNeeded) {
            createNewNumber();
        }

    }

    private static void moveRight() {

    }

    private static void moveUp() {

    }

    private static void moveDown() {

    }

    private static void createNewNumber() {
        int x = Utilities.getRandomNumber(SIDE);
        int y = Utilities.getRandomNumber(SIDE);
        boolean isCreated = false;
        do {
            if (gameField[y][x] == 0) {
                gameField[y][x] = Utilities.getRandomNumber(10) == 9 ? 4 : 2;
                isCreated = true;
            }
        } while (!isCreated);
    }

    private static void setCellColoredNumber(int x, int y, int value) {
        Color color = Utilities.getColorByValue(value);
        if (value == 0) {
            UI.setTileValueEx(x, y, color, "");
        } else {
            UI.setTileValueEx(x, y, color, Integer.toString(value));
        }
    }

    private static boolean compressRow(int[] row) {
        int insertPosition = 0;
        boolean isCompressed = false;
        for (int x = 0; x < SIDE; x++) {
            if (row[x] > 0) {
                if (x != insertPosition) {
                    row[insertPosition] = row[x];
                    row[x] = 0;
                    isCompressed = true;
                }
                insertPosition++;
            }
        }
        return isCompressed;

    }

    private static boolean mergeRow(int[] row) {
        int pointer = 1;
        boolean result = false;
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] != 0 || row[pointer] != 0) {
                if (row[i] == row[pointer]) {
                    row[i] += row[pointer];
                    row[pointer] = 0;
                    result = true;
                }
            }
            pointer++;
        }
        return result;
    }

    @Test
    void mergeRow() {
        boolean result = mergeRow(new int[]{4, 4, 0, 0});
        Assert.assertTrue(result);

        result = mergeRow(new int[]{2, 2, 2, 2});
        Assert.assertTrue(result);

        result = mergeRow(new int[]{4, 2, 2, 0});
        Assert.assertTrue(result);

        result = mergeRow(new int[]{0, 2, 2, 0});
        Assert.assertTrue(result);

        result = mergeRow(new int[]{0, 2, 2, 2});
        Assert.assertTrue(result);

        result = mergeRow(new int[]{4, 0, 4, 0});
        Assert.assertFalse(result);

        result = mergeRow(new int[]{0, 0, 0, 0});
        Assert.assertFalse(result);
    }
}

