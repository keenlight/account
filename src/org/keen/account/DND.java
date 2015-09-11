package org.keen.account;

import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.text.Text;

public class DND {
	
	public static class AccountTableRow extends TableRow<Account> {
		
		@Override
		protected void updateItem(Account account, boolean empty) {
			super.updateItem(account, empty);
			if (!empty) {
				this.setOnMouseEntered(e -> {
					Node node = this.getChildren().get(0);
					if(node instanceof TableCell){
						TableCell<?, ?> firstCell = (TableCell<?, ?>)node;
						firstCell.getGraphic().setVisible(true);
					}
				});
				this.setOnMouseExited(e -> {
					Node node = this.getChildren().get(0);
					if(node instanceof TableCell){
						TableCell<?, ?> firstCell = (TableCell<?, ?>)node;
						firstCell.getGraphic().setVisible(false);
					}
				});
				this.setOnDragDetected(e -> {
					System.out.println("setOnDragDetected");
					Dragboard dragboard = this.startDragAndDrop(TransferMode.COPY);
					ClipboardContent content = new ClipboardContent();
					content.putString(account.getId());
					dragboard.setContent(content);
				});
			}
		}
	}

	public static class LabelListCell extends ListCell<AccountLabel> {
		
		public LabelListCell(){
			setPrefHeight(30);
		}
		
		@Override
		public void updateItem(AccountLabel label, boolean empty) {
			super.updateItem(label, empty);
			getStyleClass().add("labelListCell");
//			getStyleClass().remove("indexed-cell");
//			getStyleClass().remove("list-cell");
			if (!empty) {
				setText(label.getName());
				this.setOnDragDropped(e -> {
					System.out.println("setOnDragDropped");
					System.out.println(label.getId());
					Dragboard dragboard = e.getDragboard();
					String accountId = dragboard.getString();
				});
				this.setOnDragOver(e -> {
					if (e.getGestureSource() != this && e.getDragboard().hasString()) {
						e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
					}
				});
			} else {
				setText("");
			}
		}
	}

}
