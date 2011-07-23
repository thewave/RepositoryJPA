package br.com.brasilti.repository.validators.examples;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class ClasseEntidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Version
	private Integer version;

	private Boolean active;

	public ClasseEntidade() {

	}

	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public Boolean getActive() {
		return active;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
