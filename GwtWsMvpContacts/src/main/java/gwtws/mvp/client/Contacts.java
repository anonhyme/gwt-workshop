package gwtws.mvp.client;

import gwtws.mvp.client.gin.ClientInjector;
import gwtws.mvp.client.presenter.MainPresenter;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point 
 */
public class Contacts implements EntryPoint {

	MainPresenter appPresenter;
	
	public Contacts() {
		this((ClientInjector)GWT.create(ClientInjector.class));
	}
	
	public Contacts(ClientInjector injector) {
		appPresenter = injector.getMainPresenter();
	}
	
	public void onModuleLoad() {
		appPresenter.bind();
	}
}
