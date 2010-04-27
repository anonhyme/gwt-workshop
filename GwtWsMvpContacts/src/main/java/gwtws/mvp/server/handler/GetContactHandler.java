package gwtws.mvp.server.handler;

import gwtws.mvp.server.ContactDao;
import gwtws.mvp.shared.Contact;
import gwtws.mvp.shared.rpc.GetContact;
import gwtws.mvp.shared.rpc.GetContactResult;
import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

import com.google.inject.Inject;

public class GetContactHandler implements
		ActionHandler<GetContact, GetContactResult> {

	private ContactDao contactsDao;

	@Inject
	public GetContactHandler(ContactDao contactsDao) {
		super();
		this.contactsDao = contactsDao;
	}

	public GetContactResult execute(GetContact action, ExecutionContext context)
			throws ActionException {
		Contact contact = contactsDao.getContact(action.getId());
		GetContactResult result = new GetContactResult(contact);
		return result;
	}

	public Class<GetContact> getActionType() {
		return GetContact.class;
	}

	public void rollback(GetContact action, GetContactResult result,
			ExecutionContext context) throws ActionException {
	}

}
