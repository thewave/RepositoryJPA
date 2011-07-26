package br.com.brasilti.repository.validators;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import br.com.brasilti.repository.enums.ErrorEnum;
import br.com.brasilti.repository.enums.FieldEnum;
import br.com.brasilti.repository.enums.MethodEnum;
import br.com.brasilti.repository.exceptions.RepositoryException;
import br.com.brasilti.utils.reflection.ReflectionUtil;

/**
 * Define as regras que garantem a manutencao dos dados de uma instancia.
 * 
 * @author Benedito Barbosa
 * @author Christian Peixoto
 * 
 */
public class ClassValidator {

	/**
	 * Verifica se uma determinada classe obedece as regras que definem uma entidade.
	 * 
	 * @param klass
	 * @throws RepositoryException
	 */
	public void validate(Class<?> klass) throws RepositoryException {
		if (!ReflectionUtil.isAnnotated(klass, Entity.class)) {
			throw new RepositoryException(ErrorEnum.ANNOTATION_ENTITY_NOT_FOUND);
		}

		if (!ReflectionUtil.implementz(klass, Serializable.class)) {
			throw new RepositoryException(ErrorEnum.INTERFACE_NOT_FOUND);
		}

		this.validateIdentifier(klass);

		this.validateVersion(klass);

		this.validateActive(klass);

		if (!ReflectionUtil.hasConstructor(klass)) {
			throw new RepositoryException(ErrorEnum.CONSTRUCTOR_NOT_FOUND);
		}

		if (!ReflectionUtil.hasMethod(klass, MethodEnum.EQUALS.getValue())) {
			throw new RepositoryException(ErrorEnum.METHOD_NOT_FOUND, MethodEnum.EQUALS.getValue());
		}

		if (!ReflectionUtil.hasMethod(klass, MethodEnum.HASHCODE.getValue())) {
			throw new RepositoryException(ErrorEnum.METHOD_NOT_FOUND, MethodEnum.HASHCODE.getValue());
		}
	}

	/**
	 * Indica que uma determinada classe e entidade.
	 * 
	 * @param klass
	 * @return true se a classe e entidade.
	 */
	public boolean isEntity(Class<?> klass) {
		try {
			this.validate(klass);
		} catch (RepositoryException e) {
			return false;
		}

		return true;
	}

	private void validateIdentifier(Class<?> klass) throws RepositoryException {
		if (!ReflectionUtil.hasField(klass, FieldEnum.ID.getValue())) {
			throw new RepositoryException(ErrorEnum.ID_NOT_FOUND);
		}

		Field field = ReflectionUtil.getField(FieldEnum.ID.getValue(), klass);
		if (!field.getType().equals(Long.class)) {
			throw new RepositoryException(ErrorEnum.ID_NOT_LONG);
		}

		if (!ReflectionUtil.isAnnotated(field, Id.class)) {
			throw new RepositoryException(ErrorEnum.ANNOTATION_ID_NOT_FOUND);
		}
	}

	private void validateVersion(Class<?> klass) throws RepositoryException {
		if (!ReflectionUtil.hasField(klass, FieldEnum.VERSION.getValue())) {
			throw new RepositoryException(ErrorEnum.VERSION_NOT_FOUND);
		}

		Field field = ReflectionUtil.getField(FieldEnum.VERSION.getValue(), klass);
		if (!field.getType().equals(Integer.class)) {
			throw new RepositoryException(ErrorEnum.VERSION_NOT_INTEGER);
		}

		if (!ReflectionUtil.isAnnotated(field, Version.class)) {
			throw new RepositoryException(ErrorEnum.ANNOTATION_VERSION_NOT_FOUND);
		}
	}

	private void validateActive(Class<?> klass) throws RepositoryException {
		if (!ReflectionUtil.hasField(klass, FieldEnum.ACTIVE.getValue())) {
			throw new RepositoryException(ErrorEnum.ACTIVE_NOT_FOUND);
		}

		Field field = ReflectionUtil.getField(FieldEnum.ACTIVE.getValue(), klass);
		if (!field.getType().equals(Boolean.class)) {
			throw new RepositoryException(ErrorEnum.ACTIVE_NOT_BOOLEAN);
		}
	}

}
