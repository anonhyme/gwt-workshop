package gwtquery.plugins.myplugin.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.Plugin;
import static com.google.gwt.query.client.plugins.Effects.Effects;

/**
 * MyPlugin for GwtQuery
 */
public class MyPlugin extends GQuery {


  // A shortcut to the class 
  public static final Class<MyPlugin> MyPlugin = MyPlugin.class;

  // Register the plugin in GQuery
  static {
    GQuery.registerPlugin(MyPlugin.class, new Plugin<MyPlugin>() {
      public MyPlugin init(GQuery gq) {
        return new MyPlugin(gq);
      }
    });
  }

  // Initialization
  public MyPlugin(GQuery gq) {
    super(gq);
  }

  // This pluggin adds this to the set of available methods in GQuery
  public MyPlugin apply() {
    hover(new Function() {
      public void f(Element e) {
        $(e).css("color", "blue").as(Effects).stop().animate("fontSize: '+=10px'");
      }
    }, new Function() {
      public void f(Element e) {
        $(e).css("color", "").as(Effects).stop().animate("fontSize: '-=10px'");
      }
    });
    return this;
  }

}
