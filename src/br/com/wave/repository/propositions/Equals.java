package br.com.wave.repository.propositions;

import br.com.wave.repository.enums.OperatorEnum;

public class Equals extends Proposition{

	public Equals(String fieldName, Object value) {
		super(fieldName, value);
	}

	@Override
	public String getOperator() {
		return OperatorEnum.EQUALS.getValue();
	}

}
