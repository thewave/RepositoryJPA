package br.com.wave.repository.core.examples;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class ClasseSemAtId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	public Long getId() {
		return id;
	}

}
