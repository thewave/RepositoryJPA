package br.com.brasilti.repository.enums;

public enum MethodEnum {

	EQUALS("equals"),
	HASHCODE("hashCode");

	private String value;

	private MethodEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
