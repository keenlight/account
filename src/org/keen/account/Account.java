package org.keen.account;

import java.time.LocalDate;
import java.util.UUID;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {

	private String id = UUID.randomUUID().toString();
	private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
	private StringProperty moneyDirection = new SimpleStringProperty();
	private DoubleProperty money = new SimpleDoubleProperty();
	private StringProperty desc = new SimpleStringProperty();

	public Account() {
	}

	public Account(LocalDate date, String moneyDirection, double money,
			String desc) {
		this.setDate(date);
		this.setMoneyDirection(moneyDirection);
		this.setMoney(money);
		this.setDesc(desc);
	}

	public String getId() {
		return id;
	}

	public LocalDate getDate() {
		return date.get();
	}

	public void setDate(LocalDate date) {
		this.date.set(date);
	}

	public ObjectProperty<LocalDate> dateProperty() {
		return date;
	}

	public String getMoneyDirection() {
		return moneyDirection.get();
	}

	public void setMoneyDirection(String moneyDirection) {
		this.moneyDirection.set(moneyDirection);
	}

	public StringProperty moneyDirectionProperty() {
		return moneyDirection;
	}

	public double getMoney() {
		return money.get();
	}

	public void setMoney(double money) {
		this.money.set(money);
	}

	public DoubleProperty moneyProperty() {
		return money;
	}

	public String getDesc() {
		return desc.get();
	}

	public void setDesc(String desc) {
		this.desc.set(desc);
	}

	public StringProperty descProperty() {
		return desc;
	}

}
