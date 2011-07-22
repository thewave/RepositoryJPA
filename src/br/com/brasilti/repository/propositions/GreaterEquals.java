package br.com.brasilti.repository.propositions;

import br.com.brasilti.repository.enums.VerbEnum;

public class GreaterEquals extends SimpleProposition {

	public GreaterEquals(String fieldName, Object value) {
		super(fieldName, value);
	}

	@Override
	public VerbEnum getVerb() {
		return VerbEnum.GREATER_EQUALS;
	}

}
