package br.com.wave.repository.propositions;

import javax.persistence.Query;

import br.com.wave.repository.enums.OperatorEnum;

public class EndLike extends Proposition {

	public EndLike(String fieldName, Object value) {
		super(fieldName, value);
	}

	@Override
	public String getOperator() {
		return OperatorEnum.LIKE.getValue();
	}

	public void setConditionalTerm(StringBuilder builder) {
		builder.append(" lower(");
		builder.append("o.");
		builder.append(this.getFieldName());
		builder.append(") ");
		builder.append(this.getOperator());
		builder.append(" lower(:");
		builder.append(this.getFieldName());
		builder.append(String.valueOf(this.getIndex()));
		builder.append(")");
	}

	@Override
	public void setParameters(Query query) {
		query.setParameter(this.getFieldName() + String.valueOf(this.getIndex()), "%" + this.getValues()[0]);
	}

}
