package gwtws.seo.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

public class Properties extends JavaScriptObject {

  protected Properties() { }
  
  public static Properties create(String properties) {
    String s = properties.replaceFirst("^[({]*(.*)[})]*$", "$1");
    s = "({" + s + "})";
    return (Properties)createImpl(s);
  }
  
  public native static JavaScriptObject createImpl(String properties) /*-{
    return eval(properties);
  }-*/;
  
  public final native String get(String name) /*-{
    return "" + (this[name] ? this[name] : "");
  }-*/;
  
  public final native int getInt(String name) /*-{
    return this[name] ? this[name]: 0;
  }-*/;
  
  public final native float getFloat(String name) /*-{
    return this[name] ? this[name]: 0;
  }-*/;

  public final native double getDouble(String name) /*-{
    return this[name] ? this[name]: 0;
  }-*/;

  public final native boolean getBoolean(String name) /*-{
    return this[name] ? this[name] === true ? true : this[name] == 1 ? true : false : false;
  }-*/;
  
  public final String[] keys() {
    JsArrayString a = keysImpl();
    String[] ret = new String[a.length()];
    for (int i=0; i < a.length(); i++) {
      ret[i] = "" + a.get(i);
    }
    return ret;
  }
  public final native JsArrayString keysImpl() /*-{
    var key, keys=[];
    for(key in this) {
      keys.push("" + key); 
    }
    return keys;
  }-*/;
  
  public final String getString() {
    String ret = "";
    for (String s : keys()) {
      ret += s + " " + get(s);
    }
    return ret;
  }
}
