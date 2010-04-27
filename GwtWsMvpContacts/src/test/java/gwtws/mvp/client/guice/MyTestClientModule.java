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
package gwtws.mvp.client.guice;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.service.DispatchService;
import net.customware.gwt.dispatch.server.DefaultDispatch;
import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.Result;
import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.Display;
import net.customware.gwt.presenter.client.EventBus;

import org.easymock.EasyMock;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Guice module used to test presenters
 * 
 * @author manolo
 * 
 */
public class MyTestClientModule extends AbstractModule {

	// Override either, to change module behavior
	protected DispatchAsync dispatchAsyncInstance = null;
	protected Class<? extends DispatchAsync> dispatchAsyncClass = DispatchTestAsync.class;

	@Override
	protected void configure() {

		if (dispatchAsyncInstance == null) {
			bind(DispatchAsync.class).to(dispatchAsyncClass).in(Singleton.class);
		} else {
			bind(DispatchAsync.class).toInstance(dispatchAsyncInstance);
		}

		bind(EventBus.class).to(DefaultEventBus.class);

		bind(DispatchTestAsync.class);

		// bindDisplay(ContactsPresenter.Display.class);
		// bind(MessageSendPresenter.Display.class).to(MockMessageSendDisplay.class);
	}

	protected <D extends Display> void bindDisplay(final Class<D> display) {
		final D mockDisplay = EasyMock.createNiceMock(display);
		bind(display).toInstance(mockDisplay);
	}

	static class DispatchTestService implements DispatchService {
		private Dispatch dispatch;

		@Inject
		public DispatchTestService(Dispatch dispatch) {
			this.dispatch = dispatch;
		}

		public Result execute(Action<?> action) throws Exception {
			Result result = dispatch.execute(action);
			return result;
		}
	}

	static public class DispatchTestAsync implements DispatchAsync {
		private DefaultDispatch dispatch;

		@Inject
		public DispatchTestAsync(Dispatch dispatch) {
			this.dispatch = (DefaultDispatch) dispatch;
		}

		public <A extends Action<R>, R extends Result> void execute(A action,
				AsyncCallback<R> callback) {
			try {
				R result = dispatch.execute(action);
				callback.onSuccess(result);
			} catch (ActionException e) {
				callback.onFailure(e);
			}
		}
	}

}