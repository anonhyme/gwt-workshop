package gwtws.mvp.server.handler;

import gwtws.mvp.server.ContactDao;
import gwtws.mvp.shared.ContactDetails;
import gwtws.mvp.shared.rpc.DeleteContacts;
import gwtws.mvp.shared.rpc.DeleteContactsResult;

import java.util.List;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

import com.google.inject.Inject;

public class DeleteContactsHandler implements
		ActionHandler<DeleteContacts, DeleteContactsResult> {

	private ContactDao contactDao;

	@Inject
	public DeleteContactsHandler(ContactDao contactDao) {
		super();
		this.contactDao = contactDao;
	}

	public DeleteContactsResult execute(DeleteContacts deleteContacts,
			ExecutionContext context) throws ActionException {
		List<ContactDetails> contactDetails = contactDao
				.deleteContacts(deleteContacts.getIds());
		return new DeleteContactsResult(contactDetails);
	}

	public Class<DeleteContacts> getActionType() {
		return DeleteContacts.class;
	}

	public void rollback(DeleteContacts action, DeleteContactsResult result,
			ExecutionContext context) throws ActionException {
	}

}
