package gwtws.mvp.client.event;

import gwtws.mvp.shared.pojo.Contact;

import com.google.gwt.event.shared.GwtEvent;

public class ContactUpdatedEvent extends GwtEvent<ContactUpdatedEventHandler>{
  public static Type<ContactUpdatedEventHandler> TYPE = new Type<ContactUpdatedEventHandler>();
  private final Contact updatedContact;
  
  public ContactUpdatedEvent(Contact updatedContact) {
    this.updatedContact = updatedContact;
  }
  
  public Contact getUpdatedContact() { return updatedContact; }
  

  @Override
  public Type<ContactUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ContactUpdatedEventHandler handler) {
    handler.onContactUpdated(this);
  }
}
