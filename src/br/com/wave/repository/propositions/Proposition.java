package br.com.wave.repository.propositions;

import javax.persistence.Query;

public abstract class Proposition {

	private String fieldName;
	
	private Object[] values;
	
	private Integer index;

	public Proposition(String fieldName, Object... values) {
		this.fieldName = fieldName;
		this.values = values;
	}

	public abstract String getOperator();

	public void setParameters(Query query) {
		query.setParameter(this.fieldName + String.valueOf(this.index), this.values[0]);
	}

	public String getProposition() {
		StringBuilder builder = new StringBuilder();

		builder.append(" o.");
		builder.append(this.fieldName);
		builder.append(" ");
		builder.append(this.getOperator());
		builder.append(" :");
		builder.append(this.fieldName);
		builder.append(String.valueOf(this.index));

		return builder.toString();
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object[] getValues() {
		return values;
	}
	
	public Integer getIndex() {
		return index;
	}
	
	public void setIndex(Integer index) {
		this.index = index;
	}
	
}
