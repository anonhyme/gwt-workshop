package gwtws.mvp.server.handler;

import gwtws.mvp.server.ContactDao;
import gwtws.mvp.shared.cmd.GetContactDetails;
import gwtws.mvp.shared.cmd.GetContactDetailsResult;
import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

import com.google.inject.Inject;

public class GetContactsDetailDetailHandler implements
		ActionHandler<GetContactDetails, GetContactDetailsResult> {

	private ContactDao contactsDao;

	@Inject
	public GetContactsDetailDetailHandler(ContactDao contactsDao) {
		this.contactsDao = contactsDao;
	}

	public GetContactDetailsResult execute(GetContactDetails action,
			ExecutionContext context) throws ActionException {

		GetContactDetailsResult getContactDetailsResult = new GetContactDetailsResult();

		getContactDetailsResult.setContactsList(contactsDao.getContactDetails());

		return getContactDetailsResult;

	}

	public Class<GetContactDetails> getActionType() {
		return GetContactDetails.class;
	}

	public void rollback(GetContactDetails action,
			GetContactDetailsResult result, ExecutionContext context)
			throws ActionException {
	}
}
