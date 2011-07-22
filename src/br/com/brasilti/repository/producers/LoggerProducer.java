package br.com.brasilti.repository.producers;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.apache.log4j.Logger;

public class LoggerProducer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private InjectionPoint point;

	@Produces
	public Logger getLogger() {
		return Logger.getLogger(this.point.getMember().getDeclaringClass());
	}

}
