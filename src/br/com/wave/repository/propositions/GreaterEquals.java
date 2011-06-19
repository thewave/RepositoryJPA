package br.com.wave.repository.propositions;

import br.com.wave.repository.enums.OperatorEnum;

public class GreaterEquals extends Proposition{

	public GreaterEquals(String fieldName, Object value) {
		super(fieldName, value);
	}

	@Override
	public String getOperator() {
		return OperatorEnum.GREATER_EQUALS.getValue();
	}

}
