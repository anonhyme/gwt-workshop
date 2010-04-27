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

package gwtws.mvp.client.gin;

import gwtws.mvp.client.place.ContactsPresenterPlace;
import gwtws.mvp.client.place.EditContactPlace;
import gwtws.mvp.client.place.MyPlaceManager;
import gwtws.mvp.client.presenter.MainPresenter;
import gwtws.mvp.client.presenter.ContactsPresenter;
import gwtws.mvp.client.presenter.EditContactPresenter;
import gwtws.mvp.client.view.MainView;
import gwtws.mvp.client.view.ContactsView;
import gwtws.mvp.client.view.EditContactView;
import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.gin.AbstractPresenterModule;
import net.customware.gwt.presenter.client.place.ParameterTokenFormatter;
import net.customware.gwt.presenter.client.place.PlaceManager;
import net.customware.gwt.presenter.client.place.TokenFormatter;

import com.google.inject.Singleton;

public class MyClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {

		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);

		bind(TokenFormatter.class).to(ParameterTokenFormatter.class);
		bind(PlaceManager.class).to(MyPlaceManager.class);

		bindPresenter(MainPresenter.class,
				MainPresenter.Display.class, MainView.class);
		bindPresenter(ContactsPresenter.class, ContactsPresenter.Display.class,
				ContactsView.class);
		bindPresenter(EditContactPresenter.class,
				EditContactPresenter.Display.class, EditContactView.class);

		bind(ContactsPresenterPlace.class).in(Singleton.class);
		bind(EditContactPlace.class).in(Singleton.class);

	}

}
