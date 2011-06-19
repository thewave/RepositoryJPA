package br.com.wave.repository.propositions;

import br.com.wave.repository.enums.OperatorEnum;

public class LesserThan extends Proposition{

	public LesserThan(String fieldName, Object value) {
		super(fieldName, value);
	}

	@Override
	public String getOperator() {
		return OperatorEnum.LESSER_THAN.getValue();
	}

}
