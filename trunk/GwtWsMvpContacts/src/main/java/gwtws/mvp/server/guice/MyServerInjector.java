package gwtws.mvp.server.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class MyServerInjector extends GuiceServletContextListener {

	protected Injector getInjector() {
		return Guice
				.createInjector(new MyServerModule(), new MyServletModule());
	}

}
