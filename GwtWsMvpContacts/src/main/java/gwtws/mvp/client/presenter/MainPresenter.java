package gwtws.mvp.client.presenter;

import gwtws.mvp.client.event.AddContactEvent;
import gwtws.mvp.client.event.AddContactEventHandler;
import gwtws.mvp.client.event.ContactDeletedEvent;
import gwtws.mvp.client.event.ContactDeletedEventHandler;
import gwtws.mvp.client.event.ContactUpdatedEvent;
import gwtws.mvp.client.event.ContactUpdatedEventHandler;
import gwtws.mvp.client.event.EditContactCancelledEvent;
import gwtws.mvp.client.event.EditContactCancelledEventHandler;
import gwtws.mvp.client.event.EditContactEvent;
import gwtws.mvp.client.event.EditContactEventHandler;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.PlaceManager;
import net.customware.gwt.presenter.client.widget.WidgetContainerDisplay;
import net.customware.gwt.presenter.client.widget.WidgetContainerPresenter;

import com.google.inject.Inject;

/**
 * Main presenter, it contains all presenters of the application
 */
public class MainPresenter extends
		WidgetContainerPresenter<MainPresenter.Display> {

	public interface Display extends WidgetContainerDisplay {
	}

	private ContactsPresenter contactsPresenter;
	private EditContactPresenter editContactPresenter;

	@Inject
	public MainPresenter(PlaceManager pmngr, Display display,
			EventBus bus, ContactsPresenter p1, EditContactPresenter p2) {
		super(display, bus, p1, p2);
		contactsPresenter = p1;
		editContactPresenter = p2;
	}

	@Override
	protected void onBind() {
		super.onBind();

		eventBus.addHandler(AddContactEvent.TYPE, new AddContactEventHandler() {
			public void onAddContact(AddContactEvent event) {
				editContactPresenter.revealDisplay(null);
			}
		});
		eventBus.addHandler(EditContactEvent.TYPE, new EditContactEventHandler() {
			public void onEditContact(EditContactEvent event) {
				editContactPresenter.revealDisplay(event.getId());
			}
		});
		eventBus.addHandler(EditContactCancelledEvent.TYPE,
				new EditContactCancelledEventHandler() {
					public void onEditContactCancelled(EditContactCancelledEvent event) {
						contactsPresenter.revealDisplay();
					}
				});
		eventBus.addHandler(ContactUpdatedEvent.TYPE,
				new ContactUpdatedEventHandler() {
					public void onContactUpdated(ContactUpdatedEvent event) {
						contactsPresenter.updateAndRevealDisplay();
					}
				});
		eventBus.addHandler(ContactDeletedEvent.TYPE,
				new ContactDeletedEventHandler() {
					public void onContactDeleted(ContactDeletedEvent event) {
						contactsPresenter.updateAndRevealDisplay();
					}
				});

		contactsPresenter.revealDisplay();
	}

}
