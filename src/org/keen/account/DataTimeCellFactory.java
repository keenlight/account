package org.keen.account;

import java.time.LocalDate;

import javafx.scene.Cursor;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;

public class DataTimeCellFactory extends TableCell<Account, LocalDate>{

	private Image image;
	public DataTimeCellFactory(){
		image = new Image(getClass().getResourceAsStream("arrows_drag_horiz_21.714285714286px_1182489_easyicon.net.png"), 9,9,false, false);
	}
	
	@Override
	protected void updateItem(LocalDate date, boolean empty) {
		super.updateItem(date, empty);
		this.setBorder(Border.EMPTY);
		if(!empty){
			ImageView imageView = new ImageView(image);
			imageView.setOnMouseEntered(e -> {
				imageView.setCursor(Cursor.MOVE);
			});
			this.setOnMouseEntered(e -> {
				this.getGraphic().setVisible(true);
			});
			this.setOnMouseExited(e -> {
				this.getGraphic().setVisible(false);
			});
			this.setGraphic(imageView);
			this.getGraphic().setVisible(false);
			this.setText(date.toString());
		}
	}
}
