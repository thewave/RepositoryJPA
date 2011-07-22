package br.com.brasilti.repository.exceptions;

import br.com.brasilti.repository.enums.ErrorEnum;

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

	@Override
	public String getMessage() {
		if (this.errorEnum == null) {
			return super.getMessage();
		}

		return this.errorEnum.getMessage(this.params);
	}

}
