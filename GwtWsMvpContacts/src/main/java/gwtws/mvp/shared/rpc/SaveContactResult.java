package gwtws.mvp.shared.rpc;

import gwtws.mvp.shared.Contact;
import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class SaveContactResult implements Result {
	private Contact contact;

	@SuppressWarnings("unused")
	private SaveContactResult() {

	}

	public SaveContactResult(Contact contact) {
		this.contact = contact;
	}

	public Contact getContact() {
		// TODO Auto-generated method stub
		return contact;
	}
}
