package gwtws.mvp.client.presenter;

import gwtws.mvp.client.ClientTestCase;
import gwtws.mvp.client.event.FlashEvent;
import gwtws.mvp.client.event.FlashEventHandler;
import gwtws.mvp.shared.cmd.GetContactDetails;
import gwtws.mvp.shared.cmd.GetContactDetailsResult;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public class CallBackTest extends ClientTestCase {
	
	@SuppressWarnings("serial")
	class TestResult implements Result {
	}
	
	@SuppressWarnings("serial")
	public class TestAction implements Action<TestResult> {
	}

	boolean success = false;

	public void testCallBackOnSuccess() throws Exception {
		
		// When a RPC call fails, a FlashEvent is fired 
		eventBus.addHandler(FlashEvent.TYPE, new FlashEventHandler() {
			public void onError(FlashEvent event) {
				assertTrue(event.getError().contains("No handler is registered"));
				success = false;
			}
		});
		
		// Do a success call
		success = false;
		dispatcher.execute(new GetContactDetails(), new CallBack<GetContactDetailsResult>(dispatcher, eventBus) {
			public void callback(GetContactDetailsResult result) {
				success = true;
			}
		});
		assertTrue(success);
		
		// Do a wrong call (a unregistered action)
		success = true;
		dispatcher.execute(new TestAction(), new CallBack<TestResult>(dispatcher, eventBus) {
			public void callback(TestResult result) {
				success = true;
			}
		});
		assertFalse(success);
	}

}
