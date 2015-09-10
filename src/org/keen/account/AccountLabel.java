package org.keen.account;

import java.util.List;
import java.util.UUID;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class AccountLabel {

	private String id = UUID.randomUUID().toString();

	private StringProperty name = new SimpleStringProperty();
	private ListProperty<Account> accountList = new SimpleListProperty<Account>();

	public AccountLabel(){}
	
	public AccountLabel(String name, List<Account> accountList){
		this.setName(name);
		this.setAccountList(accountList);
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty nameProperty() {
		return name;
	}

	public List<Account> getAccountList() {
		return accountList.get();
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList.set(FXCollections.observableArrayList(accountList));
	}

	public ListProperty<Account> accountListProperty() {
		return accountList;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
