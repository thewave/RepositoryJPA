package br.com.wave.repository.core;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.repository.enums.ErrorEnum;
import br.com.wave.repository.examples.ClasseNaoSerializavel;
import br.com.wave.repository.examples.ClasseSemAtEntity;
import br.com.wave.repository.examples.ClasseSerializavel;
import br.com.wave.repository.exceptions.RepositoryException;

public class ValidatorTest {
	
	private Validator validator;
	
	private Class<?> klass;
	
	@Before
	public void setUp() {
		this.validator = new Validator();
	}
	
	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoAClasseNaoEstiverAnotadaComAtEntityException() throws RepositoryException {
		this.klass = ClasseSemAtEntity.class;
		
		this.validator.validate(this.klass);
	}
	
	@Test
	public void deveLancarExcecaoQuandoAClasseNaoEstiverAnotadaComAtEntity() {
		this.klass = ClasseSemAtEntity.class;
		
		try {
			this.validator.validate(this.klass);
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.ANOTACAO_INEXISTENTE.getMessage(), e.getMessage());
		}
	}
	
	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoAClasseNaoForSerializavelException() throws RepositoryException {
		this.klass = ClasseNaoSerializavel.class;
		
		this.validator.validate(this.klass);
	}
	
	@Test
	public void deveLancarExcecaoQuandoAClasseNaoForSerializavel() {
		this.klass = ClasseNaoSerializavel.class;
		
		try {
			this.validator.validate(this.klass);
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.INTERFACE_INEXISTENTE.getMessage(), e.getMessage());
		}
	}
	
	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoAClasseNaoTiverUmAtributoIdException() throws RepositoryException {
		this.klass = ClasseSerializavel.class;
		
		this.validator.validate(this.klass);
	}
	
	@Test
	public void deveLancarExcecaoQuandoAClasseNaoTiverUmAtributoId() {
		this.klass = ClasseSerializavel.class;
		
		try {
			this.validator.validate(this.klass);
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.ID_INEXISTENTE.getMessage(), e.getMessage());
		}
	}
	
	@After
	public void tearDown() {
		this.validator = null;
	}

}
