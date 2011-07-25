package br.com.brasilti.repository.validators;

import java.util.List;

import br.com.brasilti.repository.enums.ErrorEnum;
import br.com.brasilti.repository.enums.VerbEnum;
import br.com.brasilti.repository.exceptions.RepositoryException;
import br.com.brasilti.repository.propositions.Proposition;
import br.com.brasilti.utils.reflection.ReflectionUtil;

public class PropositionValidator {

	public void validate(Proposition proposition) throws RepositoryException {
		VerbEnum verbEnum = proposition.getVerb();
		if (verbEnum.requiresSubject() && proposition.getSubject() == null) {
			throw new RepositoryException(ErrorEnum.NULL_SUBJECT);
		}

		if (verbEnum.requiresPredicative() && hasEmptyPredicative(proposition)) {
			throw new RepositoryException(ErrorEnum.EMPTY_PREDICATIVE);
		}

		if (verbEnum.isConnective()) {
			this.validateInternalPropositions(proposition);
		}
	}

	private boolean hasEmptyPredicative(Proposition proposition) {
		Object[] predicative = proposition.getPredicative();

		if (predicative.length == 0) {
			return true;
		}

		for (Object elementOfPredicative : predicative) {
			if (elementOfPredicative == null) {
				return true;
			}

			if (ReflectionUtil.isCollection(elementOfPredicative.getClass())) {
				List<?> list = (List<?>) elementOfPredicative;
				if (list.isEmpty()) {
					return true;
				}

				for (Object elementOfList : list) {
					if (elementOfList == null) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private void validateInternalPropositions(Proposition proposition) throws RepositoryException {
		Proposition subject = (Proposition) proposition.getSubject();
		if (subject != null) {
			this.validateTermOf(subject);
		}

		Proposition[] predicative = (Proposition[]) proposition.getPredicative();
		for (Proposition elementOfPredicative : predicative) {
			this.validateTermOf(elementOfPredicative);
		}
	}

	private void validateTermOf(Proposition proposition) throws RepositoryException {
		if (proposition.getVerb().equals(VerbEnum.ORDER_BY)) {
			throw new RepositoryException(ErrorEnum.ORDER_NOT_ALLOWED);
		}

		this.validate(proposition);
	}

}
