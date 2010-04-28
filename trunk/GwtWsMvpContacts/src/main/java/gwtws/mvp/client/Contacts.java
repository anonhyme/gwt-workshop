package gwtws.mvp.client;

import gwtws.mvp.client.gin.ClientInjector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point 
 */
public class Contacts implements EntryPoint {

  protected final ClientInjector injector;
  
  public Contacts() {
    injector = GWT.create(ClientInjector.class);
  }
  
  // For testing
  public Contacts(ClientInjector clientInjector) {
    injector = clientInjector;
  }
  
  public void onModuleLoad() {
    injector.getMainPresenter().bind();
  }
}
