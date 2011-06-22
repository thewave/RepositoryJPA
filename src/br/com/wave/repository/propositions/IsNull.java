package br.com.wave.repository.propositions;

import javax.persistence.Query;

import br.com.wave.repository.enums.OperatorEnum;

public class IsNull extends Proposition {

	private IsNull(String fieldName, Object value) {
		super(fieldName, value);
	}

	public IsNull(String fieldName) {
		this(fieldName, null);
	}

	@Override
	public String getOperator() {
		return OperatorEnum.IS_NULL.getValue();
	}

	@Override
	public void setConditionalTerm(StringBuilder builder) {
		builder.append(" o.");
		builder.append(this.getFieldName());
		builder.append(" ");
		builder.append(this.getOperator());
	}

	@Override
	public void setParameters(Query query) {
	}

}
