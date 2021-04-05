package Game2048;

import userinterface.UserInterfaceImpl;

public class Game2048 {

    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];

    public static void initialize(UserInterfaceImpl UI) {
        UI.setTileLayout(SIDE);
    }

}
