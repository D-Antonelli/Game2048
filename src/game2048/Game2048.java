package game2048;


import game2048.userinterface.UserInterfaceImpl;
import game2048.computationlogic.Utilities;
import javafx.scene.paint.Color;

import java.util.Arrays;


public class Game2048 {

    private static final int SIDE = 4;
    private static final int[][] gameField = new int[SIDE][SIDE];
    private static UserInterfaceImpl UI;

    public static void initialize(UserInterfaceImpl newUI) {
        UI = newUI;
        UI.setTileLayout(SIDE);
        createGame();
        drawScene();
    }

    private static void createGame() {
        createNewNumber();
        createNewNumber();
    }

    private static void drawScene() {
        for(int x= 0; x < SIDE; x++ ) {
            for(int y=0; y < SIDE; y++) {
                setCellColoredNumber(x, y, gameField[y][x]);
            }
        }
    }

    private static void createNewNumber() {
        int x = Utilities.getRandomNumber(SIDE);
        int y = Utilities.getRandomNumber(SIDE);
        /*
            POSSIBILITY OF NUMBER 2 IS 90%,
            WHILE NUMBER 4 IS 10%
         */
        int random = Utilities.getRandomNumber(10);

        if(gameField[x][y] == 0) {
            if(random == 9) {
                gameField[x][y] = 4;
            } else {
                gameField[x][y] = 2;
            }
        } else {
            createNewNumber();
        }
    }

    private static void setCellColoredNumber(int x, int y, int value) {
        Color color = Utilities.getColorByValue(value);
        if(value == 0) {
            UI.setTileValueEx(x, y, color, "");
        } else {
            UI.setTileValueEx(x, y, color, Integer.toString(value));
        }
    }

    public static boolean compressRow(int[] row) {
       int insertPosition = 0;
       boolean isCompressed = false;
       for(int x =0; x < SIDE; x++) {
           if(row[x] > 0) {
               if(x != insertPosition) {
                   row[insertPosition] = row[x];
                   row[x] = 0;
                   isCompressed = true;
               }
               insertPosition++;
           }
       }
       return isCompressed;

    }
}
