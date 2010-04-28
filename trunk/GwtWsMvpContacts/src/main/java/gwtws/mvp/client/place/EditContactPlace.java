package gwtws.mvp.client.place;

import gwtws.mvp.client.presenter.EditContactPresenter;
import net.customware.gwt.presenter.client.gin.ProvidedPresenterPlace;
import net.customware.gwt.presenter.client.place.PlaceRequest;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class EditContactPlace extends
    ProvidedPresenterPlace<EditContactPresenter> {

  @Inject
  public EditContactPlace(Provider<EditContactPresenter> presenter) {
    super(presenter);
  }

  @Override
  public String getName() {
    return "EditContact";
  }
  
  @Override
  public void preparePresenter(PlaceRequest request, EditContactPresenter presenter) {
    presenter.setId(request.getParameter("id", null));
  }
  
  @Override
  public PlaceRequest prepareRequest(PlaceRequest request, EditContactPresenter presenter) {
    if (presenter.getId() != null) {
      request = request.with("id", presenter.getId());
    }
    return request;
  }

}
