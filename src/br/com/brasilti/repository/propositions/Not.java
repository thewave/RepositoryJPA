package br.com.brasilti.repository.propositions;

import br.com.brasilti.repository.enums.VerbEnum;

public class Not extends CompoundProposition {

	public Not(Proposition predicative) {
		super(null, predicative);
	}

	@Override
	public VerbEnum getVerb() {
		return VerbEnum.NOT;
	}

}
