package org.keen.account;

import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class DND {
	
	public static class AccountTableRow extends TableRow<Account> {
		
		private Image image;
		public AccountTableRow(){
			image = new Image(getClass().getResourceAsStream("arrows_drag_horiz_21.714285714286px_1182489_easyicon.net.png"), 9,9,false, false);
		}
		
		@Override
		protected void updateItem(Account account, boolean empty) {
			this.getChildren().stream().forEach(e -> {
				System.out.println(((TableCell)e).getText());
			});;
			super.updateItem(account, empty);
			if (!empty) {
				this.setOnMouseEntered(e -> {
//					((MyTableColumn)this.getTableView().getColumns().get(0)).showDndIcon();
				});
				this.setOnMouseExited(e -> {
//					((MyTableColumn)this.getTableView().getColumns().get(0)).hideDndIcon();
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
