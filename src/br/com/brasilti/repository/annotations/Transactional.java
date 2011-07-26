package br.com.brasilti.repository.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * Define a classe ou metodo interceptado durante acesso ao repositorio.
 * 
 * @author Benedito Barbosa
 * @author Christian Peixoto
 * 
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Transactional {

}
