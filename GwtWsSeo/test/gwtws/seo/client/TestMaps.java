package gwtws.seo.client;

import junit.framework.Assert;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class TestMaps extends GWTTestCase {

  static String ID = "mirai-maps";
  static String URL = "test.js?lat=1.1&lng=2.2&key=myKey";
  static String HTML = "  <div id='googlemap'></div> <script id='" + ID + "' type=\"text/javascript\" language=\"javascript\" src='" + URL + "'></script><div id='googlemap'></div>";

  public String getModuleName() {
    return "gwtws.seo.Test";
  }

  public void gwtSetUp() {
    RootPanel.get().add(new HTML(HTML));
  }
  
  public void testStartingServerAndBrowser() {
  }
  
  public void testPropertiesParser() {
    Properties p = Properties.create("_str: 'a', _int: 1234, _float: 1.234, _double: 123123.123456789, _bool: true, _true: 1, _false: false");
    Assert.assertEquals("a", p.get("_str"));
    Assert.assertEquals(1234, p.getInt("_int"));
    Assert.assertEquals(1.234f, p.getFloat("_float"));
    Assert.assertEquals(123123.123456789d, p.getDouble("_double"));
    Assert.assertEquals(true, p.getBoolean("_bool"));
    Assert.assertEquals(true, p.getBoolean("_true"));
    Assert.assertEquals(false, p.getBoolean("_false"));
  }
  
  public void testFillData() {
    GoogleMap map = new GoogleMap();
    Assert.assertEquals(0d, map.lat);
    Assert.assertEquals(0d, map.lng);
    Assert.assertEquals(GoogleMap.DEFAULT_ZOOM, map.zoom);
    Assert.assertEquals(GoogleMap.DEFAULT_PRECISSION, map.precission);
    Assert.assertEquals(0, map.id);
    Assert.assertEquals(GoogleMap.LOCALHOST_KEY, map.key);
    
    Assert.assertEquals(false, map.hasEnoughInfo());
    
    map.fillDataFromUrl("http://localhost:111?lat=1.1");
    Assert.assertEquals(1.1d, map.lat);
    Assert.assertEquals(0.0d, map.lng);
    Assert.assertEquals(GoogleMap.DEFAULT_ZOOM, map.zoom);
    Assert.assertEquals(GoogleMap.DEFAULT_PRECISSION, map.precission);
    Assert.assertEquals(0, map.id);
    Assert.assertEquals(GoogleMap.LOCALHOST_KEY, map.key);
    Assert.assertEquals(false, map.hasEnoughInfo());
    
    String props = "lat: 111.11111111, lng: -222.2222222, zoom: 3, id: 1234, key: 'a_key'";
    map.fillDataFromProperties(props);
    Assert.assertEquals(111.11111111d, map.lat);
    Assert.assertEquals(-222.2222222d, map.lng);
    Assert.assertEquals(3, map.zoom);
    Assert.assertEquals(GoogleMap.DEFAULT_PRECISSION, map.precission);
    Assert.assertEquals(1234, map.id);
    Assert.assertEquals("a_key", map.key);
    Assert.assertTrue(map.hasEnoughInfo());
    
    map.fillDataFromWebService(1.3, 2.4, 5, null);
    Assert.assertEquals(1.3d, map.lat);
    Assert.assertEquals(2.4d, map.lng);
    Assert.assertEquals(5, map.zoom);
    Assert.assertEquals(GoogleMap.DEFAULT_PRECISSION, map.precission);
    Assert.assertEquals(true, map.hasEnoughInfo());
  }

  public void testApplicationMethods() {
    Maps.exportJsMethods();
    Assert.assertTrue(existsNativeFunction("update_map"));
    Assert.assertTrue(existsNativeFunction("api_loaded"));
    String host = Maps.determineWebServicesHost("http://staging.hotel-moderno.com/my.js");
    Assert.assertEquals("http://staging.hotelsearch.com", host);
    host = Maps.determineWebServicesHost("http://integration.hotel-moderno.com/my.js");
    Assert.assertEquals("http://integration.hotelsearch.com", host);
    host = Maps.determineWebServicesHost("http://www.hotel-moderno.com/my.js");
    Assert.assertEquals("http://www.hotelsearch.com", host);
    host = Maps.determineWebServicesHost("http://localhost:1111/my.js");
    Assert.assertEquals("http://internaldev.hotelsearch.com", host);
    
  }
  public void testApplication() {
    Maps.URL_GMAP_CONFIG = "maps.conf.txt";
    final Maps mapApp = new Maps();
    mapApp.onModuleLoad();
    
    delayTestFinish(80000);
    (new Timer() {
      @Override
      public void run() {
        Assert.assertTrue(Maps.apiloaded);
        Assert.assertNotNull(Maps.myGmap.map);
        Assert.assertTrue(Maps.myGmap.map.toString().contains("mapfiles/transparent.png"));
        finishTest();
      }
    }).schedule(10000);
  }
  
  private native boolean existsNativeFunction(String name)/*-{
     return(eval('$wnd.' + name + ' ? true : false')); 
  }-*/;

}
