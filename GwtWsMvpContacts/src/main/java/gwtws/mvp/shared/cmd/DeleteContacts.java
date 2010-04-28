package gwtws.mvp.shared.cmd;

import java.util.ArrayList;

import net.customware.gwt.dispatch.shared.Action;

@SuppressWarnings("serial")
public class DeleteContacts implements Action<DeleteContactsResult> {

  private ArrayList<String> ids;

  @SuppressWarnings("unused")
  private DeleteContacts() {
    super();
  }

  public DeleteContacts(ArrayList<String> ids) {
    this.ids = ids;
  }

  public ArrayList<String> getIds() {
    return ids;
  }

}
