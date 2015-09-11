package org.keen.account;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class UIUtils {
	
	@SafeVarargs
	public static EventHandler<? super MouseEvent> collect(EventHandler<? super MouseEvent>... es){
		return e -> {
			for(EventHandler<? super MouseEvent> item : es){
				item.handle(e);
			}
		};
	}
	
	public static EventHandler<? super MouseEvent> setDropShadow(Node node) {
		return e -> {
			DropShadow ds = new DropShadow();
	        ds.setOffsetY(3.0);
	        ds.setOffsetX(3.0);
	        ds.setColor(Color.GRAY);
	        node.setEffect(ds);
		};
	}
	
	public static EventHandler<? super MouseEvent> removeEffect(Node node) {
		return e -> {
			node.setEffect(null);
		};
	}
	
	public static EventHandler<? super MouseEvent> setHandCursor(Node node) {
		return e -> {
			node.setCursor(Cursor.HAND);
		};
	}
	
	public static EventHandler<? super MouseEvent> setDefaultCursor(Node node) {
		return e -> {
			node.setCursor(Cursor.DEFAULT);
		};
	}
}
