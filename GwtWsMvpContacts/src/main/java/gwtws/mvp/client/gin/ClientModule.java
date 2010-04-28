package gwtws.mvp.client.gin;

import gwtws.mvp.client.place.AppPlaceManager;
import gwtws.mvp.client.place.ContactsPresenterPlace;
import gwtws.mvp.client.place.EditContactPlace;
import gwtws.mvp.client.presenter.ContactsPresenter;
import gwtws.mvp.client.presenter.EditContactPresenter;
import gwtws.mvp.client.presenter.MainPresenter;
import gwtws.mvp.client.view.ContactsView;
import gwtws.mvp.client.view.EditContactView;
import gwtws.mvp.client.view.MainView;
import net.customware.gwt.dispatch.client.DefaultDispatchAsync;
import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.gin.AbstractPresenterModule;
import net.customware.gwt.presenter.client.place.ParameterTokenFormatter;
import net.customware.gwt.presenter.client.place.PlaceManager;
import net.customware.gwt.presenter.client.place.TokenFormatter;

import com.google.inject.Singleton;

public class ClientModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
    bind(TokenFormatter.class).to(ParameterTokenFormatter.class);
    bind(PlaceManager.class).to(AppPlaceManager.class);
    bind(DefaultDispatchAsync.class);

    bindPresenter(MainPresenter.class,
        MainPresenter.Display.class, MainView.class);
    bindPresenter(ContactsPresenter.class, ContactsPresenter.Display.class,
        ContactsView.class);
    bindPresenter(EditContactPresenter.class,
        EditContactPresenter.Display.class, EditContactView.class);

    bind(ContactsPresenterPlace.class).in(Singleton.class);
    bind(EditContactPlace.class).in(Singleton.class);
  }
}
