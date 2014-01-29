package org.comtel.javafx;

import java.util.Locale;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import org.comtel.javafx.control.KeyBoardPopup;
import org.comtel.javafx.control.KeyBoardPopupBuilder;
import org.comtel.javafx.robot.RobotFactory;

public class FxStandAloneApp extends Application {

	private int posX = 0;
	private int posY = 0;

	@Override
	public void start(Stage stage) {

		stage.setTitle("FX Keyboard (" + System.getProperty("javafx.runtime.version") + ")");
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);

		String fontUrl = this.getClass().getResource("/font/FontKeyboardFX.ttf").toExternalForm();
		Font.loadFont(fontUrl, -1);

		final KeyBoardPopup popup = KeyBoardPopupBuilder.create().initScale(1.0).initLocale(Locale.ENGLISH).addIRobot(RobotFactory.createNativeAsciiRobot()).build();

		Scene scene = new Scene(new Group(), 1, 1);
		popup.setOwner(scene);

		scene.getStylesheets().add(this.getClass().getResource("/css/KeyboardButtonStyle.css").toExternalForm());

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		stage.setScene(scene);
		stage.show();

		popup.getScene().getWindow().setX(posX);
		popup.getScene().getWindow().setY(posY);

		popup.show(stage);

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
