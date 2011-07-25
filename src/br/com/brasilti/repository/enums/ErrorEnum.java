package br.com.brasilti.repository.enums;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public enum ErrorEnum {
	
	NULL_CLASS("error.message.nullClass"),
	NULL_INSTANCE("error.message.nullInstance"),
	
	ANNOTATION_ENTITY_NOT_FOUND("error.message.annotationEntityNotFound"),
	INTERFACE_NOT_FOUND("error.message.interfaceNotFound"),
	
	ID_NOT_FOUND("error.message.idNotFound"),
	ID_NOT_LONG("error.message.idNotLong"),
	ANNOTATION_ID_NOT_FOUND("error.message.annotationIdNotFound"),
	
	VERSION_NOT_FOUND("error.message.versionNotFound"),
	VERSION_NOT_INTEGER("error.message.versionNotInteger"),
	ANNOTATION_VERSION_NOT_FOUND("error.message.annotationVersionNotFound"),
	
	ACTIVE_NOT_FOUND("error.message.activeNotFound"),
	ACTIVE_NOT_BOOLEAN("error.message.activeNotBoolean"),
	
	CONSTRUCTOR_NOT_FOUND("error.message.constructorNotFound"),
	METHOD_NOT_FOUND("error.message.methodNotFound"),
	
	NULL_SUBJECT("error.message.nullSubject"),
	EMPTY_PREDICATIVE("error.message.emptyPredicative"),
	
	MORE_THAN_ONE_INSTANCE("error.message.moreThanOneInstance"),
	
	ORDER_NOT_ALLOWED("error.message.orderNotAllowed"),
	
	UNEXPECTED_EXCEPTION("error.message.unexpectedException");
	
	private String key;

	private ErrorEnum(String key) {
		this.key = key;
	}
	
	public String getMessage(Object... params) {
		ResourceBundle bundle = ResourceBundle.getBundle("br.com.brasilti.repository.messages.messages", Locale.getDefault());

		String value = bundle.getString(this.key);
		
		return new MessageFormat(value).format(params);
	}
	
}
