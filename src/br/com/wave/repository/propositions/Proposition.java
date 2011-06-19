package br.com.wave.repository.propositions;

public abstract class Proposition{

	private String fieldName;
	private Object value;
	
	public Proposition(String fieldName, Object value) {
		this.fieldName = fieldName;
		this.value = value;
	}
	
	public abstract String getOperator();
	
	public String getProposition() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(" o.");
		builder.append(this.fieldName);
		builder.append(" ");
		builder.append(this.getOperator());
		builder.append(" :");
		builder.append(this.fieldName);
		
		return builder.toString();
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public Object getValue() {
		return value;
	}
}
