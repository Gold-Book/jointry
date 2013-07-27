package jp.ac.aiit.jointry.models.blocks;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import static jp.ac.aiit.jointry.models.blocks.Move.getColor;

public class Costume extends Statement {

    // TODO: dummy
    public Costume() {
        super();
        rect.setFill(getColor());
    }

    public static Color getColor() {
        return Color.AQUA;
    }

    public Label getLabel() {
        return new Label("コスチューム");
    }

    public String intern() {
        StringBuilder sb = new StringBuilder();
        sb.append("costume");
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
}
