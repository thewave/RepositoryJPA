package br.com.brasilti.repository.propositions;

import javax.persistence.Query;

public abstract class SimpleProposition implements Proposition {

	private String fieldName;

	private Object[] values;

	public SimpleProposition(String fieldName, Object... values) {
		this.fieldName = fieldName;
		this.values = values;
	}

	@Override
	public Object getSubject() {
		return fieldName;
	}

	@Override
	public Object[] getPredicative() {
		return values;
	}

	// TODO Rever o index.
	@Override
	public void fillQLString(StringBuilder builder) {
		builder.append("o.");
		builder.append(this.fieldName);
		builder.append(this.getVerb().getValue());
		builder.append(":");
		builder.append(this.fieldName);
		builder.append(String.valueOf(this.hashCode()));
	}

	@Override
	public void setParameters(Query query) {
		query.setParameter(this.fieldName + String.valueOf(this.hashCode()), this.values[0]);
	}

}
