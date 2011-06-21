package br.com.wave.repository.propositions;

import javax.persistence.Query;

import br.com.wave.repository.enums.OperatorEnum;

public class Between extends Proposition{
	
	public Between(String fieldName, Object value1, Object value2) {
		super(fieldName, value1, value2);
	}	
	
	@Override
	public String getOperator() {
		return OperatorEnum.BETWEEN.getValue();
	}

	@Override
	public String getProposition() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(" o.");
		builder.append(this.getFieldName());
		builder.append(" ");
		builder.append(this.getOperator());
		builder.append(" :minimo");
		builder.append(String.valueOf(this.getIndex()));
		builder.append(" and ");
		builder.append(" :maximo");
		builder.append(String.valueOf(this.getIndex()));
		
		return builder.toString();
	}

	@Override
	public void setParameters(Query query) {
		
		query.setParameter("minimo"+String.valueOf(this.getIndex()), this.getValues()[0]);
		query.setParameter("maximo"+String.valueOf(this.getIndex()), this.getValues()[1]);
	}

}
