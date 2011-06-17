package br.com.wave.repository.core.examples;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class ClasseSemActive implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@Version
	private Integer version;

	public Long getId() {
		return id;
	}
	
	public Integer getVersion() {
		return version;
	}

}
