package br.com.brasilti.repository.propositions;

import br.com.brasilti.repository.enums.VerbEnum;

public class LesserEquals extends SimpleProposition {

	public LesserEquals(String fieldName, Object value) {
		super(fieldName, value);
	}

	@Override
	public VerbEnum getVerb() {
		return VerbEnum.LESSER_EQUALS;
	}

}
