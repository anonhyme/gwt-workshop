package gwtws.mvp.client.presenter;

import gwtws.mvp.client.ClientTestCase;
import gwtws.mvp.client.event.AddContactEvent;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class EditContactPresenterTest extends ClientTestCase {

	MainPresenter mainPresenter = injector.getInstance(MainPresenter.class);
	MainPresenter.Display mainView = mainPresenter.getDisplay();
	ContactsPresenter contactsPresenter = injector.getInstance(ContactsPresenter.class);
	ContactsPresenter.Display contactsView = injector.getInstance(ContactsPresenter.Display.class);
	EditContactPresenter editPresenter = injector.getInstance(EditContactPresenter.class);
	EditContactPresenter.Display editView = injector.getInstance(EditContactPresenter.Display.class);
	
	public void testContactsPresenter() throws Exception {
		
  	// Prepare the contacts view
		HasClickHandlers addBtn = EasyMock.createNiceMock(HasClickHandlers.class);
		HasClickHandlers delBtn = EasyMock.createNiceMock(HasClickHandlers.class);
		HasClickHandlers list = EasyMock.createNiceMock(HasClickHandlers.class);
		EasyMock.expect(contactsView.getAddButton()).andReturn(addBtn);
		EasyMock.expect(contactsView.getDeleteButton()).andReturn(delBtn);
		EasyMock.expect(contactsView.getList()).andReturn(list);
		EasyMock.expect(contactsView.asWidget()).andReturn(new Widget()).anyTimes() ;
		List<Integer> toDelete =  Arrays.asList(0, 10);
		EasyMock.expect(contactsView.getSelectedRows()).andReturn(toDelete);
		EasyMock.replay(contactsView);
		
  	// When the application starts, it gets all the contact from the server
		mainPresenter.onBind();
		assertEquals(22, contactsPresenter.contactDetails.size());
		// The list must be ordered
		assertEquals("Abigail Louis", contactsPresenter.contactDetails.get(0).getDisplayName());
		
		// The mocked edit view uses an unique easy-mock object for all HasText methods 
		HasText mockedText = editView.getFirstName();
		EasyMock.expect(mockedText.getText()).andReturn("aName").anyTimes();
		EasyMock.replay(mockedText);
		
		// Modify the first contact. The trick is that the mocked HasText always return the text 'aName'
		editPresenter.setId(contactsPresenter.contactDetails.get(0).getId());
		editPresenter.doSave();
		assertEquals("aName aName", contactsPresenter.contactDetails.get(0).getDisplayName());
		
		// Add a new contact
		assertEquals(22, contactsPresenter.contactDetails.size());
		eventBus.fireEvent(new AddContactEvent());
		editPresenter.doSave();
		assertEquals(23, contactsPresenter.contactDetails.size());
		
	}
}
