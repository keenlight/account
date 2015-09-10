package org.keen.account.test;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.keen.account.ModalDialog;

public class DragAndDropTest extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		HBox root = new HBox(50);
		Button btn = new Button("confirm");
		btn.setOnAction(e -> {
			new ModalDialog(stage);
		});
		root.getChildren().addAll(m_drag, m_drop);
		func();
		stage.setScene(new Scene(root , 600, 400));
		stage.show();
	}
	
	private Label m_drag = new Label("m_drag");
	private Label m_drop = new Label("m_drop");
	public void func(){
		m_drag.setOnDragDetected(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				Dragboard dragboard = m_drag.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString(m_drag.getText());
				dragboard.setContent(content);
			}
		});

		m_drop.setOnDragEntered(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				m_drop.setTextFill(Color.RED);
			}
		});

		m_drop.setOnDragExited(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				m_drop.setTextFill(Color.BLACK);
			}
		});

		m_drop.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (event.getGestureSource() != m_drop && event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
			}
		});

		m_drop.setOnDragDropped(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				Dragboard dragboard = event.getDragboard();
				m_drop.setText(dragboard.getString());
			}
		});

		m_drag.setOnDragDone(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
//				m_drag.setText("");
			}
		});
	}

}
