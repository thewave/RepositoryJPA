package br.com.brasilti.repository.core;

import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import br.com.brasilti.repository.enums.ErrorEnum;
import br.com.brasilti.repository.enums.FieldEnum;
import br.com.brasilti.repository.enums.InfoEnum;
import br.com.brasilti.repository.enums.RemoveEnum;
import br.com.brasilti.repository.exceptions.RepositoryException;
import br.com.brasilti.repository.validators.ClassValidator;
import br.com.brasilti.utils.reflection.ReflectionUtil;

public class Keeper {

	@Inject
	private ClassValidator validator;

	@Inject
	private Logger logger;

	@Inject
	private EntityManager manager;

	public <T> void persist(T instance) throws RepositoryException {
		if (instance == null) {
			throw new RepositoryException(ErrorEnum.NULL_INSTANCE);
		}

		this.validator.validate(instance.getClass());

		this.logger.info(InfoEnum.PERSIST.getMessage());

		Field field = ReflectionUtil.getField(FieldEnum.ID.getValue(), instance.getClass());
		if (ReflectionUtil.get(field, instance) == null) {
			this.manager.persist(instance);
		} else {
			this.manager.merge(instance);
		}
	}

	public <T> void remove(T instance) throws RepositoryException {
		this.remove(instance, RemoveEnum.LOGICAL);
	}

	public <T> void remove(T instance, RemoveEnum type) throws RepositoryException {
		if (instance == null) {
			throw new RepositoryException(ErrorEnum.NULL_INSTANCE);
		}

		this.validator.validate(instance.getClass());

		this.logger.info(InfoEnum.REMOVE.getMessage());

		type.remove(instance, this.manager);
	}

}
