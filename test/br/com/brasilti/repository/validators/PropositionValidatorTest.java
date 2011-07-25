package br.com.brasilti.repository.validators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.brasilti.repository.enums.ErrorEnum;
import br.com.brasilti.repository.exceptions.RepositoryException;
import br.com.brasilti.repository.propositions.And;
import br.com.brasilti.repository.propositions.Equals;
import br.com.brasilti.repository.propositions.In;
import br.com.brasilti.repository.propositions.IsNotNull;
import br.com.brasilti.repository.propositions.IsNull;
import br.com.brasilti.repository.propositions.Not;
import br.com.brasilti.repository.propositions.NotIn;
import br.com.brasilti.repository.propositions.Or;
import br.com.brasilti.repository.propositions.Order;

public class PropositionValidatorTest {

	private PropositionValidator validator;

	@Before
	public void setUp() {
		this.validator = new PropositionValidator();
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoAProposicaoExigirUmSujeitoException() throws RepositoryException {
		this.validator.validate(new Equals(null, 0));
	}

	@Test
	public void deveLancarExcecaoQuandoAProposicaoExigirUmSujeito() {
		try {
			this.validator.validate(new Equals(null, 0));
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.NULL_SUBJECT.getMessage(), e.getMessage());
		}
	}

	@Test
	public void naoDeveLancarExcecaoQuandoAProposicaoNaoExigirUmSujeitoException() {
		try {
			this.validator.validate(new Not(new Equals("", 0)));
		} catch (RepositoryException e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoAProposicaoExigirUmPredicativoException() throws RepositoryException {
		this.validator.validate(new Equals("", null));
	}

	@Test
	public void deveLancarExcecaoQuandoAProposicaoExigirUmPredicativo() {
		try {
			this.validator.validate(new Equals("", null));
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.EMPTY_PREDICATIVE.getMessage(), e.getMessage());
		}
	}

	@Test
	public void naoDeveLancarExcecaoQuandoAProposicaoNaoExigirUmPredicativoException() {
		try {
			this.validator.validate(new IsNull(""));
		} catch (RepositoryException e) {
			fail(e.getMessage());
		}

		try {
			this.validator.validate(new IsNotNull(""));
		} catch (RepositoryException e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoHouverPredicativoVazioParaAProposicaoInException() throws RepositoryException {
		this.validator.validate(new In(""));
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoHouverPredicativoVazioParaAProposicaoNotInException() throws RepositoryException {
		this.validator.validate(new NotIn(""));
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoHouverPredicativoVazioParaAProposicaoAndException() throws RepositoryException {
		this.validator.validate(new And(new Equals("", 0)));
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoHouverPredicativoVazioParaAProposicaoOrException() throws RepositoryException {
		this.validator.validate(new Or(new Equals("", 0)));
	}

	@Test
	public void deveLancarExcecaoQuandoAProposicaoExigirUmPredicativoNaoVazio() {
		try {
			this.validator.validate(new In(""));
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.EMPTY_PREDICATIVE.getMessage(), e.getMessage());
		}

		try {
			this.validator.validate(new NotIn(""));
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.EMPTY_PREDICATIVE.getMessage(), e.getMessage());
		}

		try {
			this.validator.validate(new And(new Equals("", 0)));
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.EMPTY_PREDICATIVE.getMessage(), e.getMessage());
		}

		try {
			this.validator.validate(new Or(new Equals("", 0)));
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.EMPTY_PREDICATIVE.getMessage(), e.getMessage());
		}
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoHouverPredicativoDeListaVazioParaAProposicaoInException() throws RepositoryException {
		List<String> list = new ArrayList<String>();
		list.add(null);

		this.validator.validate(new In("", list));
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoHouverPredicativoDeListaVazioParaAProposicaoNotInException() throws RepositoryException {
		List<String> list = new ArrayList<String>();
		list.add(null);

		this.validator.validate(new NotIn("", list));
	}

	@Test
	public void deveLancarExcecaoQuandoAProposicaoExigirUmPredicativoListaNaoVazio() {
		List<String> list = new ArrayList<String>();
		list.add(null);

		try {
			this.validator.validate(new In("", list));
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.EMPTY_PREDICATIVE.getMessage(), e.getMessage());
		}

		try {
			this.validator.validate(new NotIn("", list));
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.EMPTY_PREDICATIVE.getMessage(), e.getMessage());
		}
	}

	@Test
	public void naoDeveLancarExcecaoQuandoAProposicaoPermitirUmPredicativoNaoVazioException() {
		try {
			this.validator.validate(new In("", "1", "2", "3"));
		} catch (RepositoryException e) {
			fail(e.getMessage());
		}

		try {
			this.validator.validate(new NotIn("", "1", "2", "3"));
		} catch (RepositoryException e) {
			fail(e.getMessage());
		}

		try {
			this.validator.validate(new And(new Equals("", 0), new Equals("", 0)));
		} catch (RepositoryException e) {
			fail(e.getMessage());
		}

		try {
			this.validator.validate(new Or(new Equals("", 0), new Equals("", 0)));
		} catch (RepositoryException e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoAProposicaoTiverProposicoesInvalidas() throws RepositoryException {
		this.validator.validate(new And(new Equals(null, 0), new Equals("", null), new In(""), new Or(new Equals("", 0))));
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoUmaProposicaoCompostaTiverOrderComoSujeitoException() throws RepositoryException {
		this.validator.validate(new And(new Order(""), new Equals("", 0)));
	}

	@Test
	public void deveLancarExcecaoQuandoUmaProposicaoCompostaTiverOrderComoSujeito() {
		try {
			this.validator.validate(new And(new Order(""), new Equals("", 0)));
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.ORDER_NOT_ALLOWED.getMessage(), e.getMessage());
		}
	}

	@Test(expected = RepositoryException.class)
	public void deveLancarExcecaoQuandoUmaProposicaoCompostaTiverOrderComoPredicativoException() throws RepositoryException {
		this.validator.validate(new And(new Equals("", 0), new Order("")));
	}

	@Test
	public void deveLancarExcecaoQuandoUmaProposicaoCompostaTiverOrderComoPredicativo() {
		try {
			this.validator.validate(new And(new Equals("", 0), new Order("")));
		} catch (RepositoryException e) {
			assertEquals(ErrorEnum.ORDER_NOT_ALLOWED.getMessage(), e.getMessage());
		}
	}

	@After
	public void tearDown() {
		this.validator = null;
	}

}
