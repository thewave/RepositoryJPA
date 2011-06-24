package br.com.wave.repository.exceptions;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.wave.repository.enums.ErrorEnum;


public class RepositoryExceptionTest {

	@Test
	public void deveExibirMensagemDoErrorEnum() {
		try {
			throw new RepositoryException(ErrorEnum.ACTIVE_NOT_BOOLEAN);
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.ACTIVE_NOT_BOOLEAN.getMessage(),e.getMessage());
		}
	}

	@Test
	public void deveExibirMensagemGeral() {
		String message = "Mensagem de erro capturada";
		try {
			throw new RepositoryException(message);
		} catch (RepositoryException e) {
			assertEquals(message,e.getMessage());
		}
	}

}
