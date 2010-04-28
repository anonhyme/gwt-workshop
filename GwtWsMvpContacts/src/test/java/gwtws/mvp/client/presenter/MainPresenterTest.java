package gwtws.mvp.client.presenter;

import gwtws.mvp.client.ClientTestCase;
import gwtws.mvp.client.Contacts;
import gwtws.mvp.client.event.AddContactEvent;
import gwtws.mvp.client.event.ContactDeletedEvent;
import gwtws.mvp.client.event.ContactUpdatedEvent;
import gwtws.mvp.client.event.EditContactCancelledEvent;
import gwtws.mvp.client.event.EditContactEvent;
import gwtws.mvp.client.gin.ClientInjector;

import org.easymock.EasyMock;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public class MainPresenterTest extends ClientTestCase {

	private MainPresenter mainPresenter = injector.getInstance(MainPresenter.class);
	private MainPresenter.Display mainView = mainPresenter.getDisplay();
	private ContactsPresenter.Display contactsView = injector.getInstance(ContactsPresenter.Display.class);
	private EditContactPresenter.Display editContactView = injector.getInstance(EditContactPresenter.Display.class);

	public void testApplicationController() throws Exception {
		
	  // Prepare the contacts view
		HasClickHandlers addBtn = EasyMock.createNiceMock(HasClickHandlers.class);
		HasClickHandlers delBtn = EasyMock.createNiceMock(HasClickHandlers.class);
		HasClickHandlers list = EasyMock.createNiceMock(HasClickHandlers.class);
		EasyMock.expect(contactsView.getAddButton()).andReturn(addBtn);
		EasyMock.expect(contactsView.getDeleteButton()).andReturn(delBtn);
		EasyMock.expect(contactsView.getList()).andReturn(list);
		EasyMock.expect(contactsView.asWidget()).andReturn(new Widget()).anyTimes();
		EasyMock.replay(contactsView);
		
		// When the app starts, the contacts view is shown
		assertNull(mainView.asWidget());
		Contacts entryPoint = new Contacts(new ClientInjector() {
			public MainPresenter getMainPresenter() {
				return injector.getInstance(MainPresenter.class);
			}
		});
		entryPoint.onModuleLoad();
		assertEquals(contactsView.asWidget(), mainView.asWidget());

		// Different events make change the view
		eventBus.fireEvent(new AddContactEvent());
		assertEquals(editContactView.asWidget(), mainView.asWidget());

		eventBus.fireEvent(new EditContactCancelledEvent());
		assertEquals(contactsView.asWidget(), mainView.asWidget());

		eventBus.fireEvent(new EditContactEvent("1"));
		assertEquals(editContactView.asWidget(), mainView.asWidget());

		eventBus.fireEvent(new ContactUpdatedEvent(null));
		assertEquals(contactsView.asWidget(), mainView.asWidget());

		eventBus.fireEvent(new ContactDeletedEvent());
		assertEquals(contactsView.asWidget(), mainView.asWidget());
	}
}
