package gwtws.mvp.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class FlashEvent extends GwtEvent<FlashEventHandler> {
  public static Type<FlashEventHandler> TYPE = new Type<FlashEventHandler>();
  
  @Override
  public Type<FlashEventHandler> getAssociatedType() {
    return TYPE;
  }
  
  private String error;
  
  public FlashEvent(Throwable c) {
    error = c.getMessage();
  }
  
  public FlashEvent(String s) {
    error = s;
  }
  
  public String getError() {
    return error;
  }

  @Override
  protected void dispatch(FlashEventHandler handler) {
    handler.onError(this);
  }
}
