package org.keen.account.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import org.keen.account.MessgeDialog;

public class ModalDialogTest extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		HBox root = new HBox();
		Button btn = new Button("confirm");
		btn.setOnAction(e -> {
			new MessgeDialog(stage, "消息");
		});
		root.getChildren().add(btn);
		stage.setScene(new Scene(root , 600, 400));
		stage.show();
	}

}
