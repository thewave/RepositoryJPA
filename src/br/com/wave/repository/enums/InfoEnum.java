package br.com.wave.repository.enums;

public enum InfoEnum {

	PERSIST("info.message.persist"),
	REMOVE("info.message.remove"),
	OPEN("info.message.open"),
	CLOSE("info.message.close");

	private String message;

	private InfoEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
