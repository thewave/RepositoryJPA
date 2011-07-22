package br.com.brasilti.repository.propositions;

import br.com.brasilti.repository.enums.VerbEnum;

public class Or extends CompoundProposition {

	public Or(Proposition subject, Proposition... predicative) {
		super(subject, predicative);
	}

	@Override
	public VerbEnum getVerb() {
		return VerbEnum.OR;
	}

}
