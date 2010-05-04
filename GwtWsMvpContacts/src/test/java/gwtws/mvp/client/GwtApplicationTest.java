package gwtws.mvp.client;

import gwtws.mvp.client.event.AddContactEvent;
import gwtws.mvp.client.event.ContactDeletedEvent;
import gwtws.mvp.client.event.ContactUpdatedEvent;
import gwtws.mvp.client.event.EditContactCancelledEvent;
import gwtws.mvp.client.event.EditContactEvent;
import gwtws.mvp.client.presenter.ContactsPresenter;
import gwtws.mvp.client.presenter.EditContactPresenter;
import gwtws.mvp.client.presenter.MainPresenter;
import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;

public class GwtApplicationTest extends GWTTestCase {
  
  // - A flag to enable/disable this gwt test.
  // - Doing TDD, it is better not to run these kinds of tests
  //   until the interaction ends, in order to speed up the
  //   development.
  // - It is not possible to use System.getProperty or System.getenv
  //   because this code has to be translatable into JS.
  private boolean skipGwtTest = false;

  public String getModuleName() {
    return skipGwtTest ? null: "gwtws.mvp.ContactsJUnit";
  }

  public void testApplication() {
    
    // Do not run this test within JVM
    if (skipGwtTest) {
      System.out.println("Skiping the tests because running in JVM, set skipGwtTest=false to enable it");
      return;
    }
    
    // Run the application
    final Contacts entryPoint = new Contacts();
    entryPoint.onModuleLoad();

    // Asynchronous test in order to wait for the first rpc call.
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
        
        // When the application starts, it ask for all the contacts to the presenter
        assertEquals(22, contactsPresenter.getContactDetails().size());
        
        // The first view presented id the list of contacts
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
        
        // Notify to the test system that everything was OK
        finishTest();
      }
      // wait enough time to get the first rpc data
    }.schedule(500);
  }

}
