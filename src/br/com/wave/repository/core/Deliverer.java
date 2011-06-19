package br.com.wave.repository.core;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wave.repository.propositions.Proposition;

public class Deliverer {

	private Class<?> klass;
	
	private List<Proposition> propositions;
	
	@Inject
	private EntityManager manager;

	public Deliverer() {
		this.propositions = new ArrayList<Proposition>();
	}
	
	public Deliverer select(Class<?> klass) {
		this.klass = klass;
		return this;
	}

	public Deliverer whose(Proposition proposition) {
		propositions.add(proposition);
		return this;		
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> fetchAll() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("select o from ");
		builder.append(this.klass.getName());
		builder.append(" o ");
		
		if(!this.propositions.isEmpty()) {
			builder.append(" where ");			
		}
		
		for (int i = 0; i < this.propositions.size(); i++) {
			if(i > 0) {
				builder.append(" and ");
			}		
			Proposition proposition = this.propositions.get(i);
			builder.append(proposition.getProposition());
		}

		TypedQuery<T> query = (TypedQuery<T>) this.manager.createQuery(builder.toString(),this.klass);
		for (Proposition proposition : this.propositions) {
			query.setParameter(proposition.getFieldName(), proposition.getValue());
		}
		
		return query.getResultList();
	}

}
