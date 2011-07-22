package br.com.brasilti.repository.propositions;

import javax.persistence.Query;

import br.com.brasilti.repository.enums.VerbEnum;

public class Between extends SimpleProposition {

	public Between(String fieldName, Object value1, Object value2) {
		super(fieldName, value1, value2);
	}

	@Override
	public VerbEnum getVerb() {
		return VerbEnum.BETWEEN;
	}

	@Override
	public void fillQLString(StringBuilder builder) {
		builder.append("o.");
		builder.append(this.getSubject());
		builder.append(this.getVerb().getValue());
		builder.append(":min");
		builder.append(String.valueOf(this.hashCode()));
		builder.append(" and ");
		builder.append(":max");
		builder.append(String.valueOf(this.hashCode()));
	}

	@Override
	public void setParameters(Query query) {
		query.setParameter("min" + String.valueOf(this.hashCode()), this.getPredicative()[0]);
		query.setParameter("max" + String.valueOf(this.hashCode()), this.getPredicative()[1]);
	}

}
