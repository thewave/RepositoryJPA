package br.com.brasilti.repository.propositions;

import br.com.brasilti.repository.enums.VerbEnum;

public class NotEquals extends SimpleProposition {

	public NotEquals(String fieldName, Object value) {
		super(fieldName, value);
	}

	@Override
	public VerbEnum getVerb() {
		return VerbEnum.NOT_EQUALS;
	}

}
