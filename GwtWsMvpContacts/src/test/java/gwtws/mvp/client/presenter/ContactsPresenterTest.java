package gwtws.mvp.client.presenter;

import gwtws.mvp.client.ClientTestCase;
import gwtws.mvp.shared.pojo.ContactDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public class ContactsPresenterTest extends ClientTestCase {

	ContactsPresenter presenter = injector.getInstance(ContactsPresenter.class);
	ContactsPresenter.Display contactsDspl = presenter.getDisplay();
	MainPresenter controller = injector.getInstance(MainPresenter.class);
	
  public void testContactSort() {
    ArrayList<ContactDetails> contactDetails = new ArrayList<ContactDetails>();
    contactDetails.add(new ContactDetails("0", "c_contact"));
    contactDetails.add(new ContactDetails("1", "b_contact"));
    contactDetails.add(new ContactDetails("2", "a_contact"));
    presenter.setContactDetails(contactDetails);
    presenter.sortContactDetails();
    assertTrue(presenter.getContactDetail(0).getDisplayName().equals("a_contact"));
    assertTrue(presenter.getContactDetail(1).getDisplayName().equals("b_contact"));
    assertTrue(presenter.getContactDetail(2).getDisplayName().equals("c_contact"));
  }
  
	public void testContactsPresenter() throws Exception {
		HasClickHandlers addBtn = EasyMock.createNiceMock(HasClickHandlers.class);
		HasClickHandlers delBtn = EasyMock.createNiceMock(HasClickHandlers.class);
		HasClickHandlers list = EasyMock.createNiceMock(HasClickHandlers.class);
		EasyMock.expect(contactsDspl.getAddButton()).andReturn(addBtn);
		EasyMock.expect(contactsDspl.getDeleteButton()).andReturn(delBtn);
		EasyMock.expect(contactsDspl.getList()).andReturn(list);
		EasyMock.expect(contactsDspl.asWidget()).andReturn(new Widget()).anyTimes() ;
		List<Integer> toDelete =  Arrays.asList(0, 10);
		EasyMock.expect(contactsDspl.getSelectedRows()).andReturn(toDelete);
		EasyMock.replay(contactsDspl);
		
		controller.onBind();
		assertEquals(22, presenter.contactDetails.size());
		assertEquals("Abigail Louis", presenter.contactDetails.get(0).getDisplayName());
	
		presenter.deleteSelectedContacts();
		assertEquals(20, presenter.contactDetails.size());
		assertEquals("Bell Snedden", presenter.contactDetails.get(0).getDisplayName());
	}
	
}
