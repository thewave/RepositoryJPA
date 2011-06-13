package br.com.wave.repository.core;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.repository.enums.ErrorEnum;
import br.com.wave.repository.examples.ClasseNaoSerializavel;
import br.com.wave.repository.examples.ClasseSemActive;
import br.com.wave.repository.examples.ClasseSemActiveBoolean;
import br.com.wave.repository.examples.ClasseSemAtEntity;
import br.com.wave.repository.examples.ClasseSemAtId;
import br.com.wave.repository.examples.ClasseSemAtVersion;
import br.com.wave.repository.examples.ClasseSemConstrutorPadrao;
import br.com.wave.repository.examples.ClasseSemId;
import br.com.wave.repository.examples.ClasseSemIdLong;
import br.com.wave.repository.examples.ClasseSemVersion;
import br.com.wave.repository.examples.ClasseSemVersionInteger;
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
			assertEquals(ErrorEnum.ANNOTATION_ENTITY_NOT_FOUND.getMessage(), e.getMessage());
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
			assertEquals(ErrorEnum.INTERFACE_NOT_FOUND.getMessage(), e.getMessage());
		}
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoAClasseNaoTiverUmAtributoIdException() throws RepositoryException {
		this.klass = ClasseSemId.class;

		this.validator.validate(this.klass);
	}

	@Test
	public void deveLancarExcecaoQuandoAClasseNaoTiverUmAtributoId() {
		this.klass = ClasseSemId.class;

		try {
			this.validator.validate(this.klass);
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.ID_NOT_FOUND.getMessage(), e.getMessage());
		}
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarUmaExcecaoQuandoOIdDaClasseNaoForDoTipoLongException() throws RepositoryException {
		this.klass = ClasseSemIdLong.class;

		this.validator.validate(this.klass);
	}

	@Test
	public void deveLancarUmaExcecaoQuandoOIdDaClasseNaoForDoTipoLong() {
		this.klass = ClasseSemIdLong.class;

		try {
			this.validator.validate(this.klass);
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.ID_NOT_LONG.getMessage(), e.getMessage());
		}
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarUmaExcecaoQuandoOIdDaClasseNaoEstiverAnotadoComAtIdException() throws RepositoryException {
		this.klass = ClasseSemAtId.class;

		this.validator.validate(this.klass);
	}

	@Test
	public void deveLancarUmaExcecaoQuandoOIdDaClasseNaoEstiverAnotadoComAtId() {
		this.klass = ClasseSemAtId.class;

		try {
			this.validator.validate(this.klass);
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.ANNOTATION_ID_NOT_FOUND.getMessage(), e.getMessage());
		}
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoAClasseNaoTiverUmAtributoVersionException() throws RepositoryException {
		this.klass = ClasseSemVersion.class;

		this.validator.validate(this.klass);
	}

	@Test
	public void deveLancarExcecaoQuandoAClasseNaoTiverUmAtributoVersion() {
		this.klass = ClasseSemVersion.class;

		try {
			this.validator.validate(this.klass);
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.VERSION_NOT_FOUND.getMessage(), e.getMessage());
		}
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarUmaExcecaoQuandoOVersionDaClasseNaoForDoTipoIntegerException() throws RepositoryException {
		this.klass = ClasseSemVersionInteger.class;

		this.validator.validate(this.klass);
	}

	@Test
	public void deveLancarUmaExcecaoQuandoOVersionDaClasseNaoForDoTipoInteger() {
		this.klass = ClasseSemVersionInteger.class;

		try {
			this.validator.validate(this.klass);
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.VERSION_NOT_INTEGER.getMessage(), e.getMessage());
		}
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarUmaExcecaoQuandoOVersionDaClasseNaoEstiverAnotadoComAtVersionException() throws RepositoryException {
		this.klass = ClasseSemAtVersion.class;

		this.validator.validate(this.klass);
	}

	@Test
	public void deveLancarUmaExcecaoQuandoOVersionDaClasseNaoEstiverAnotadoComAtVersion() {
		this.klass = ClasseSemAtVersion.class;

		try {
			this.validator.validate(this.klass);
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.ANNOTATION_VERSION_NOT_FOUND.getMessage(), e.getMessage());
		}
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoAClasseNaoTiverUmAtributoActiveException() throws RepositoryException {
		this.klass = ClasseSemActive.class;

		this.validator.validate(this.klass);
	}

	@Test
	public void deveLancarExcecaoQuandoAClasseNaoTiverUmAtributoActive() {
		this.klass = ClasseSemActive.class;

		try {
			this.validator.validate(this.klass);
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.ACTIVE_NOT_FOUND.getMessage(), e.getMessage());
		}
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarUmaExcecaoQuandoOActiveDaClasseNaoForDoTipoBooleanException() throws RepositoryException {
		this.klass = ClasseSemActiveBoolean.class;

		this.validator.validate(this.klass);
	}

	@Test
	public void deveLancarUmaExcecaoQuandoOActiveDaClasseNaoForDoTipoBoolean() {
		this.klass = ClasseSemActiveBoolean.class;

		try {
			this.validator.validate(this.klass);
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.ACTIVE_NOT_BOOLEAN.getMessage(), e.getMessage());
		}
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoAClasseNaoTiverConstrutorPadraoException() throws RepositoryException {
		this.klass = ClasseSemConstrutorPadrao.class;

		this.validator.validate(this.klass);
	}

	@Test
	public void deveLancarExcecaoQuandoAClasseNaoTiverConstrutorPadrao() {
		this.klass = ClasseSemConstrutorPadrao.class;

		try {
			this.validator.validate(this.klass);
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.CONSTRUCTOR_NOT_FOUND.getMessage(), e.getMessage());
		}
	}

	@After
	public void tearDown() {
		this.validator = null;
	}

}
