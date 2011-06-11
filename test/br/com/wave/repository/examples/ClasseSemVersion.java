package br.com.wave.repository.examples;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClasseSemVersion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;

	public Long getId() {
		return id;
	}

}
