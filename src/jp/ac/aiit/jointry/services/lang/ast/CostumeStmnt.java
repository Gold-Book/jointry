package jp.ac.aiit.jointry.services.lang.ast;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import jp.ac.aiit.jointry.services.lang.parser.Environment;
import jp.ac.aiit.jointry.models.Sprite;

public class CostumeStmnt extends ASTList {

    public CostumeStmnt(List<ASTree> list) {
        super(list);
    }

    public ASTree condition() {
        return child(0);
    }

    @Override
    public String toString() {
        return "(costume " + condition() + ")";
    }

    @Override
    public Object eval(Environment env) {
        final Object c = ((ASTree) condition()).eval(env);
        if (c instanceof Integer) {
            final Sprite sprite = env.getSprite();
            SequentialTransition st = env.getSequentialTransition();
            TranslateTransition tt
                    = new TranslateTransition(Duration.millis(10), sprite);
            tt.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    sprite.setSpriteCostume((int) c);
                }
            });
            st.getChildren().add(tt);
        }
        return c;
    }
}
