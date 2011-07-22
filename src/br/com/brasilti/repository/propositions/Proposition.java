package br.com.brasilti.repository.propositions;

import javax.persistence.Query;

import br.com.brasilti.repository.enums.VerbEnum;

public abstract class Proposition implements Comparable<Proposition> {

	public abstract Object getSubject();

	public abstract VerbEnum getVerb();

	public abstract Object[] getPredicative();

	public abstract void fillQLString(StringBuilder builder);

	public abstract void setParameters(Query query);

	@Override
	public int compareTo(Proposition proposition) {
		if (this.getVerb().equals(VerbEnum.ORDER_BY) && !proposition.getVerb().equals(VerbEnum.ORDER_BY)) {
			return 1;
		}

		return 0;
	}

}
