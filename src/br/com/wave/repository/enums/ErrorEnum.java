package br.com.wave.repository.enums;

public enum ErrorEnum {
	
	ANOTACAO_INEXISTENTE("erro.message.anotacao.inexistente"),
	INTERFACE_INEXISTENTE("erro.message.interface.inexistente"),
	ID_INEXISTENTE("erro.message.id.inexistente");
	
	private String message;

	private ErrorEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
