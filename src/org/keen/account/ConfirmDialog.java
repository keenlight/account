package org.keen.account;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmDialog {
	
	private static boolean isConfirm;

	public static boolean show(final Stage stage) {
		new ConfirmDialog(stage);
		return isConfirm;
	}

	public ConfirmDialog(final Stage parentStage) {
		final Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(parentStage);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 100);

		Button confirm = new Button("确认");
		confirm.setLayoutX(100);
		confirm.setLayoutY(80);
		confirm.setOnAction(e -> {
			isConfirm = true;
			stage.hide();
		});
		
		Button cancel = new Button("取消");
		cancel.setLayoutX(300);
		cancel.setLayoutY(80);
		cancel.setOnAction(e -> {
			isConfirm = false;
			stage.hide();
		});

		root.getChildren().addAll(confirm, cancel);
		stage.setScene(scene);
		stage.show();
	}

}