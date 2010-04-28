package gwtws.mvp.client.place;

import gwtws.mvp.client.presenter.ContactsPresenter;
import net.customware.gwt.presenter.client.gin.ProvidedPresenterPlace;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ContactsPresenterPlace extends
    ProvidedPresenterPlace<ContactsPresenter> {

  @Inject
  public ContactsPresenterPlace(Provider<ContactsPresenter> presenter) {
    super(presenter);
  }

  @Override
  public String getName() {
    return "Contacts";
  }

}
