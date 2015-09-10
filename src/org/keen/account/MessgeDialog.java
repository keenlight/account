package org.keen.account;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 简单封装的消息弹出框
 * @author keenlight
 *
 */
public class MessgeDialog {

	public static void show(final Stage stage, final String message) {
		new MessgeDialog(stage, message);
	}

	public MessgeDialog(final Stage parentStage, String message) {
		final Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(parentStage);
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 500, 100);

		root.setCenter(new Label(message));
		Button confirm = new Button("确认");
		confirm.setOnAction(e -> {
			stage.close();
		});
		HBox buttonBox = new HBox(confirm);
		buttonBox.setAlignment(Pos.CENTER);
		root.setBottom(buttonBox);
		stage.setScene(scene);
		stage.show();
	}

}