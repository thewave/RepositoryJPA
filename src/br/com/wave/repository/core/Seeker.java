package br.com.wave.repository.core;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wave.repository.propositions.Proposition;

public class Seeker {

	private Class<?> klass;

	private List<Proposition> propositions;

	private StringBuilder builder;

	@Inject
	private EntityManager manager;

	public Seeker() {
		this.propositions = new ArrayList<Proposition>();
		this.builder = new StringBuilder();
	}

	public Seeker giveme(Class<?> klass) {
		this.klass = klass;

		this.builder.append("select o from ");
		this.builder.append(this.klass.getName());
		this.builder.append(" o ");

		return this;
	}

	public Seeker whose(Proposition proposition) {
		if (this.propositions.isEmpty()) {
			this.builder.append(" where ");
		} else {
			this.builder.append(" and ");
		}

		proposition.setIndex(this.propositions.size());
		proposition.setConditionalTerm(this.builder);

		// TODO Pensar numa maneira de retirar o indice.
		this.propositions.add(proposition);

		return this;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> go() {
		System.out.println(this.getJPQL());

		TypedQuery<?> query = this.manager.createQuery(this.getJPQL(), this.klass);
		for (Proposition proposition : this.propositions) {
			proposition.setParameters(query);
		}

		return (List<T>) query.getResultList();
	}

	private String getJPQL() {
		return this.builder.toString();
	}

}
