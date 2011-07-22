package br.com.brasilti.repository.validators;

import br.com.brasilti.repository.enums.ErrorEnum;
import br.com.brasilti.repository.enums.VerbEnum;
import br.com.brasilti.repository.exceptions.RepositoryException;
import br.com.brasilti.repository.propositions.Proposition;

public class PropositionValidator {

	public void validate(Proposition proposition) throws RepositoryException {
		VerbEnum verbEnum = proposition.getVerb();
		if (verbEnum.requiresSubject() && proposition.getSubject() == null) {
			throw new RepositoryException(ErrorEnum.NULL_SUBJECT);
		}

		if (verbEnum.requiresPredicative() && proposition.getPredicative().length == 0) {
			throw new RepositoryException(ErrorEnum.EMPTY_PREDICATIVE);
		}

		if (verbEnum.requiresPredicative() && !hasNullPredicative(proposition)) {
			throw new RepositoryException(ErrorEnum.NULL_PREDICATIVE);
		}

		if (verbEnum.isConnective()) {
			this.validateInternalPropositions(proposition);
		}
	}

	private boolean hasNullPredicative(Proposition proposition) {
		for (Object predicative : proposition.getPredicative()) {
			if (predicative == null) {
				return false;
			}
		}

		return true;
	}

	private void validateInternalPropositions(Proposition proposition) throws RepositoryException {
		Proposition subject = (Proposition) proposition.getSubject();
		if (subject != null) {
			this.validate(subject);
		}

		Proposition[] predicative = (Proposition[]) proposition.getPredicative();
		for (Proposition p : predicative) {
			this.validate(p);
		}
	}

}
