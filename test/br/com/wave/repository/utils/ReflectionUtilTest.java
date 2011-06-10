package br.com.wave.repository.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.hibernate.annotations.Entity;
import org.junit.Test;

import br.com.wave.repository.enums.FieldEnum;
import br.com.wave.repository.examples.ClasseComAtEntity;
import br.com.wave.repository.examples.ClasseComId;
import br.com.wave.repository.examples.ClasseNaoSerializavel;
import br.com.wave.repository.examples.ClasseSemAtEntity;
import br.com.wave.repository.examples.ClasseSerializavel;

public class ReflectionUtilTest {
	
	@Test
	public void deveRetornarFalsoQuandoUmaClasseNaoTiverUmaDeterminadaAnotacao() {
		assertFalse(ReflectionUtil.isAnnotated(ClasseSemAtEntity.class, Entity.class));
	}
	
	@Test
	public void deveRetornarVerdadeiroQuandoUmaClasseTiverUmaDeterminadaAnotacao() {
		assertTrue(ReflectionUtil.isAnnotated(ClasseComAtEntity.class, Entity.class));
	}
	
	@Test
	public void deveRetornarFalsoQuandoUmaClasseNaoImplementarUmaDeterminadaInterface() {
		assertFalse(ReflectionUtil.implementz(ClasseNaoSerializavel.class, Serializable.class));
	}
	
	@Test
	public void deveRetornarVerdadeiroQuandoUmaClasseImplementarUmaDeterminadaInterface() {
		assertTrue(ReflectionUtil.implementz(ClasseSerializavel.class, Serializable.class));
	}
	
	@Test
	public void deveRetornarFalsoQuandoUmaClasseNaoTiverUmDeterminadoAtributo() {
		assertFalse(ReflectionUtil.hasField(ClasseSerializavel.class, FieldEnum.ID));
	}
	
	@Test
	public void deveRetornarVerdadeiroQuandoUmaClasseTiverUmDeterminadoAtributo() {
		assertTrue(ReflectionUtil.hasField(ClasseComId.class, FieldEnum.ID));
	}

}
