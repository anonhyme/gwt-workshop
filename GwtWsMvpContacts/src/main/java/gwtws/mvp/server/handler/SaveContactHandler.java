package gwtws.mvp.server.handler;

import gwtws.mvp.server.ContactDao;
import gwtws.mvp.shared.cmd.SaveContact;
import gwtws.mvp.shared.cmd.SaveContactResult;
import gwtws.mvp.shared.pojo.Contact;
import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

import com.google.inject.Inject;

public class SaveContactHandler implements
    ActionHandler<SaveContact, SaveContactResult> {
  private ContactDao contactDao;

  @Inject
  public SaveContactHandler(ContactDao contactDao) {
    this.contactDao = contactDao;
  }

  public SaveContactResult execute(SaveContact action, ExecutionContext context)
      throws ActionException {
    Contact contact = contactDao.updateContact(action.getContact());
    SaveContactResult saveContactResult = new SaveContactResult(contact);
    return saveContactResult;
  }

  public Class<SaveContact> getActionType() {
    return SaveContact.class;
  }

  public void rollback(SaveContact action, SaveContactResult result,
      ExecutionContext context) throws ActionException {
  }

}
