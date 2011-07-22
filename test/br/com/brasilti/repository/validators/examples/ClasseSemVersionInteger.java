package br.com.brasilti.repository.validators.examples;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClasseSemVersionInteger implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Long version;

	public Long getId() {
		return id;
	}

	public Long getVersion() {
		return version;
	}

}
