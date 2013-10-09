/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.ac.aiit.jointry.util;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StageUtil<P> {

    private Object controller;
    private Stage stage;

    public StageUtil(Stage stage, Window owner, URL fxml, final P resources) {
        if (stage == null) stage = new Stage();

        if (owner != null) {
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(owner);
        }

        if (fxml != null) {
            FXMLLoader loder = new FXMLLoader(fxml);

            try {
                Parent root = (Parent) loder.load();
                stage.setScene(new Scene(root));
            } catch (IOException ex) {
                Logger.getLogger(StageUtil.class.getName()).log(Level.SEVERE, null, ex);
            }

            controller = loder.getController();

            if (controller instanceof ParameterAware)
                ((ParameterAware<P>) controller).setParameter(resources);


            this.stage = stage;
        }
    }

    public Stage getStage() {
        return stage;
    }

    public Object getController() {
        return controller;
    }
}
