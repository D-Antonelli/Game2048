package game2048;


import game2048.userinterface.UserInterfaceImpl;
import game2048.logic.Utilities;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Game2048 {
    private static final int SIDE = 4;
    private static boolean isGameStopped = false;
    private static int[][] gameField = new int[SIDE][SIDE];
    private static UserInterfaceImpl UI;
    private static int score = 0;

    public static void initialize(UserInterfaceImpl newUI) {
        UI = newUI;
        UI.setTileLayout(SIDE);
        createGame();
        drawScene();
        handleKeyEvent(UI.getStage());
    }

    public static void handleKeyEvent(Stage stage) {
        stage.addEventHandler(KeyEvent.KEY_PRESSED, Game2048::onKeyPress);
    }

    private static void onKeyPress(KeyEvent e) {
        String key = e.getCode().getName().toUpperCase();
        if(isGameStopped && !key.equals("SPACE")) {
            return;
        }
        else if(isGameStopped) {
            isGameStopped = false;
            createGame();
            drawScene();
            return;
        }
        else if(!canUserMove()) {
            gameOver();
            return;
        }

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
        gameField = new int[SIDE][SIDE];
        score = 0;
        UI.drawScore(score);
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
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private static void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private static void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private static void createNewNumber() {
        int max = getMaxTileValue();
        if(max == 2048) {
            win();
        }
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

    private static boolean canUserMove() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                if (gameField[y][x] == 0) {
                    return true;
                } else if (y < SIDE - 1 && gameField[y][x] == gameField[y + 1][x]) {
                    return true;
                } else if ((x < SIDE - 1) && gameField[y][x] == gameField[y][x + 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void gameOver() {
        isGameStopped = true;
        //UI.showMessageDialog(Color.YELLOW, "GAME OVER", Color.RED, 18);
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
                    score += row[i];
                    UI.drawScore(score);
                    row[pointer] = 0;
                    result = true;
                }
            }
            pointer++;
        }
        return result;
    }

    private static void rotateClockwise() {
        int[][] result = new int[SIDE][SIDE];
        int lastCol = result.length -1;
        for(int[] row: gameField) {
            for(int i=0; i < row.length; i++) {
                result[i][lastCol] = row[i];
            }
            lastCol--;
        }
        gameField = result;
    }

    private static int getMaxTileValue() {
        int max = 0;
        for(int[] row: gameField) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
        }

    private static void win() {
        isGameStopped = true;
        //UI.showMessageDialog(Color.YELLOW, "CONGRATULATIONS, YOU WON!", Color.RED, 18);
    }

}

