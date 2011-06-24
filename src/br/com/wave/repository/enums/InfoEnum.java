package br.com.wave.repository.enums;

public enum InfoEnum {

	PERSIST("info.message.persist"),
	REMOVE("info.message.remove"),
	
	OPEN("info.message.open"),
	CLOSE("info.message.close"),
	
	BEGIN("info.message.begin"),
	COMMIT("info.message.commit"),
	ROLLBACK("info.message.rollback");

	private String key;

	private InfoEnum(String message) {
		this.key = message;
	}

	public String getMessage(Object... params) {
//		ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
//
//		String value = bundle.getString(this.key);
//		
//		return new MessageFormat(value).format(params);
		return this.key;
	}

}
