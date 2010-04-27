package gwtws.mvp.server.handler;

import gwtws.mvp.server.ContactDao;
import gwtws.mvp.shared.Contact;
import gwtws.mvp.shared.rpc.AddContact;
import gwtws.mvp.shared.rpc.AddContactResult;
import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

import com.google.inject.Inject;

public class AddContactHandler implements
		ActionHandler<AddContact, AddContactResult> {

	private ContactDao contactDao;

	@Inject
	public AddContactHandler(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	public AddContactResult execute(AddContact action, ExecutionContext context)
			throws ActionException {
		Contact contact = contactDao.addContact(action.getContact());
		return new AddContactResult(contact);
	}

	public Class<AddContact> getActionType() {
		return AddContact.class;
	}

	public void rollback(AddContact action, AddContactResult result,
			ExecutionContext context) throws ActionException {
	}

}
