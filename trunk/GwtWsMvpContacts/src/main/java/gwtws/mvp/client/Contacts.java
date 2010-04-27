package gwtws.mvp.client;

import gwtws.mvp.client.gin.ClientInjector;
import gwtws.mvp.client.place.PlaceManager;
import gwtws.mvp.client.presenter.MainPresenter;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point 
 */
public class Contacts implements EntryPoint {
	private final ClientInjector injector = GWT.create(ClientInjector.class);

	public void onModuleLoad() {
		MainPresenter appPresenter = injector.getAppPresenter();
		appPresenter.bind();
		appPresenter.revealDisplay();
		
		PlaceManager placeManager = (PlaceManager)injector.getPlaceManager();
		placeManager.fireCurrentPlace();
	}
}
