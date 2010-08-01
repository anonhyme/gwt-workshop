package gwtquery.plugins.myplugin.client;

import static com.google.gwt.query.client.GQuery.*;
import static gwtquery.plugins.myplugin.client.MyPlugin.MyPlugin;

import com.google.gwt.core.client.EntryPoint;

/**
 * Example code BasePlugin plugin for GwtQuery
 */
public class MyPluginSample implements EntryPoint {

  public void onModuleLoad() {
    
    $("div").as(MyPlugin).apply();
    
  }
}
