package gwtws.mvp.client.presenter;

import gwtws.mvp.client.event.FlashEvent;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.Display;
import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public abstract class CallBack<T> implements AsyncCallback<T> {

	@SuppressWarnings("unused")
	private DispatchAsync dispatcher = null;
	@SuppressWarnings("unused")
	private Display display = null;

	private EventBus eventBus = null;

	@Inject
	public CallBack(DispatchAsync dispatcher, EventBus bus) {
		this.dispatcher = dispatcher;
		this.eventBus = bus;
	}
	
	/**
	 * This is the callback the user has to implement.
	 * 
	 * @param result
	 */
	public abstract void callback(T result);

	/**
	 * The callback code in the case of error.
	 * Override this method, if you need to handle it.
	 * 
	 * @param result
	 */
	public void callbackError(Throwable originalCaught) {
		eventBus.fireEvent(new FlashEvent(originalCaught));
	}

	/**
	 * If you override this method, remember to call super.onFailure().
	 */
	public void onFailure(Throwable caught) {
		callbackError(caught);
	}

	/**
	 * If you override this method, remember to call super.onSuccess().
	 */
	public void onSuccess(T result) {
		callback(result);
	}
}
