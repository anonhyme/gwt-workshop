package gwtws.mvp.server.guice;

import net.customware.gwt.dispatch.server.service.DispatchServiceServlet;

import com.google.inject.servlet.ServletModule;

public class MyServletModule extends ServletModule
{
	@Override
	public void configureServlets()
	{
		serve("/contacts/dispatch").with(DispatchServiceServlet.class);
	}
}
