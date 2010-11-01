package com.google.gwt.sample.stockwatcher.client;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
  void refreshWatchList(ArrayList<String> stocks, AsyncCallback<StockPrice[]> callback);
}
