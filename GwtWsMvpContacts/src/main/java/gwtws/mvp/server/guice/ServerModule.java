package gwtws.mvp.server.guice;

import gwtws.mvp.server.ContactDao;
import gwtws.mvp.server.ContactsDaoInMemory;
import gwtws.mvp.server.handler.AddContactHandler;
import gwtws.mvp.server.handler.DeleteContactsHandler;
import gwtws.mvp.server.handler.GetContactHandler;
import gwtws.mvp.server.handler.GetContactsDetailDetailHandler;
import gwtws.mvp.server.handler.SaveContactHandler;
import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import net.customware.gwt.dispatch.server.service.DispatchServiceServlet;

import com.google.inject.Module;
import com.google.inject.servlet.ServletModule;

public class ServerModule extends ActionHandlerModule implements Module {

	public static class WebModule extends ServletModule {
		@Override
		public void configureServlets() {
			serve("/contacts/dispatch").with(DispatchServiceServlet.class);
		}
	}

	@Override
	protected void configureHandlers() {
		bindHandler(GetContactsDetailDetailHandler.class);
		bindHandler(GetContactHandler.class);
		bind(ContactDao.class).to(ContactsDaoInMemory.class);
		bindHandler(SaveContactHandler.class);
		bindHandler(DeleteContactsHandler.class);
		bindHandler(AddContactHandler.class);
	}

}
