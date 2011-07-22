package br.com.brasilti.repository.enums;

import javax.persistence.Query;

import br.com.brasilti.repository.propositions.Proposition;

public enum LikeEnum {

	START {
		@Override
		public void setParameters(Proposition proposition, Query query) {
			query.setParameter(proposition.getSubject() + String.valueOf(proposition.hashCode()), proposition.getPredicative()[0] + "%");
		}
	},
	ANY_WHERE {
		@Override
		public void setParameters(Proposition proposition, Query query) {
			query.setParameter(proposition.getSubject() + String.valueOf(proposition.hashCode()), "%" + proposition.getPredicative()[0] + "%");
		}
	},
	END {
		@Override
		public void setParameters(Proposition proposition, Query query) {
			query.setParameter(proposition.getSubject() + String.valueOf(proposition.hashCode()), "%" + proposition.getPredicative()[0]);
		}
	};

	public abstract void setParameters(Proposition proposition, Query query);

}
