package br.com.wave.repository.enums;

public enum InfoEnum {

	PERSIST("info.message.persist"),
	REMOVE("info.message.remove"),
	
	OPEN("info.message.open"),
	CLOSE("info.message.close"),
	
	BEGIN("info.message.begin"),
	COMMIT("info.message.commit"),
	ROLLBACK("info.message.rollback");

	private String message;

	private InfoEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
