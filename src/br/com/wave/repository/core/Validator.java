package br.com.wave.repository.core;

import java.io.Serializable;

import org.hibernate.annotations.Entity;

import br.com.wave.repository.enums.ErrorEnum;
import br.com.wave.repository.enums.FieldEnum;
import br.com.wave.repository.exceptions.RepositoryException;
import br.com.wave.repository.utils.ReflectionUtil;

public class Validator {

	public void validate(Class<?> klass) throws RepositoryException {
		if (!ReflectionUtil.isAnnotated(klass, Entity.class)) {
			throw new RepositoryException(ErrorEnum.ANOTACAO_INEXISTENTE.getMessage());
		}
		
		if (!ReflectionUtil.implementz(klass, Serializable.class)) {
			throw new RepositoryException(ErrorEnum.INTERFACE_INEXISTENTE.getMessage());
		}
		
		if (!ReflectionUtil.hasField(klass, FieldEnum.ID)) {
			throw new RepositoryException(ErrorEnum.ID_INEXISTENTE.getMessage());
		}
	}

}
