package br.com.wave.repository.propositions;

import br.com.wave.repository.enums.OperatorEnum;

public class NotEquals extends Proposition{

	public NotEquals(String fieldName, Object value) {
		super(fieldName, value);
	}

	@Override
	public String getOperator() {
		return OperatorEnum.NOT_EQUALS.getValue();
	}

}
