package br.com.brasilti.repository.enums;

public enum VerbEnum {

	LESSER(" < "),
	GREATER(" > "),
	LESSER_EQUALS(" <= "),
	GREATER_EQUALS(" >= "),
	EQUALS(" = "),
	NOT_EQUALS(" <> "),
	IS_NULL(" is null") {
		@Override
		public boolean requiresPredicative() {
			return false;
		}
	},
	IS_NOT_NULL(" is not null") {
		@Override
		public boolean requiresPredicative() {
			return false;
		}
	},
	BETWEEN(" between "),
	LIKE(" like "),
	IN(" in "),
	NOT_IN(" not in "),
	AND(" and ") {
		@Override
		public boolean isConnective() {
			return true;
		}
	},
	OR(" or ") {
		@Override
		public boolean isConnective() {
			return true;
		}
	},
	NOT("not ") {
		@Override
		public boolean requiresSubject() {
			return false;
		}
		
		@Override
		public boolean isConnective() {
			return true;
		}
	};

	private String value;

	private VerbEnum(String value) {
		this.value = value;
	}

	public boolean requiresSubject() {
		return true;
	}
	
	public boolean requiresPredicative() {
		return true;
	}
	
	public boolean isConnective() {
		return false;
	}

	public String getValue() {
		return value;
	}

}
