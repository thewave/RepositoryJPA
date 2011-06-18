package br.com.wave.repository.enums;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;

import br.com.wave.utils.reflection.ReflectionUtil;

public enum RemoveEnum {

	LOGICAL {
		@Override
		public <T> void remove(T instance, EntityManager manager) {
			Field activeField = ReflectionUtil.getField(FieldEnum.ACTIVE.getValue(), instance.getClass());
			ReflectionUtil.set(Boolean.FALSE, activeField, instance);

			Field idField = ReflectionUtil.getField(FieldEnum.ID.getValue(), instance.getClass());
			if (ReflectionUtil.get(idField, instance) == null) {
				manager.persist(instance);
			} else {
				manager.merge(instance);
			}

		}
	},

	PHYSICAL {
		@Override
		public <T> void remove(T instance, EntityManager manager) {
			if (manager.contains(instance)) {
				manager.remove(instance);
			} else {
				manager.remove(manager.merge(instance));
			}
		}
	};

	public abstract <T> void remove(T instance, EntityManager manager);

}
