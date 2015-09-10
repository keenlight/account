package org.keen.account;

import java.time.LocalDate;

import javafx.scene.Cursor;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;

public class MyTableColumn extends TableColumn<Account, LocalDate>{

	private DataTimeCellFactory tableRow;
	public MyTableColumn(String name){
		super(name);
		this.setCellFactory(cb -> {
			tableRow = new DataTimeCellFactory();
			return tableRow;
		});
	}
	
	public void hideDndIcon(){
		tableRow.getGraphic().setVisible(false);
	}
	
	public void showDndIcon(){
		tableRow.getGraphic().setVisible(true);
	}
	
	class DataTimeCellFactory extends TableCell<Account, LocalDate>{

		private Image image;
		public DataTimeCellFactory(){
			image = new Image(getClass().getResourceAsStream("arrows_drag_horiz_21.714285714286px_1182489_easyicon.net.png"), 9,9,false, false);
			this.setBorder(Border.EMPTY);
		}
		
		@Override
		protected void updateItem(LocalDate date, boolean empty) {
			super.updateItem(date, empty);
			if(!empty){
				ImageView imageView = new ImageView(image);
				imageView.setOnMouseEntered(e -> {
					imageView.setCursor(Cursor.MOVE);
				});
				this.setGraphic(imageView);
				this.getGraphic().setVisible(false);
				this.setText(date.toString());
			}
		}
	}
}
