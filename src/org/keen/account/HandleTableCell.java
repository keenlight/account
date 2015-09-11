package org.keen.account;

import static org.keen.account.UIUtils.removeEffect;
import static org.keen.account.UIUtils.setDefaultCursor;
import static org.keen.account.UIUtils.setDropShadow;
import static org.keen.account.UIUtils.setHandCursor;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class HandleTableCell extends TableCell<Account, String> {

	private Image openImg;
	private Image deleteImg;

	public HandleTableCell() {
		openImg = new Image(getClass().getResourceAsStream("view_account.png"), 15, 15,
				false, false);
		deleteImg = new Image(getClass().getResourceAsStream("delete_account.png"), 12, 12,
				false, false);
	}
	
	@Override
	protected void updateItem(String id, boolean empty) {
		super.updateItem(id, empty);
		if (!empty) {
			ImageView view = new ImageView(openImg);
			view.setOnMouseEntered(UIUtils.collect(setDropShadow(view), setHandCursor(view)));
			view.setOnMouseExited(UIUtils.collect(removeEffect(view), setDefaultCursor(view)));
			view.setOnMouseClicked(e -> {
				System.out.println("view_" + id);
			});
			ImageView delete = new ImageView(deleteImg);
			delete.setOnMouseEntered(UIUtils.collect(setDropShadow(delete), setHandCursor(delete)));
			delete.setOnMouseExited(UIUtils.collect(removeEffect(delete), setDefaultCursor(delete)));
			delete.setOnMouseClicked(e -> {
				System.out.println("delete_" + id);
			});
			HBox buttonBox = new HBox(7, view, delete);
			buttonBox.setAlignment(Pos.CENTER);
			setGraphic(buttonBox);
		}
	}
}
