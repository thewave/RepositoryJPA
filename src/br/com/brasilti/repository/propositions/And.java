package br.com.brasilti.repository.propositions;

import br.com.brasilti.repository.enums.VerbEnum;

public class And extends CompoundProposition {

	public And(Proposition subject, Proposition... predicative) {
		super(subject, predicative);
	}

	@Override
	public VerbEnum getVerb() {
		return VerbEnum.AND;
	}

}
