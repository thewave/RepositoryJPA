package br.com.brasilti.repository.enums;

public enum OrderEnum {

	ASC("asc"),
	DESC("desc");

	private String value;

	private OrderEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
