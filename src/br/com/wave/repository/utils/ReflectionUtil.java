package br.com.wave.repository.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import br.com.wave.repository.enums.FieldEnum;

public class ReflectionUtil {

	public static boolean isAnnotated(Class<?> klass, Class<? extends Annotation> annotationClass) {
		return klass.getAnnotation(annotationClass) != null;
	}

	public static boolean implementz(Class<?> klass, Class<?> interfaceClass) {
		Class<?>[] interfaces = klass.getInterfaces();
		for (Class<?> i : interfaces) {
			if (i.equals(interfaceClass)) {
				return true;
			}
		}
		
		return false;
	}

	public static boolean hasField(Class<?> klass, FieldEnum fieldEnum) {
		Field[] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals(fieldEnum.getValue())) {
				return true;
			}
		}
		
		return false;
	}

}
