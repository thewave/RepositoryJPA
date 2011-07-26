package br.com.brasilti.repository.exceptions;

import br.com.brasilti.repository.enums.ErrorEnum;

/**
 * Excecao que apresenta as mensagens de erro da aplicacao.
 * 
 * @author Benedito Barbosa
 * @author Christian Peixoto
 * 
 * @see ErrorEnum
 * 
 */
public class RepositoryException extends Exception {

	private static final long serialVersionUID = 1L;

	private ErrorEnum errorEnum;

	private Object[] params;

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(ErrorEnum errorEnum, Object... params) {
		this.errorEnum = errorEnum;
		this.params = params;
	}

	/**
	 * Retorna a mensagem de erro.
	 */
	@Override
	public String getMessage() {
		if (this.errorEnum == null) {
			return super.getMessage();
		}

		return this.errorEnum.getMessage(this.params);
	}

}
