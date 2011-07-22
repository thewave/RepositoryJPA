package br.com.brasilti.repository.propositions;

import br.com.brasilti.repository.enums.VerbEnum;

public class Equals extends SimpleProposition {

	public Equals(String fieldName, Object value) {
		super(fieldName, value);
	}

	@Override
	public VerbEnum getVerb() {
		return VerbEnum.EQUALS;
	}

}
