package br.com.wave.repository.enums;

public enum FieldEnum {

	ID("id");

	private String value;

	private FieldEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
