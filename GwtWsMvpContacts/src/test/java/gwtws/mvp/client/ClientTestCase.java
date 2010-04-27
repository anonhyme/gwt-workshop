package gwtws.mvp.client;

import gwtws.mvp.client.guice.ClientTestModule;
import gwtws.mvp.server.guice.ServerModule;
import junit.framework.TestCase;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Base class for testing presenters. 
 * Tests extending this class only work in the JVM.
 */
public abstract class ClientTestCase extends TestCase {
	// Disarm GWT.create
	static {
		GWTMockUtilities.disarm();
	}

	protected Injector injector = Guice.createInjector(new ServerModule(), new ClientTestModule());
	protected EventBus eventBus = injector.getInstance(EventBus.class);
	protected DispatchAsync dispatcher = injector.getInstance(DispatchAsync.class);
}
