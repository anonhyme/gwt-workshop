package gwtws.mvp.shared.cmd;

import gwtws.mvp.shared.pojo.Contact;
import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class SaveContact implements Action<SaveContactResult> {
	private Contact contact;

	@SuppressWarnings("unused")
	private SaveContact() {
	}

	public SaveContact(Contact contact) {
		this.contact = contact;
	}

	public Contact getContact() {
		return contact;
	}

}
