package jp.ac.aiit.jointry.services.lang.ast;

import java.util.List;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.util.Duration;
import jp.ac.aiit.jointry.services.lang.parser.Environment;
import jp.ac.aiit.jointry.models.Sprite;

public class RotateStmnt extends ASTList {

    public RotateStmnt(List<ASTree> list) {
        super(list);
    }

    public ASTree condition() {
        return child(0);
    }

    @Override
    public String toString() {
        return "(rotate " + condition() + ")";
    }

    @Override
    public Object eval(Environment env) {
        Object c = ((ASTree) condition()).eval(env);
        if (c instanceof Integer) {
            Sprite sprite = env.getSprite();
            SequentialTransition st = env.getSequentialTransition();
            RotateTransition rt = new RotateTransition(Duration.millis(env.getSpeed()), sprite);
            rt.setByAngle((Integer) c);
            rt.setAutoReverse(true);
            st.getChildren().add(rt);
        }

        return c;
    }
}
