package br.com.wave.repository.enums;

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
	
	private String message;

	private ErrorEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
