package game2048.computationlogic;


import javafx.scene.paint.Color;
import java.util.Arrays;

public class Utilities {

    public static int getRandomNumber(int maxNumber) {
        return (int) (Math.random() * maxNumber);
    }

    public static Color getColorByValue(int value) {
        Color color;
        switch (value) {
            case 2:
                color = Color.rgb(253,205,86);
                break;

            case 4:
                color = Color.rgb(158,210,106);
                break;

            case 8:
                color = Color.rgb(235,134,190);
                break;

            case 16:
                color = Color.rgb(70, 206, 172);
                break;

            case 32:
                color = Color.rgb(235,84,99);
                break;

            case 64:
                color = Color.rgb(172,146,234);
                break;

            case 128:
                color = Color.rgb(79,192,232);
                break;

            case 256:
                color = Color.rgb(0,255,255);
                break;

            case 512:
                color = Color.rgb(74,136,218);
                break;

            case 1024:
                color = Color.rgb(250,108,81);
                break;

            case 2048:
                color = Color.rgb(34, 34, 34);
                break;

            default:
                color = Color.TRANSPARENT;
        }
        return color;
    }



}
