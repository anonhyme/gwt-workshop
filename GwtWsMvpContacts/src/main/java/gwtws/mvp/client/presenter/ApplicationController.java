/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

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
public class ApplicationController extends
		WidgetContainerPresenter<ApplicationController.Display> {

	public interface Display extends WidgetContainerDisplay {
	}

	private ContactsPresenter contactsPresenter;
	private EditContactPresenter editContactPresenter;

	@Inject
	public ApplicationController(PlaceManager pmngr, Display display,
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
