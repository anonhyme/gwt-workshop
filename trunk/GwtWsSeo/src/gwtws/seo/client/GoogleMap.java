package gwtws.seo.client;

import com.google.code.p.gwtchismes.client.GWTCHelper;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

public class GoogleMap {
  static String DEFAULT_PRECISSION = "point";
  static int DEFAULT_ZOOM = 15;
  static int DEFAULT_WIDTH = 500;
  static int DEFAULT_HEIGHT = 300;
  static String LOCALHOST_KEY = "LocalHostKey";
  
  double lng = 0;
  double lat = 0;
  int zoom = DEFAULT_ZOOM;
  int width = DEFAULT_WIDTH;
  int height = DEFAULT_HEIGHT;
  int id = 0;
  String precission = DEFAULT_PRECISSION;
  String key = null;
  MapWidget map = null;
  Properties prop = null;
  String host = Window.Location.getHost();

  GoogleMap() {
    if (host.contains("localhost") || host.contains("127.0.")) {
      key = LOCALHOST_KEY;
    }
  }

  public String toString() {
    return " lng:" + lng + " lat:" + lat + " zoom:" + zoom + " id:" + id + " host:" + host + " key:" + key;
  }
  
  public boolean hasEnoughInfo() {
    return lat != 0 && lng != 0 && key != null;
  }

  public void draw() {

    LatLng pos = LatLng.newInstance(lat, lng);
    if (map == null)
      map = new MapWidget();
    

    map.setCenter(pos, zoom);
    map.setSize(width + "px", height + "px");

    map.setUIToDefault();
    
    MarkerOptions options = MarkerOptions.newInstance();
    options.setIcon(Icon.newInstance("hs.png"));
    map.addOverlay(new Marker(pos, options));
    
    Size size = Size.newInstance(width, height);
    MapUIOptions pts = MapUIOptions.newInstance(size);
    pts.setScrollwheel(true);
    map.setUI(pts);
    
    Panel p = RootPanel.get(Maps.ID_MAP) != null ? RootPanel.get(Maps.ID_MAP) : RootPanel.get();
    p.add(map);
    
  }


  public void fillDataFromUrl(String url) {

    String lng = GWTCHelper.getParameterFromUrl(url, "lng");
    if (lng != null)
      this.lng = Double.parseDouble(lng);
    String lat = GWTCHelper.getParameterFromUrl(url, "lat");

    if (lat != null)
      this.lat = Double.parseDouble(lat);
    String id = GWTCHelper.getParameterFromUrl(url, "id");

    if (id != null)
      this.id = Integer.parseInt(id);
    String zoom = GWTCHelper.getParameterFromUrl(url, "zoom");

    if (zoom != null)
      this.zoom = Integer.parseInt(zoom);

    String key = GWTCHelper.getParameterFromUrl(url, "key");
    if (key != null)
      this.key = key;
    
  }

  public void fillDataFromProperties(String props) {
    if (props == null)
      return;

    try {
      prop = Properties.create(props);
    } catch (Exception e) {
      System.out.println("Exception: " + e);
      return;
    }

    lng = prop.getDouble("lng");
    lat = prop.getDouble("lat");
    zoom = prop.getInt("zoom");
    id = prop.getInt("id");

    String key = prop.get("key");
    if (key.length() > 0)
      this.key = key;

    System.out.println(this);
  }

  public void fillDataFromWebService(double lat, double lng, int zoom, String precission) {
    this.lat = lat;
    this.lng = lng;
    this.zoom = zoom;
    if (precission != null)
      this.precission = precission;
    System.out.println(this);
  }

}
