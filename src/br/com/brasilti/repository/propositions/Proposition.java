package br.com.brasilti.repository.propositions;

import javax.persistence.Query;

import br.com.brasilti.repository.enums.VerbEnum;

public interface Proposition {

	Object getSubject();

	VerbEnum getVerb();

	Object[] getPredicative();

	void fillQLString(StringBuilder builder);

	void setParameters(Query query);

}
