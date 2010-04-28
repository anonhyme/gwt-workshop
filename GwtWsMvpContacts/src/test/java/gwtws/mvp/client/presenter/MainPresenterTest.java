package gwtws.mvp.client.presenter;

import gwtws.mvp.client.ClientTestCase;
import gwtws.mvp.client.event.AddContactEvent;
import gwtws.mvp.client.event.ContactDeletedEvent;
import gwtws.mvp.client.event.ContactUpdatedEvent;
import gwtws.mvp.client.event.EditContactCancelledEvent;
import gwtws.mvp.client.event.EditContactEvent;

import org.easymock.EasyMock;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public class MainPresenterTest extends ClientTestCase {

	MainPresenter presenter = injector
			.getInstance(MainPresenter.class);
	MainPresenter.Display mainDisplay = presenter.getDisplay();
	ContactsPresenter contactsPresenter = injector
			.getInstance(ContactsPresenter.class);
	ContactsPresenter.Display contactsDisplay = injector
			.getInstance(ContactsPresenter.Display.class);
	EditContactPresenter.Display editContactDisplay = injector
			.getInstance(EditContactPresenter.Display.class);

	public void testApplicationController() throws Exception {
		
		HasClickHandlers addBtn = EasyMock.createNiceMock(HasClickHandlers.class);
		HasClickHandlers delBtn = EasyMock.createNiceMock(HasClickHandlers.class);
		HasClickHandlers list = EasyMock.createNiceMock(HasClickHandlers.class);
		EasyMock.expect(contactsDisplay.getAddButton()).andReturn(addBtn);
		EasyMock.expect(contactsDisplay.getDeleteButton()).andReturn(delBtn);
		EasyMock.expect(contactsDisplay.getList()).andReturn(list);
		EasyMock.expect(contactsDisplay.asWidget()).andReturn(new Widget()).anyTimes();
		EasyMock.replay(contactsDisplay);

		assertNull(mainDisplay.asWidget());
		presenter.onBind();
		assertEquals(contactsDisplay.asWidget(), mainDisplay.asWidget());

		eventBus.fireEvent(new AddContactEvent());
		assertEquals(editContactDisplay.asWidget(), mainDisplay.asWidget());

		eventBus.fireEvent(new EditContactCancelledEvent());
		assertEquals(contactsDisplay.asWidget(), mainDisplay.asWidget());

		eventBus.fireEvent(new EditContactEvent("1"));
		assertEquals(editContactDisplay.asWidget(), mainDisplay.asWidget());

		eventBus.fireEvent(new ContactUpdatedEvent(null));
		assertEquals(contactsDisplay.asWidget(), mainDisplay.asWidget());

		eventBus.fireEvent(new ContactDeletedEvent());
		assertEquals(contactsDisplay.asWidget(), mainDisplay.asWidget());
	}
}
