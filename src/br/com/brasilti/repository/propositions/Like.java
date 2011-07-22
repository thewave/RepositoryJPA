package br.com.brasilti.repository.propositions;

import javax.persistence.Query;

import br.com.brasilti.repository.enums.LikeEnum;
import br.com.brasilti.repository.enums.VerbEnum;

public class Like extends SimpleProposition {

	private LikeEnum likeEnum;

	public Like(String fieldName, Object value) {
		super(fieldName, value);
		this.likeEnum = LikeEnum.ANY_WHERE;
	}

	public Like(String fieldName, Object value, LikeEnum likeEnum) {
		super(fieldName, value);
		this.likeEnum = likeEnum;
	}

	@Override
	public VerbEnum getVerb() {
		return VerbEnum.LIKE;
	}

	@Override
	public void fillQLString(StringBuilder builder) {
		builder.append("lower(");
		builder.append("o.");
		builder.append(this.getSubject());
		builder.append(")");
		builder.append(this.getVerb().getValue());
		builder.append("lower(");
		builder.append(":");
		builder.append(this.getSubject());
		builder.append(String.valueOf(this.hashCode()));
		builder.append(")");
	}

	@Override
	public void setParameters(Query query) {
		this.likeEnum.setParameters(this, query);
	}

}
