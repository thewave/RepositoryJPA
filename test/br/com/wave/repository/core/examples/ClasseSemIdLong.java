package br.com.wave.repository.core.examples;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class ClasseSemIdLong implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	public String getId() {
		return id;
	}

}
