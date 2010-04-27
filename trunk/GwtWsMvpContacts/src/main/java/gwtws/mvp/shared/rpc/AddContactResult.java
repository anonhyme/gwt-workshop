package gwtws.mvp.shared.rpc;

import gwtws.mvp.shared.Contact;
import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class AddContactResult implements Result {

	private Contact contact;

	@SuppressWarnings("unused")
	private AddContactResult() {

	}

	public AddContactResult(Contact contact) {
		this.contact = contact;
	}

	public Contact getContact() {
		return contact;
	}

}
