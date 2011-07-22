package br.com.brasilti.repository.propositions;

import javax.persistence.Query;

import br.com.brasilti.repository.enums.VerbEnum;

public class IsNull extends SimpleProposition {

	private IsNull(String fieldName, Object value) {
		super(fieldName, value);
	}

	public IsNull(String fieldName) {
		this(fieldName, null);
	}

	@Override
	public VerbEnum getVerb() {
		return VerbEnum.IS_NULL;
	}

	@Override
	public void fillQLString(StringBuilder builder) {
		builder.append("o.");
		builder.append(this.getSubject());
		builder.append(this.getVerb().getValue());
	}

	@Override
	public void setParameters(Query query) {

	}

}
