package org.keen.account;

import java.time.LocalDate;

import javafx.scene.Cursor;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;

public class DataTimeTableCell extends TableCell<Account, LocalDate> {

	private Image image;

	public DataTimeTableCell() {
		image = new Image(getClass().getResourceAsStream("arrows_drag_vert_931px_1182496_easyicon.net.png"), 7, 9,
				false, false);
	}

	@Override
	protected void updateItem(LocalDate date, boolean empty) {
		super.updateItem(date, empty);
		if (!empty) {
			ImageView imageView = new ImageView(image);
			this.setOnMouseMoved(e -> {
				double x = e.getX();
				if (x < image.getWidth()) {
					this.setCursor(Cursor.MOVE);
				} else {
					this.setCursor(Cursor.DEFAULT);
				}
			});
			this.setGraphic(imageView);
			imageView.setVisible(false);
			this.setText(date.toString());
		}else{
			setBorder(Border.EMPTY);
		}
	}
}
