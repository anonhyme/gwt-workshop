package gwtws.mvp.shared.cmd;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class GetContact implements Action<GetContactResult> {

  private String id;

  @SuppressWarnings("unused")
  private GetContact() {
  }

  public GetContact(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

}
