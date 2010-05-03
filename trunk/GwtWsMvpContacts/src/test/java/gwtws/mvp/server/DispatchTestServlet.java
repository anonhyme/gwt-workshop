package gwtws.mvp.server;

import gwtws.mvp.server.guice.ServerModule;
import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.server.service.DispatchServiceServlet;

import com.google.inject.Guice;

/**
 * Just a wrapper for the Dispatcher servlet.
 * It is thought to run tests which use RPC calls.
 * 
 * It is necessary because there is no way to define 
 * a filter (guiceFilter) in the .gwt.xml file. 
 */
public class DispatchTestServlet extends DispatchServiceServlet {

  public DispatchTestServlet() {
    super(Guice.createInjector(new ServerModule()).getInstance(Dispatch.class));
  }

  private static final long serialVersionUID = 1L;

}
