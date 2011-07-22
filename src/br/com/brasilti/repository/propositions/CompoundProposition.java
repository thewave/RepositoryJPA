package br.com.brasilti.repository.propositions;

import javax.persistence.Query;

public abstract class CompoundProposition extends Proposition {

	private Proposition subject;

	private Proposition[] predicative;

	public CompoundProposition(Proposition subject, Proposition... predicative) {
		this.subject = subject;
		this.predicative = predicative;
	}

	@Override
	public Object getSubject() {
		return subject;
	}

	@Override
	public Object[] getPredicative() {
		return predicative;
	}

	@Override
	public void fillQLString(StringBuilder builder) {
		if (this.subject != null) {
			builder.append("(");
			this.subject.fillQLString(builder);
		}

		for (Proposition proposition : this.predicative) {
			builder.append(this.getVerb().getValue());

			if (this.subject == null) {
				builder.append("(");
			}

			proposition.fillQLString(builder);
		}

		builder.append(")");
	}

	@Override
	public void setParameters(Query query) {
		if (this.subject != null) {
			this.subject.setParameters(query);
		}

		for (Proposition proposition : this.predicative) {
			proposition.setParameters(query);
		}
	}

}
