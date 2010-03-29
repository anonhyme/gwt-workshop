package gwtws.seo.client;

import static com.google.gwt.query.client.GQuery.$;
import java.util.Arrays;

import com.google.code.p.gwtchismes.client.GWTCHelper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;

public class Maps implements EntryPoint {

  static final String ID_MASHUP = "mirai-maps";
  static String ID_MAP = "mymap";

  private static String wsHost = "";

  static final String URL_ID_TO_COORDINATES = "/maps/%ID%.js";
  static String URL_GMAP_CONFIG = "/map.conf.txt";
  static String URL_GOOGLE_API = "http://maps.google.com/maps?file=api&v=2.x&async=2&sensor=false&gwt=1&callback=window.api_loaded&key=";

  static GoogleMap myGmap = new GoogleMap();
  static boolean apiloaded = false;

  
  
  
  /**
   * Se llama cuando el documento esta listo (document.onLoad)
   */
  public void onModuleLoad() {
    exportJsMethods();
    wsHost = determineWebServicesHost(GWTCHelper.getScriptLocation(ID_MASHUP));
    getParameterConfiguration();
  }
  
  static String determineWebServicesHost(String loc) {
    String url = loc != null && loc.contains("http") ? loc : Window.Location.getHost();
    for (String host : Arrays.asList(new String[] { "www", "staging", "integration" })) {
      if (url.contains(host))
        return "http://" + host + ".hotelsearch.com";
    }
    return "http://internaldev.hotelsearch.com";
  }

  private static void showMessage(String message) {
    $("#" + ID_MAP).append("<span>" + message + "</span>");
  }

  /**
   * Buscar la configuracion de los parametros y pintar el mapa si hay suficiente informacion
   */
  private void getParameterConfiguration() {
    showMessage("Getting map configuration from parameters... " + GWTCHelper.getScriptLocation(ID_MASHUP));
    myGmap.fillDataFromUrl(GWTCHelper.getScriptLocation(ID_MASHUP));
    if (myGmap.hasEnoughInfo()) {
      showMap();
    } else {
      getAjaxConfiguration();
    }
  }

  /**
   * Traerse por ajax el fichero de configuracion y pintar el mapa si hay suficiente informacion
   */
  private void getAjaxConfiguration() {
    showMessage("Getting map configuration from " + URL_GMAP_CONFIG + "...");
    try {
      RequestBuilder req = new RequestBuilder(RequestBuilder.GET, URL_GMAP_CONFIG);
      req.sendRequest("", new RequestCallback() {

        public void onResponseReceived(Request request, Response response) {
          myGmap.fillDataFromProperties(response.getText());
          if (myGmap.hasEnoughInfo()) {
            showMap();
          } else if (myGmap.idhotel > 0) {
            getHsWsConfiguration();
          }
        }
        public void onError(Request request, Throwable exception) {
          showMessage("Error getting map configuration from " + URL_GMAP_CONFIG );
          if (myGmap.idhotel > 0) {
            getHsWsConfiguration();
          }
        }
      });
    } catch (Exception e) {
      showMessage("Error: " + e.getMessage());
    }
  }

  /**
   * Traerse por cross-scripting la configuracion si sabemos el id y pintar el mapa si hay suficiente informacion
   */
  private void getHsWsConfiguration() {
    String ws = wsHost + URL_ID_TO_COORDINATES.replaceAll("%ID%", "" + myGmap.idhotel);
    showMessage("Getting map configuration from " + ws);
    GWTCHelper.insertJS("id_to_coordinates", ws);
  }

  /**
   * funcion de callback cuando se pide la configuracion a rails  
   */
  public static void updateMapConfFromWs(double lat, double lng, int zoom, String precission) {
    myGmap.fillDataFromWebService(lat, lng, zoom, precission);
    showMessage("Received info from ws: (" + lat + " " + lng + " " + zoom + " " + precission + ") "+ myGmap);
    if (myGmap.hasEnoughInfo()) {
      showMap();
    } else {
      showMessage("Not enought info to draw the map: " + myGmap);
    }
  }

  /**
   * Dibujar el mapa si el api de google esta listo, o cargar dicho api
   */
  static void showMap() {
    if (!apiloaded) {
      loadGoogleApi();
    } else {
      onApiLoaded();
    }
  }

  /**
   * Cargar el api de google
   */
  static void loadGoogleApi() {
    String url = URL_GOOGLE_API + myGmap.key;
    showMessage("loading google api: " + url);
    GWTCHelper.insertJS("gmap-api", url);
  }


  /**
   * funcion de callback cuando el api de google se ha cargado en el browser
   * si hay informacion suficiente se pinta el mapa.  
   */
  public static void onApiLoaded() {
    showMessage("API loaded");
    apiloaded = true;
    if (myGmap.hasEnoughInfo()) {
      showMessage("Drawing Map, " + myGmap);
      myGmap.draw();
    }
  }

  static native void exportJsMethods() /*-{
      $wnd.update_map = @gwtws.seo.client.Maps::updateMapConfFromWs(DDILjava/lang/String;);
      $wnd.api_loaded = @gwtws.seo.client.Maps::onApiLoaded(); 
    }-*/;

}
