package gwtws.mvp.shared.cmd;

import net.customware.gwt.dispatch.shared.Action;

import gwtws.mvp.shared.pojo.Contact;

@SuppressWarnings("serial")
public class AddContact implements Action<AddContactResult> {

	private Contact contact;

	@SuppressWarnings("unused")
	private AddContact() {
		super();
	}

	public AddContact(Contact contact) {
		this.contact = contact;
	}

	public Contact getContact() {
		// TODO Auto-generated method stub
		return contact;
	}

}
