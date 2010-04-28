package gwtws.mvp.shared.cmd;

import gwtws.mvp.shared.pojo.Contact;
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
    return contact;
  }
}
