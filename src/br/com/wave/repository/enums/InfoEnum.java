package br.com.wave.repository.enums;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

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
		ResourceBundle bundle = ResourceBundle.getBundle("br.com.wave.repository.messages.messages", Locale.getDefault());

		String value = bundle.getString(this.key);
		
		return new MessageFormat(value).format(params);
	}

}
