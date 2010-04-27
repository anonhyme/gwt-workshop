package gwtws.mvp.shared.rpc;

import gwtws.mvp.shared.ContactDetails;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class GetContactDetailsResult implements Result {
	List<ContactDetails> contactsList;

	public List<ContactDetails> getContactsList() {
		return contactsList;
	}

	public void setContactsList(List<ContactDetails> contactsList) {
		this.contactsList = contactsList;
	}

	public List<ContactDetails> getContactList() {
		return contactsList;
	}

}
