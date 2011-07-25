package br.com.brasilti.repository.propositions;

import java.util.List;

import br.com.brasilti.repository.enums.VerbEnum;
import br.com.brasilti.utils.collection.CollectionUtil;

public class NotIn extends SimpleProposition {

	public NotIn(String fieldName, Object... values) {
		this(fieldName, CollectionUtil.convert(values));
	}

	public NotIn(String fieldName, List<?> list) {
		super(fieldName, list);
	}

	@Override
	public VerbEnum getVerb() {
		return VerbEnum.NOT_IN;
	}

	@Override
	public void fillQLString(StringBuilder builder) {
		builder.append("o.");
		builder.append(this.getSubject());
		builder.append(this.getVerb().getValue());
		builder.append("(");
		builder.append(":");
		builder.append(this.getSubject());
		builder.append(String.valueOf(this.hashCode()));
		builder.append(")");
	}

}
