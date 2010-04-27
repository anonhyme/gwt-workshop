package gwtws.mvp.client;

import gwtws.mvp.client.gin.MyClientInjector;
import gwtws.mvp.client.place.MyPlaceManager;
import gwtws.mvp.client.presenter.MainPresenter;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point 
 */
public class MyEntryPoint implements EntryPoint {

	private final MyClientInjector injector = GWT.create(MyClientInjector.class);

	public void onModuleLoad() {
		MainPresenter appPresenter = injector.getAppPresenter();
		appPresenter.bind();
		appPresenter.revealDisplay();
		
		MyPlaceManager placeManager = (MyPlaceManager)injector.getPlaceManager();
		placeManager.fireCurrentPlace();
	}
}
