package game2048.userinterface;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TextField {
    private final Rectangle tile;
    private final Text text;
    private final double width;

    public TextField(Rectangle tile, String value) {
        this.tile = tile;
        text = new Text(value);
        width = text.getLayoutBounds().getWidth();
        style();
        position();
    }

    public void draw(Group group) {
        group.getChildren().add(text);
    }

    public void remove(Group group) {
        group.getChildren().remove(text);
    }

    public void fillBackground(Color color) {
        tile.setFill(color);
    }

    public void setText(String value) {
        text.setText(value);
    }

    public void setSize(double size) {
        text.setScaleX(size);
        text.setScaleY(size);
    }

    private void style() {
        text.setScaleX(4);
        text.setScaleY(4);
        text.setFont(Font.font("Segoe UI Black"));
        text.setFill(Color.WHITE);
    }

    private void position() {
        double posX = tile.getX() + (tile.getWidth() / 2 - width / 2);
        double posY = tile.getY() + (tile.getHeight() / 2);
        text.setX(posX);
        text.setY(posY);
    }

}
