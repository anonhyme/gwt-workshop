package gwtws.mvp.server.guice;

import gwtws.mvp.server.guice.ServerModule.WebModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class ServerInjector extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServerModule(), new WebModule());
  }

}
