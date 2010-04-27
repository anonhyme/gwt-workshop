package gwtws.mvp.shared.rpc;

import gwtws.mvp.shared.ContactDetails;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class DeleteContactsResult implements Result {

	List<ContactDetails> contactDetails;

	@SuppressWarnings("unused")
	private DeleteContactsResult() {
		super();
	}

	public DeleteContactsResult(List<ContactDetails> contactDetails) {
		super();
		this.contactDetails = contactDetails;
	}

	public List<ContactDetails> getContactDetails() {
		return contactDetails;
	}

}
