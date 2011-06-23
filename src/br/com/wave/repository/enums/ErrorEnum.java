package br.com.wave.repository.enums;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public enum ErrorEnum {
	
	ANNOTATION_ENTITY_NOT_FOUND("error.message.annotationEntityNotFound"),
	INTERFACE_NOT_FOUND("error.message.interfaceNotFound"),
	CONSTRUCTOR_NOT_FOUND("error.message.constructorNotFound"),
	
	ID_NOT_FOUND("error.message.idNotFound"),
	ID_NOT_LONG("error.message.idNotLong"),
	ANNOTATION_ID_NOT_FOUND("error.message.annotationIdNotFound"),
	
	VERSION_NOT_FOUND("error.message.versionNotFound"),
	VERSION_NOT_INTEGER("error.message.versionNotInteger"),
	ANNOTATION_VERSION_NOT_FOUND("error.message.annotationVersionNotFound"),
	
	ACTIVE_NOT_FOUND("error.message.activeNotFound"),
	ACTIVE_NOT_BOOLEAN("error.message.activeNotBoolean");
	
	private String key;

	private ErrorEnum(String key) {
		this.key = key;
	}
	
	public String getMessage(Object... params) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

		String value = bundle.getString(this.key);
		
		return new MessageFormat(value).format(params);
	}
	
}
