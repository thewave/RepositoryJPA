package br.com.wave.repository.core;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import br.com.wave.repository.enums.InfoEnum;
import br.com.wave.repository.exceptions.RepositoryException;

public class Repository {

	@Inject
	private Validator validator;

	@Inject
	private Logger logger;

	@Inject
	private EntityManager manager;

	public <T> void persist(T instance) throws RepositoryException {
		this.validator.validate(instance.getClass());

		this.logger.info(InfoEnum.PERSIST.getMessage());

		this.manager.persist(instance);
	}

	public <T> void remove(T instance) throws RepositoryException {
		this.validator.validate(instance.getClass());

		this.logger.info(InfoEnum.REMOVE.getMessage());

		this.manager.remove(instance);
	}

}
