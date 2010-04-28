package gwtws.mvp.client.presenter;

import gwtws.mvp.client.event.AddContactEvent;
import gwtws.mvp.client.event.ContactDeletedEvent;
import gwtws.mvp.client.event.EditContactEvent;
import gwtws.mvp.shared.cmd.DeleteContacts;
import gwtws.mvp.shared.cmd.DeleteContactsResult;
import gwtws.mvp.shared.cmd.GetContactDetails;
import gwtws.mvp.shared.cmd.GetContactDetailsResult;
import gwtws.mvp.shared.pojo.ContactDetails;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.inject.Inject;

public class ContactsPresenter extends
    WidgetPresenter<ContactsPresenter.Display> {

  private List<ContactDetails> contactDetails;
  

  public interface Display extends WidgetDisplay {
    HasClickHandlers getAddButton();

    HasClickHandlers getDeleteButton();

    HasClickHandlers getList();

    void setData(List<String> data);

    int getClickedRow(ClickEvent event);

    List<Integer> getSelectedRows();
  }

  private final DispatchAsync dispatcher;
  private final EventBus eventBus;
  private final Display display;

  @Inject
  public ContactsPresenter(Display view, EventBus eventBus,
      DispatchAsync dispatcher) {
    super(view, eventBus);
    this.eventBus = eventBus;
    this.display = view;
    this.dispatcher = dispatcher;
  }

  public void onBind() {
    display.getAddButton().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new AddContactEvent());
      }
    });

    display.getDeleteButton().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        deleteSelectedContacts();
      }
    });

    display.getList().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        int selectedRow = display.getClickedRow(event);

        if (selectedRow >= 0) {
          String id = contactDetails.get(selectedRow).getId();
          eventBus.fireEvent(new EditContactEvent(id));
        }
      }
    });

    fetchContactDetails();
  }

  protected void sortContactDetails() {
    // Yes, we could use a more optimized method of sorting, but the
    // point is to create a test case that helps illustrate the higher
    // level concepts used when creating MVP-based applications.
    //
    for (int i = 0; i < contactDetails.size(); ++i) {
      for (int j = 0; j < contactDetails.size() - 1; ++j) {
        if (contactDetails.get(j).getDisplayName().compareToIgnoreCase(
            contactDetails.get(j + 1).getDisplayName()) >= 0) {
          ContactDetails tmp = contactDetails.get(j);
          contactDetails.set(j, contactDetails.get(j + 1));
          contactDetails.set(j + 1, tmp);
        }
      }
    }
  }

  public void setContactDetails(List<ContactDetails> contactDetails) {
    this.contactDetails = contactDetails;
  }

  public ContactDetails getContactDetail(int index) {
    return contactDetails.get(index);
  }
  
  public List<ContactDetails> getContactDetails(){
    return contactDetails;
  }

  protected void fetchContactDetails() {
    dispatcher.execute(new GetContactDetails(),
        new CallBack<GetContactDetailsResult>(dispatcher, eventBus) {
          public void callback(GetContactDetailsResult result) {
            contactDetails = result.getContactList();
            sortContactDetails();
            List<String> data = new ArrayList<String>();
            for (ContactDetails c : contactDetails) {
              data.add(c.getDisplayName());
            }
            display.setData(data);
            System.out.println("SETTTT DATATATA");
          }
        });
  }

  protected void deleteSelectedContacts() {
    List<Integer> selectedRows = display.getSelectedRows();
    ArrayList<String> ids = new ArrayList<String>();
    for (int i = 0; i < selectedRows.size(); ++i) {
      ids.add(contactDetails.get(selectedRows.get(i)).getId());
    }
    dispatcher.execute(new DeleteContacts(ids),
        new CallBack<DeleteContactsResult>(dispatcher, eventBus) {
          @Override
          public void callback(DeleteContactsResult result) {
            eventBus.fireEvent(new ContactDeletedEvent());
          }
        });
  }

  public void updateAndRevealDisplay() {
    fetchContactDetails();
    super.revealDisplay();
  }

  @Override
  protected void onRevealDisplay() {
  }

  @Override
  protected void onUnbind() {
  }
}
