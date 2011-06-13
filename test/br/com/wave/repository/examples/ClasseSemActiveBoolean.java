package br.com.wave.repository.examples;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class ClasseSemActiveBoolean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@Version
	private Integer version;
	
	private String active;

	public Long getId() {
		return id;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public String getActive() {
		return active;
	}

}
