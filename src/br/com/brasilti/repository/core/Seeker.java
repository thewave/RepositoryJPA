package br.com.brasilti.repository.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
import br.com.brasilti.repository.propositions.In;
import br.com.brasilti.repository.propositions.Like;
import br.com.brasilti.repository.propositions.Proposition;
import br.com.brasilti.repository.validators.ClassValidator;
import br.com.brasilti.repository.validators.PropositionValidator;
import br.com.brasilti.utils.collection.CollectionUtil;
import br.com.brasilti.utils.reflection.ReflectionUtil;

public class Seeker {

	@Inject
	private EntityManager manager;

	@Inject
	private ClassValidator classValidator;

	@Inject
	private PropositionValidator propositionValidator;

	public <T> List<T> seekAll(Class<T> klass, Proposition... propositions) throws RepositoryException {
		return this.seekAll(klass, CollectionUtil.convert(propositions));
	}

	public <T> T seekOne(Class<T> klass, Proposition... propositions) throws RepositoryException {
		return this.seekOne(klass, CollectionUtil.convert(propositions));
	}

	public <T> Long count(Class<T> klass, Proposition... propositions) throws RepositoryException {
		return this.count(klass, CollectionUtil.convert(propositions));
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> seekByExample(T instance) throws RepositoryException {
		if (instance == null) {
			throw new RepositoryException(ErrorEnum.NULL_INSTANCE);
		}

		List<Proposition> propositions = new ArrayList<Proposition>();

		Class<T> klass = (Class<T>) instance.getClass();
		List<Field> fields = ReflectionUtil.getPersistentFields(klass);
		for (Field field : fields) {
			String fieldName = field.getName();
			Object value = ReflectionUtil.get(field, instance);

			if (!FieldEnum.contains(fieldName) && value != null) {
				if (field.getType().equals(String.class)) {
					propositions.add(new Like(fieldName, value));
					continue;
				}

				if (this.classValidator.isEntity(field.getType())) {
					propositions.add(new In(fieldName, this.seekByExample(value)));
					continue;
				}

				propositions.add(new Equals(fieldName, value));
			}
		}

		try {
			return this.seekAll(klass, propositions);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<T>();
		}

	}

	public <T> List<T> seekAll(Class<T> klass, List<Proposition> propositions) throws RepositoryException {
		this.validate(klass, propositions);

		StringBuilder builder = new StringBuilder();
		builder.append(QLEnum.SELECT.getValue());
		this.fillQLString(klass, propositions, builder);

		try {
			TypedQuery<T> query = this.manager.createQuery(builder.toString(), klass);
			this.setParameters(propositions, query);

			return query.getResultList();
		} catch (Exception e) {
			throw new RepositoryException(ErrorEnum.UNEXPECTED_EXCEPTION.getMessage(e.getMessage()));
		}
	}

	public <T> T seekOne(Class<T> klass, List<Proposition> propositions) throws RepositoryException {
		this.validate(klass, propositions);

		StringBuilder builder = new StringBuilder();
		builder.append(QLEnum.SELECT.getValue());
		this.fillQLString(klass, propositions, builder);

		try {
			TypedQuery<T> query = this.manager.createQuery(builder.toString(), klass);
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

	public <T> Long count(Class<T> klass, List<Proposition> propositions) throws RepositoryException {
		this.validate(klass, propositions);

		StringBuilder builder = new StringBuilder();
		builder.append(QLEnum.COUNT.getValue());
		this.fillQLString(klass, propositions, builder);

		try {
			TypedQuery<Long> query = this.manager.createQuery(builder.toString(), Long.class);
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

	private <T> void fillQLString(Class<T> klass, List<Proposition> propositions, StringBuilder builder) throws RepositoryException {
		builder.append(QLEnum.FROM.getValue());
		builder.append(klass.getName());
		builder.append(QLEnum.WHERE.getValue());

		for (int i = 0; i < propositions.size(); i++) {
			Proposition proposition = propositions.get(i);

			if (i != 0 && !proposition.getVerb().equals(VerbEnum.ORDER_BY)) {
				builder.append(QLEnum.AND.getValue());
			}

			proposition.fillQLString(builder);
		}
	}

	private <T> void setParameters(List<Proposition> propositions, Query query) {
		for (Proposition proposition : propositions) {
			proposition.setParameters(query);
		}
	}

}
