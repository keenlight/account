package org.keen.account;

import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class DND {
	
	public static class AccountTableRow extends TableRow<Account> {
		
		@Override
		protected void updateItem(Account account, boolean empty) {
			super.updateItem(account, empty);
			if (!empty) {
				this.setOnMouseEntered(e -> {
					((TableCell<Account, Image>)this.getChildren().get(0)).getGraphic().setVisible(true);
				});
				this.setOnMouseExited(e -> {
					((TableCell<Account, Image>)this.getChildren().get(0)).getGraphic().setVisible(false);
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
		@Override
		protected void updateItem(AccountLabel label, boolean empty) {
			super.updateItem(label, empty);
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
