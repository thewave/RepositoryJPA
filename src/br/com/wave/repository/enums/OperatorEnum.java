package br.com.wave.repository.enums;

public enum OperatorEnum {

	LESSER_THAN(" < "), 
	GREATER_THAN(" > "), 
	LESSER_EQUALS(" <= "), 
	GREATER_EQUALS(" >= "), 
	EQUALS(" = "), 
	NOT_EQUALS(" <> ");
	
	private String value;
	
	private OperatorEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
