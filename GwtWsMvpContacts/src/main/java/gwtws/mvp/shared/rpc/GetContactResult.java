package gwtws.mvp.shared.rpc;

import gwtws.mvp.shared.Contact;
import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class GetContactResult implements Result {

	private Contact result;

	@SuppressWarnings("unused")
	private GetContactResult() {
	}

	public GetContactResult(Contact contact) {
		this.result = contact;
	}

	public Contact getContact() {

		return result;
	}

}
