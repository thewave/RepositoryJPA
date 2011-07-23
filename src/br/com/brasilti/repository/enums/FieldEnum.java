package br.com.brasilti.repository.enums;

public enum FieldEnum {

	ID("id"),
	VERSION("version"),
	ACTIVE("active");

	private String value;

	private FieldEnum(String value) {
		this.value = value;
	}

	public static boolean contains(String value) {
		FieldEnum[] enumerations = FieldEnum.values();
		for (FieldEnum enumeration : enumerations) {
			if (enumeration.getValue().equals(value)) {
				return true;
			}
		}

		return false;
	}

	public String getValue() {
		return value;
	}

}
