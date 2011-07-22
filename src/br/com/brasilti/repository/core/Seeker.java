package br.com.brasilti.repository.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.brasilti.repository.enums.ErrorEnum;
import br.com.brasilti.repository.enums.FieldEnum;
import br.com.brasilti.repository.enums.QLEnum;
import br.com.brasilti.repository.enums.VerbEnum;
import br.com.brasilti.repository.exceptions.RepositoryException;
import br.com.brasilti.repository.propositions.Equals;
import br.com.brasilti.repository.propositions.Proposition;
import br.com.brasilti.repository.validators.ClassValidator;
import br.com.brasilti.repository.validators.PropositionValidator;

public class Seeker {

	private StringBuilder builder;

	@Inject
	private EntityManager manager;

	@Inject
	private ClassValidator classValidator;

	@Inject
	private PropositionValidator propositionValidator;

	public Seeker() {
		this.builder = new StringBuilder();
	}

	public <T> List<T> seekAll(Class<T> klass, Proposition... propositions) throws RepositoryException {
		return this.seekAll(klass, new ArrayList<Proposition>(Arrays.asList(propositions)));
	}

	public <T> List<T> seekAll(Class<T> klass, List<Proposition> propositions) throws RepositoryException {
		this.validate(klass, propositions);

		this.builder.append(QLEnum.SELECT.getValue());
		this.fillQLString(klass, propositions);

		try {
			TypedQuery<T> query = this.manager.createQuery(this.getQLString(), klass);
			this.setParameters(propositions, query);

			return query.getResultList();
		} catch (Exception e) {
			throw new RepositoryException(ErrorEnum.UNEXPECTED_EXCEPTION.getMessage(e.getMessage()));
		}
	}

	public <T> T seekOne(Class<T> klass, Proposition... propositions) throws RepositoryException {
		return this.seekOne(klass, new ArrayList<Proposition>(Arrays.asList(propositions)));
	}

	public <T> T seekOne(Class<T> klass, List<Proposition> propositions) throws RepositoryException {
		this.validate(klass, propositions);

		this.builder.append(QLEnum.SELECT.getValue());
		this.fillQLString(klass, propositions);

		try {
			TypedQuery<T> query = this.manager.createQuery(this.getQLString(), klass);
			this.setParameters(propositions, query);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new RepositoryException(ErrorEnum.MORE_THAN_ONE_INSTANCE);
		} catch (Exception e) {
			throw new RepositoryException(ErrorEnum.UNEXPECTED_EXCEPTION.getMessage(e.getMessage()));
		}
	}

	public <T> Long count(Class<T> klass, Proposition... propositions) throws RepositoryException {
		return this.count(klass, new ArrayList<Proposition>(Arrays.asList(propositions)));
	}

	public <T> Long count(Class<T> klass, List<Proposition> propositions) throws RepositoryException {
		this.validate(klass, propositions);

		this.builder.append(QLEnum.COUNT.getValue());
		this.fillQLString(klass, propositions);

		try {
			TypedQuery<Long> query = this.manager.createQuery(this.getQLString(), Long.class);
			this.setParameters(propositions, query);

			return query.getSingleResult();
		} catch (Exception e) {
			throw new RepositoryException(ErrorEnum.UNEXPECTED_EXCEPTION.getMessage(e.getMessage()));
		}
	}

	private <T> void validate(Class<T> klass, List<Proposition> propositions) throws RepositoryException {
		if (klass == null) {
			throw new RepositoryException(ErrorEnum.NULL_CLASS);
		}

		this.classValidator.validate(klass);

		propositions.add(new Equals(FieldEnum.ACTIVE.getValue(), Boolean.TRUE));
		for (Proposition proposition : propositions) {
			this.propositionValidator.validate(proposition);
		}

		Collections.sort(propositions);
	}

	private <T> void fillQLString(Class<T> klass, List<Proposition> propositions) throws RepositoryException {
		this.builder.append(QLEnum.FROM.getValue());
		this.builder.append(klass.getName());
		this.builder.append(QLEnum.WHERE.getValue());

		for (int i = 0; i < propositions.size(); i++) {
			Proposition proposition = propositions.get(i);

			if (i != 0 && !proposition.getVerb().equals(VerbEnum.ORDER_BY)) {
				this.builder.append(QLEnum.AND.getValue());
			}

			proposition.fillQLString(this.builder);
		}
	}

	private <T> void setParameters(List<Proposition> propositions, Query query) {
		for (Proposition proposition : propositions) {
			proposition.setParameters(query);
		}
	}

	public String getQLString() {
		return this.builder.toString();
	}

}
