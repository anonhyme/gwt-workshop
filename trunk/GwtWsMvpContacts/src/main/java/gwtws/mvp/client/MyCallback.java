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

package gwtws.mvp.client;

import gwtws.mvp.client.event.FlashEvent;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.Display;
import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public abstract class MyCallback<T> implements AsyncCallback<T> {

	@SuppressWarnings("unused")
	private DispatchAsync dispatcher = null;
	@SuppressWarnings("unused")
	private Display display = null;

	private EventBus eventBus = null;

	@Inject
	public MyCallback(DispatchAsync dispatcher, EventBus bus) {
		this.dispatcher = dispatcher;
		this.eventBus = bus;
	}

	@Inject
	public MyCallback(DispatchAsync dispatcher, EventBus bus, Display display) {
		this(dispatcher, bus);
		this.display = display;
	}

	/**
	 * The callback code which the user has to implement
	 * 
	 * @param result
	 */
	public abstract void callback(T result);

	/**
	 * The callback code in the case of error Override this method, if you need
	 * this feature.
	 * 
	 * @param result
	 */
	public void callbackError(Throwable originalCaught) {
		eventBus.fireEvent(new FlashEvent(originalCaught));
	}

	/**
	 * If you override this method, remember to call super.onFailure()
	 */
	public void onFailure(Throwable caught) {
		callbackError(caught);
	}

	/**
	 * If you override this method, remember to call super.onSuccess()
	 */
	public void onSuccess(T result) {
		callback(result);
	}
}
