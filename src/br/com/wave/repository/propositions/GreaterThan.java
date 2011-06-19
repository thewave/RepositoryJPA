package br.com.wave.repository.propositions;

import br.com.wave.repository.enums.OperatorEnum;

public class GreaterThan extends Proposition{

	public GreaterThan(String fieldName, Object value) {
		super(fieldName, value);
	}

	@Override
	public String getOperator() {
		return OperatorEnum.GREATER_THAN.getValue();
	}

}
