package jp.ac.aiit.jointry.models.blocks.statement.codeblock;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import jp.ac.aiit.jointry.models.blocks.statement.Statement;

public class While extends CodeBlock {

    public While() {
        super();

        p = new Polygon();
        resize();
        p.setFill(getColor());
        p.setStroke(Color.GRAY);

        getChildren().addAll(p);

        // コネクタを全面にするために
        p.toBack();
    }

    public static Color getColor() {
        return Color.LIGHTGREEN;
    }

    public String intern() {
        StringBuilder sb = new StringBuilder();
        sb.append("index = 0\n");
        sb.append("while index < 10 {\n");
        for (Statement p : childBlocks) {
            if (p.prevBlock == null) {
                sb.append(p.intern());
                sb.append("\n");
                break;
            }
        }
        sb.append("index = index + 1; \n");
        sb.append("}\n");
        sb.append("index = 0; \n");
        if (nextBlock != null) {
            sb.append(nextBlock.intern());
        }
        return sb.toString();
    }

    public String blockIntern() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(" index < 10\n");

        for (Statement block : childBlocks) {
            sb.append(block.blockIntern());
            break;
        }

        if (nextBlock != null) {
            sb.append(nextBlock.blockIntern());
        }

        return sb.toString();
    }

    public Label getLabel() {
        return new Label("くりかえす");
    }
}
