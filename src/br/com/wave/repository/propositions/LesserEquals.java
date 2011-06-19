package br.com.wave.repository.propositions;

import br.com.wave.repository.enums.OperatorEnum;

public class LesserEquals extends Proposition{

	public LesserEquals(String fieldName, Object value) {
		super(fieldName, value);
	}

	@Override
	public String getOperator() {
		return OperatorEnum.LESSER_EQUALS.getValue();
	}

}
