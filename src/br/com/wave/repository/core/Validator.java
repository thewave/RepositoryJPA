package br.com.wave.repository.core;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import br.com.wave.repository.enums.ErrorEnum;
import br.com.wave.repository.enums.FieldEnum;
import br.com.wave.repository.exceptions.RepositoryException;
import br.com.wave.utils.reflection.ReflectionUtil;

public class Validator {

	public void validate(Class<?> klass) throws RepositoryException {
		if (!ReflectionUtil.isAnnotated(klass, Entity.class)) {
			throw new RepositoryException(ErrorEnum.ANNOTATION_ENTITY_NOT_FOUND.getMessage());
		}

		if (!ReflectionUtil.implementz(klass, Serializable.class)) {
			throw new RepositoryException(ErrorEnum.INTERFACE_NOT_FOUND.getMessage());
		}

		this.validateIdentifier(klass);

		this.validateVersion(klass);

		this.validateActive(klass);
		
		if (!ReflectionUtil.hasConstructor(klass)) {
			throw new RepositoryException(ErrorEnum.CONSTRUCTOR_NOT_FOUND.getMessage());
		}
	}

	private void validateIdentifier(Class<?> klass) throws RepositoryException {
		if (!ReflectionUtil.hasField(klass, FieldEnum.ID.getValue())) {
			throw new RepositoryException(ErrorEnum.ID_NOT_FOUND.getMessage());
		}

		Field field = ReflectionUtil.getField(FieldEnum.ID.getValue(), klass);
		if (!field.getType().equals(Long.class)) {
			throw new RepositoryException(ErrorEnum.ID_NOT_LONG.getMessage());
		}

		if (!ReflectionUtil.isAnnotated(field, Id.class)) {
			throw new RepositoryException(ErrorEnum.ANNOTATION_ID_NOT_FOUND.getMessage());
		}
	}

	private void validateVersion(Class<?> klass) throws RepositoryException {
		if (!ReflectionUtil.hasField(klass, FieldEnum.VERSION.getValue())) {
			throw new RepositoryException(ErrorEnum.VERSION_NOT_FOUND.getMessage());
		}

		Field field = ReflectionUtil.getField(FieldEnum.VERSION.getValue(), klass);
		if (!field.getType().equals(Integer.class)) {
			throw new RepositoryException(ErrorEnum.VERSION_NOT_INTEGER.getMessage());
		}

		if (!ReflectionUtil.isAnnotated(field, Version.class)) {
			throw new RepositoryException(ErrorEnum.ANNOTATION_VERSION_NOT_FOUND.getMessage());
		}
	}

	private void validateActive(Class<?> klass) throws RepositoryException {
		if (!ReflectionUtil.hasField(klass, FieldEnum.ACTIVE.getValue())) {
			throw new RepositoryException(ErrorEnum.ACTIVE_NOT_FOUND.getMessage());
		}

		Field field = ReflectionUtil.getField(FieldEnum.ACTIVE.getValue(), klass);
		if (!field.getType().equals(Boolean.class)) {
			throw new RepositoryException(ErrorEnum.ACTIVE_NOT_BOOLEAN.getMessage());
		}
	}

}
