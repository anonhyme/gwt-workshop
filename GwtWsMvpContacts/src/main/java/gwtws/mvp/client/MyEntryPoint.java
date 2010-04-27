package gwtws.mvp.client;

import gwtws.mvp.client.gin.MyGinjector;
import gwtws.mvp.client.place.MyPlaceManager;
import gwtws.mvp.client.presenter.MyAppController;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point 
 */
public class MyEntryPoint implements EntryPoint {

	private final MyGinjector injector = GWT.create(MyGinjector.class);

	public void onModuleLoad() {
		MyAppController appPresenter = injector.getAppPresenter();
		appPresenter.bind();
		appPresenter.revealDisplay();
		
		MyPlaceManager placeManager = (MyPlaceManager)injector.getPlaceManager();
		placeManager.fireCurrentPlace();
	}
}
