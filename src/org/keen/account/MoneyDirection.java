package org.keen.account;

public enum MoneyDirection {
	in("收入"), out("支出");
	
	private String name;
	MoneyDirection(String name){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
