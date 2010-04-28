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

  ContactsPresenter contactsPresenter = injector.getInstance(ContactsPresenter.class);
  ContactsPresenter.Display contactsView = contactsPresenter.getDisplay();
  MainPresenter mainPresenter = injector.getInstance(MainPresenter.class);
  
  
  // Test the sort algorithm
  public void testContactSort() {
    ArrayList<ContactDetails> contactDetails = new ArrayList<ContactDetails>();
    contactDetails.add(new ContactDetails("0", "c_contact"));
    contactDetails.add(new ContactDetails("1", "b_contact"));
    contactDetails.add(new ContactDetails("2", "a_contact"));
    contactsPresenter.setContactDetails(contactDetails);
    contactsPresenter.sortContactDetails();
    assertTrue(contactsPresenter.getContactDetail(0).getDisplayName().equals("a_contact"));
    assertTrue(contactsPresenter.getContactDetail(1).getDisplayName().equals("b_contact"));
    assertTrue(contactsPresenter.getContactDetail(2).getDisplayName().equals("c_contact"));
  }
  
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
    assertEquals(22, contactsPresenter.getContactDetails().size());
    assertEquals("Abigail Louis", contactsPresenter.getContactDetails().get(0).getDisplayName());
  
    // Delete two contacts
    contactsPresenter.deleteSelectedContacts();
    assertEquals(20, contactsPresenter.getContactDetails().size());
    assertEquals("Bell Snedden", contactsPresenter.getContactDetails().get(0).getDisplayName());
  }
  
}
