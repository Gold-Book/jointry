package jp.ac.aiit.jointry.models.blocks.statement.procedure;

import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import jp.ac.aiit.jointry.models.Status;

public class Rotate extends Procedure {

    protected ComboBox cb;

    public Rotate() {
        super();
        rect.setFill(getColor());
        cb = new ComboBox();
        cb.setEditable(true);
        cb.setMaxWidth(75.0);
        AnchorPane.setTopAnchor(cb, 10.0);
        AnchorPane.setLeftAnchor(cb, 80.0);
        cb.setItems(FXCollections.observableArrayList(
                "0", "30", "60", "90", "120", "150", "180"));
        getChildren().add(cb);
        setChangeListener(cb);
    }

    public static Color getColor() {
        return Color.ORANGERED;
    }

    @Override
    public String intern() {
        StringBuilder sb = new StringBuilder();
        sb.append("rotate");
        String arg = (String) cb.getValue();
        if (arg == null) {
            arg = "0";
        }
        sb.append(" ");
        sb.append(arg);
        sb.append("\n");
        if (nextBlock != null) {
            sb.append(nextBlock.intern());
        }
        return sb.toString();
    }

    @Override
    public Status getStatus() {
        Status status = new Status();

        status.put("id", this.getUUID());
        String arg = (String) cb.getValue();
        if (arg == null) {
            arg = "0";
        }

        status.put("rotate", arg);

        return status;
    }

    @Override
    public void setStatus(Status status) {
        changeable = false; //一時的にリスナーを無効化

        this.setUUID((String) status.get("id"));
        cb.setValue(status.get("rotate"));

        changeable = true;
    }

    @Override
    public Label getLabel() {
        return new Label("かいてんする");
    }
}
