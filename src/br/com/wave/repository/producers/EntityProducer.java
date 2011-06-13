package br.com.wave.repository.producers;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import br.com.wave.repository.enums.InfoEnum;

public class EntityProducer {

	private static EntityManagerFactory factory;

	@Inject
	private Logger logger;

	static {
		factory = Persistence.createEntityManagerFactory("repositoryPU");
	}

	@Produces
	public EntityManager getEntityManager() {
		this.logger.info(InfoEnum.OPEN.getMessage());

		return factory.createEntityManager();
	}

	public void close(@Disposes EntityManager entityManager) {
		this.logger.info(InfoEnum.CLOSE.getMessage());

		entityManager.close();
	}

}
