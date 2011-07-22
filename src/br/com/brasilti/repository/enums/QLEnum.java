package br.com.brasilti.repository.enums;

public enum QLEnum {
	
	SELECT("select o"),
	COUNT("select count(o)"),
	FROM(" from "),
	WHERE(" o where "),
	AND(" and ");
	
	private String value;

	private QLEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
