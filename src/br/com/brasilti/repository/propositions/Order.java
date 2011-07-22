package br.com.brasilti.repository.propositions;

import javax.persistence.Query;

import br.com.brasilti.repository.enums.OrderEnum;
import br.com.brasilti.repository.enums.VerbEnum;

public class Order extends SimpleProposition {

	private Order(String fieldName, Object value) {
		super(fieldName, value);
	}

	public Order(String fieldName) {
		this(fieldName, OrderEnum.ASC.getValue());
	}

	public Order(String fieldName, OrderEnum orderEnum) {
		this(fieldName, orderEnum.getValue());
	}

	@Override
	public VerbEnum getVerb() {
		return VerbEnum.ORDER_BY;
	}

	@Override
	public void fillQLString(StringBuilder builder) {
		if (builder.indexOf(VerbEnum.ORDER_BY.getValue()) == -1) {
			builder.append(this.getVerb().getValue());
		} else {
			builder.append(", ");
		}

		builder.append("o.");
		builder.append(this.getSubject());
		builder.append(" ");
		builder.append(this.getPredicative()[0]);
	}

	@Override
	public void setParameters(Query query) {

	}

}
