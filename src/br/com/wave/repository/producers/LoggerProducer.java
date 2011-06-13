package br.com.wave.repository.producers;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.log4j.Logger;

public class LoggerProducer {

	private InjectionPoint point;

	@Produces
	public Logger getLogger() {
		return Logger.getLogger(this.point.getMember().getDeclaringClass());
	}

}
