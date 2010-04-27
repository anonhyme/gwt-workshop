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
	MainPresenter.Display appDspl = presenter.getDisplay();
	ContactsPresenter contactsPresenter = injector
			.getInstance(ContactsPresenter.class);
	ContactsPresenter.Display contactsDspl = injector
			.getInstance(ContactsPresenter.Display.class);
	EditContactPresenter.Display editDspl = injector
			.getInstance(EditContactPresenter.Display.class);

	public void testApplicationController() throws Exception {
		HasClickHandlers addBtn = EasyMock.createNiceMock(HasClickHandlers.class);
		HasClickHandlers delBtn = EasyMock.createNiceMock(HasClickHandlers.class);
		HasClickHandlers list = EasyMock.createNiceMock(HasClickHandlers.class);
		EasyMock.expect(contactsDspl.getAddButton()).andReturn(addBtn);
		EasyMock.expect(contactsDspl.getDeleteButton()).andReturn(delBtn);
		EasyMock.expect(contactsDspl.getList()).andReturn(list);
		EasyMock.expect(contactsDspl.asWidget()).andReturn(new Widget()).anyTimes();
		EasyMock.replay(contactsDspl);

		assertNull(appDspl.asWidget());
		presenter.onBind();
		assertEquals(contactsDspl.asWidget(), appDspl.asWidget());

		eventBus.fireEvent(new AddContactEvent());
		assertEquals(editDspl.asWidget(), appDspl.asWidget());

		eventBus.fireEvent(new EditContactCancelledEvent());
		assertEquals(contactsDspl.asWidget(), appDspl.asWidget());

		eventBus.fireEvent(new EditContactEvent("1"));
		assertEquals(editDspl.asWidget(), appDspl.asWidget());

		eventBus.fireEvent(new ContactUpdatedEvent(null));
		assertEquals(contactsDspl.asWidget(), appDspl.asWidget());

		eventBus.fireEvent(new ContactDeletedEvent());
		assertEquals(contactsDspl.asWidget(), appDspl.asWidget());
	}
}
