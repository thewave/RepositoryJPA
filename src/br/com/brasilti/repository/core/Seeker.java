package br.com.brasilti.repository.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.brasilti.repository.enums.ErrorEnum;
import br.com.brasilti.repository.enums.FieldEnum;
import br.com.brasilti.repository.exceptions.RepositoryException;
import br.com.brasilti.repository.propositions.Equals;
import br.com.brasilti.repository.propositions.Proposition;
import br.com.brasilti.repository.validators.PropositionValidator;

public class Seeker {

	private StringBuilder builder;

	@Inject
	private EntityManager manager;

	@Inject
	private PropositionValidator validator;

	public Seeker() {
		this.builder = new StringBuilder();
	}

	public <T> List<T> seekAll(Class<T> klass, Proposition... propositions) throws RepositoryException {
		return this.seekAll(klass, new ArrayList<Proposition>(Arrays.asList(propositions)));
	}

	public <T> List<T> seekAll(Class<T> klass, List<Proposition> propositions) throws RepositoryException {
		if (klass == null) {
			throw new RepositoryException(ErrorEnum.NULL_CLASS);
		}

		this.builder.append("select o");
		this.builder.append(" ");
		this.builder.append("from");
		this.builder.append(" ");
		this.builder.append(klass.getName());
		this.builder.append(" ");
		this.builder.append("o");
		this.builder.append(" ");
		this.builder.append("where");
		this.builder.append(" ");

		propositions.add(new Equals(FieldEnum.ACTIVE.getValue(), Boolean.TRUE));
		for (int i = 0; i < propositions.size(); i++) {
			if (i != 0) {
				this.builder.append(" and ");
			}

			Proposition proposition = propositions.get(i);
			this.validator.validate(proposition);
			proposition.fillQLString(this.builder);
		}

		TypedQuery<T> query = this.manager.createQuery(this.getQLString(), klass);
		for (Proposition proposition : propositions) {
			proposition.setParameters(query);
		}

		return query.getResultList();
	}

	public String getQLString() {
		return this.builder.toString();
	}

}
