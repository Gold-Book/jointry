package jp.ac.aiit.jointry.blocks;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Rotate extends Block {

    public Rotate() {
        super();
        rect.setFill(getColor());
    }

    public String intern() {
        StringBuilder sb = new StringBuilder();
        sb.append("rotate");
        String arg = tf.getText();
        if (arg == null) {
            arg = "0";
        }
        sb.append(" " + arg + "\n");
        if (nextBlock != null) {
            sb.append(nextBlock.intern());
        }
        return sb.toString();
    }

    public static Color getColor() {
        return Color.RED;
    }

    public Label getLabel() {
        return new Label("回転する");
    }
}
