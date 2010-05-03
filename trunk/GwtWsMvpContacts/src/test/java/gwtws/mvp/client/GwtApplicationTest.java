package gwtws.mvp.client;

import net.customware.gwt.presenter.client.EventBus;
import gwtws.mvp.client.event.AddContactEvent;
import gwtws.mvp.client.event.ContactDeletedEvent;
import gwtws.mvp.client.event.ContactUpdatedEvent;
import gwtws.mvp.client.event.EditContactCancelledEvent;
import gwtws.mvp.client.event.EditContactEvent;
import gwtws.mvp.client.presenter.ContactsPresenter;
import gwtws.mvp.client.presenter.EditContactPresenter;
import gwtws.mvp.client.presenter.MainPresenter;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;

public class GwtApplicationTest extends GWTTestCase {

  public String getModuleName() {
    return "gwtws.mvp.ContactsJUnit";
  }

  public void testApplication() {
    
    final Contacts entryPoint = new Contacts();
    entryPoint.onModuleLoad();

    delayTestFinish(5000);
    new Timer() {
      public void run() {
        
        EventBus eventBus = entryPoint.injector.getEventBus();
        MainPresenter mainPresenter = entryPoint.injector.getMainPresenter();
        MainPresenter.Display mainView = mainPresenter.getDisplay();
        ContactsPresenter contactsPresenter = entryPoint.injector.getContactsPresenter();
        ContactsPresenter.Display contactsView = contactsPresenter.getDisplay();
        EditContactPresenter editPresenter =  entryPoint.injector.getEditContactPresenter();
        EditContactPresenter.Display editView = editPresenter.getDisplay();
        
        assertEquals(22, contactsPresenter.getContactDetails().size());
        assertEquals(contactsView.asWidget(), mainView.asWidget());

        // Different events make change the view
        eventBus.fireEvent(new AddContactEvent());
        assertEquals(editView.asWidget(), mainView.asWidget());

        eventBus.fireEvent(new EditContactCancelledEvent());
        assertEquals(contactsView.asWidget(), mainView.asWidget());

        eventBus.fireEvent(new EditContactEvent("1"));
        assertEquals(editView.asWidget(), mainView.asWidget());

        eventBus.fireEvent(new ContactUpdatedEvent(null));
        assertEquals(contactsView.asWidget(), mainView.asWidget());

        eventBus.fireEvent(new ContactDeletedEvent());
        assertEquals(contactsView.asWidget(), mainView.asWidget());
        
        finishTest();
      }
    }.schedule(1000);
  }

}
