package br.com.wave.repository.core;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import br.com.wave.repository.enums.FieldEnum;
import br.com.wave.repository.enums.InfoEnum;
import br.com.wave.repository.enums.RemoveEnum;
import br.com.wave.repository.exceptions.RepositoryException;
import br.com.wave.utils.reflection.ReflectionUtil;

public class Keeper implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Validator validator;

	@Inject
	private Logger logger;

	@Inject
	private EntityManager manager;

	public <T> void persist(T instance) throws RepositoryException {
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
		this.validator.validate(instance.getClass());

		this.logger.info(InfoEnum.REMOVE.getMessage());

		type.remove(instance, this.manager);
	}

}
