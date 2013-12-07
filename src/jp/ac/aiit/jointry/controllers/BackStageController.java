package jp.ac.aiit.jointry.controllers;

import java.net.URL;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import jp.ac.aiit.jointry.services.lang.parser.Environment;
import jp.ac.aiit.jointry.models.Costume;
import jp.ac.aiit.jointry.models.Sprite;
import jp.ac.aiit.jointry.models.SpriteTask;
import jp.ac.aiit.jointry.models.blocks.Block;
import jp.ac.aiit.jointry.util.StageUtil;

public class BackStageController {

    @FXML
    private ScrollPane costumeList;
    @FXML
    private Tab scriptTab;
    private MainController mainController;
    private Environment env;

    @FXML
    protected void handlePaintBtnAct(ActionEvent event) throws Exception {
        Window owner = costumeList.getScene().getWindow(); //画面オーナー
        URL fxml = getClass().getResource("Paint.fxml"); //表示するfxml
        final StageUtil paintStage = new StageUtil(null, owner, fxml, null);

        //新規コスチューム追加
        paintStage.getStage().setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                PaintController ctrl = (PaintController) paintStage.getController();

                if (ctrl.getResult() != null) {
                    Sprite sprite = mainController.getFrontStageController().getCurrentSprite();
                    sprite.setSpriteCostume(sprite.addCostume("costume", ctrl.getResult()));
                    showCostumes(sprite);
                }
            }
        });

        paintStage.getStage().show();
    }

    public void showCostumes(Sprite sprite) {
        VBox vbox = new VBox();
        for (Costume costume : sprite.getCostumes()) {
            URL fxml = getClass().getResource("Costume.fxml"); //表示するfxml
            StageUtil costumeStage = new StageUtil(null, null, fxml, costume);

            CostumeCntroller controller = (CostumeCntroller) costumeStage.getController();
            controller.setMainController(mainController);
            vbox.getChildren().add(costumeStage.getParent());
        }
        costumeList.setContent(vbox);
    }

    @FXML
    protected void handleCamBtnAct(ActionEvent event) throws Exception {
        Window owner = costumeList.getScene().getWindow(); //画面オーナー
        URL fxml = getClass().getResource("Camera.fxml"); //表示するfxml
        final StageUtil cameraStage = new StageUtil(null, owner, fxml, null);

        //新規コスチューム追加
        cameraStage.getStage().setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                CameraController ctrl = (CameraController) cameraStage.getController();

                if (ctrl.getResult() != null) {
                    Sprite sprite = mainController.getFrontStageController().getCurrentSprite();
                    sprite.setSpriteCostume(sprite.addCostume("costume", ctrl.getResult()));
                    showCostumes(sprite);
                }
            }
        });

        cameraStage.getStage().show();
    }

    @FXML
    protected void handleCostumeSelected(Event event) {
        Sprite sprite = mainController.getFrontStageController().getCurrentSprite();
        setCurrentSprite(sprite);
    }

    public void setCurrentSprite(Sprite sprite) {
        showCostumes(sprite);
        showBlocks(sprite);
    }

    public void start(double speed) {
        for (Sprite sprite : mainController.getFrontStageController().getSprites()) {
            SpriteTask task = new SpriteTask();
            task.setSprite(sprite);
            task.setSpeed(speed);
            Thread th = new Thread(task);
            th.setDaemon(true);
            th.start();
        }
    }

    public void stop() {
        env.getSequentialTransition().stop();
    }

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }

    private void showBlocks(Sprite sprite) {
        //組み立てたブロックを表示
        scriptTab.setContent(sprite.getScriptPane());
    }

    public void addBlock(Block block) {
        AnchorPane ap = (AnchorPane) scriptTab.getContent();
        ap.getChildren().add(block);
    }
}
