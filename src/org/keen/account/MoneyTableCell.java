package org.keen.account;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.TextAlignment;
import javafx.util.StringConverter;

public class MoneyTableCell extends TextFieldTableCell<Account, Double> {

	public MoneyTableCell(StringConverter<Double> sc){
		super(sc);
	}
	@Override
	public void updateItem(Double item, boolean empty) {
		super.updateItem(item, empty);
		if(!empty){
			setText(String.valueOf(item));
			setTextAlignment(TextAlignment.RIGHT);
		}else{
			setText("");
		}
	}
}
