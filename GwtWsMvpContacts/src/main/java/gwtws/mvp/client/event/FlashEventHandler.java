package gwtws.mvp.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface FlashEventHandler extends EventHandler {
	void onError(FlashEvent event);
}
