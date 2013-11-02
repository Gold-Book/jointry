package jp.ac.aiit.jointry.models.blocks;

import javafx.scene.shape.Rectangle;

public class Connector extends Rectangle {

    private Block holder;
    private Position position;

    public enum Position {

        TOP, BOTTOM, LEFT, RIGHT, CENTER,
        INSIDE_LEFT, INSIDE_RIGHT
    };

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Block getHolder() {
        return holder;
    }

    public void setHolder(Block holder) {
        this.holder = holder;
    }
}
