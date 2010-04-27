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

import gwtws.mvp.client.MyClientTestCase;
import gwtws.mvp.client.event.AddContactEvent;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class EditContactPresenterTest extends MyClientTestCase {

	ApplicationController controller = injector.getInstance(ApplicationController.class);
	ApplicationController.Display appDspl = controller.getDisplay();
	ContactsPresenter contactsPresenter = injector.getInstance(ContactsPresenter.class);
	ContactsPresenter.Display contactsDspl = injector.getInstance(ContactsPresenter.Display.class);
	EditContactPresenter editPresenter = injector.getInstance(EditContactPresenter.class);
	EditContactPresenter.Display editDspl = injector.getInstance(EditContactPresenter.Display.class);
	
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
		assertEquals(22, contactsPresenter.contactDetails.size());
		assertEquals("Abigail Louis", contactsPresenter.contactDetails.get(0).getDisplayName());
		
		HasText mockedText = editDspl.getFirstName();
		EasyMock.expect(mockedText.getText()).andReturn("aName").anyTimes();
		EasyMock.replay(mockedText);
		
		editPresenter.setId(contactsPresenter.contactDetails.get(0).getId());
		editPresenter.doSave();
		assertEquals("aName aName", contactsPresenter.contactDetails.get(0).getDisplayName());
		
		eventBus.fireEvent(new AddContactEvent());
		assertEquals(22, contactsPresenter.contactDetails.size());
		editPresenter.doSave();
		assertEquals(23, contactsPresenter.contactDetails.size());
		
	}
}
