package gwtws.mvp.client.presenter;

import gwtws.mvp.client.MyCallback;
import gwtws.mvp.client.event.ContactUpdatedEvent;
import gwtws.mvp.client.event.EditContactCancelledEvent;
import gwtws.mvp.shared.Contact;
import gwtws.mvp.shared.rpc.AddContact;
import gwtws.mvp.shared.rpc.AddContactResult;
import gwtws.mvp.shared.rpc.GetContact;
import gwtws.mvp.shared.rpc.GetContactResult;
import gwtws.mvp.shared.rpc.SaveContact;
import gwtws.mvp.shared.rpc.SaveContactResult;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.inject.Inject;

public class EditContactPresenter extends
		WidgetPresenter<EditContactPresenter.Display> {
	public interface Display extends WidgetDisplay {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasText getFirstName();

		HasText getLastName();

		HasText getEmailAddress();
	}

	private Contact contact = new Contact();
	private final EventBus eventBus;
	private final Display display;
	private final DispatchAsync dispatcher;

	@Inject
	public EditContactPresenter(Display display, EventBus eventBus,
			DispatchAsync dispatcher) {
		super(display, eventBus);
		this.eventBus = eventBus;
		this.display = display;
		this.dispatcher = dispatcher;
	}

	public void onBind() {
		this.display.getSaveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doSave();
			}
		});

		this.display.getCancelButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditContactCancelledEvent());
			}
		});
	}

	protected void doSave() {
		contact.setFirstName(display.getFirstName().getText());
		contact.setLastName(display.getLastName().getText());
		contact.setEmailAddress(display.getEmailAddress().getText());

		if (contact.getId() == null) {
			dispatcher.execute(new AddContact(contact),
					new MyCallback<AddContactResult>(dispatcher, eventBus) {
						public void callback(AddContactResult result) {
							eventBus.fireEvent(new ContactUpdatedEvent(result.getContact()));
						}
					});
		} else {
			dispatcher.execute(new SaveContact(contact),
					new MyCallback<SaveContactResult>(dispatcher, eventBus) {
						public void callback(SaveContactResult result) {
							eventBus.fireEvent(new ContactUpdatedEvent(result.getContact()));
						}
					});
		}
	}
	
	String id ;
	
	public void setId(String id) {
		this.id = id;
		if (id == null) {
			contact = new Contact();
			refresh();
		} else {
			dispatcher.execute(new GetContact(id), new MyCallback<GetContactResult>(dispatcher, eventBus) {
				public void callback(GetContactResult result) {
					contact = result.getContact() != null ? result.getContact() : new Contact();
					refresh();
				}
			});
		}
	}
	
	private void refresh() {
		display.getFirstName().setText(contact.getFirstName());
		display.getLastName().setText(contact.getLastName());
		display.getEmailAddress().setText(contact.getEmailAddress());
	}
	
	public String getId() {
		return id;
	}

	protected void revealDisplay(String id) {
		setId(id);
		revealDisplay();
	}

	@Override
	protected void onRevealDisplay() {
	}

	@Override
	protected void onUnbind() {
	}

}
