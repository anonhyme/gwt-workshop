package gwtws.seo.client;


import static com.google.gwt.query.client.GQuery.$;

import java.util.HashMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.Event;

/**
 * Non-obstructive GWT script for handling menus in mirai pages.
 *  
 * It needs basic conventions in the html structure:  @see com.miraiespana.seo.client.TestMenu 
 *  
 */
public class Menu implements EntryPoint {

      protected static String DEFAULT_SECTION = ".defaultItem";
      protected HashMap<String, GQuery> menuLinks = new HashMap<String, GQuery>();
      protected HashMap<Element, GQuery> menuContainers = new HashMap<Element, GQuery>();
      protected HashMap<Element, GQuery> submenuContainers = new HashMap<Element, GQuery>();
      protected HashMap<Element, GQuery> submenuParentItems = new HashMap<Element, GQuery>();
      protected HashMap<Element, GQuery> submenuSiblings = new HashMap<Element, GQuery>();
      protected HashMap<String, GQuery> sections = new HashMap<String, GQuery>();
      protected HashMap<String, GQuery> subsections = new HashMap<String, GQuery>();
      
      public void onModuleLoad() {
          fillHashtables();
          $("a").click(new Function() {
              public boolean f(Event e) {
            	  return showSection(e.getCurrentEventTarget().<Element>cast());
              }
          });
          showSection($(DEFAULT_SECTION).get(0));
      }

      void selectCurrent(Element e) {
          $(".current").removeClass("current");
          $(".submenu").hide();

          if (menuContainers.get(e) != null)
              menuContainers.get(e).addClass("current");
          if (submenuSiblings.get(e) != null)
              submenuSiblings.get(e).show();
          if (submenuContainers.get(e) != null) {
              submenuContainers.get(e).addClass("current");
              submenuContainers.get(e).show();
              if (submenuParentItems.get(e) != null)
                  submenuParentItems.get(e).addClass("current");
          }
      }

      boolean showSection(String id) {
          return (menuLinks.get(id) == null ) ? false : showSection(menuLinks.get(id).get(0));
      }
      boolean showSection(Element e) {
          if (isExternalLink(e)) return true;
          
          String id = e.getNodeName().equalsIgnoreCase("A") ? getLinkHash(e) : e.getId();
          if (id.length() == 0 || (sections.get(id) == null && subsections.get(id) == null)) {
              selectCurrent(e);
          } else {
              $("#content > div[id]").hide();
              GQuery section = subsections.get(id) != null ? subsections.get(id) : sections.get(id);

              String linkid =  menuLinks.get(id) != null ? id : section.get(0).getId();
              selectCurrent(menuLinks.get(linkid).get(0));
              
              if (!id.equals(linkid)) {
                  section.show();
                  return true;
              }
              
              section.fadeIn(900);
          }
          return false;
      }

      void fillHashtables() {
          $(".menuContainer a").each(new Function() {
              public void f(Element e) {
                  String section = getLinkHash(e);
                  if (section.length() > 0)
                      menuLinks.put(section, $(e));
                  GQuery container = $(e).parent("li");
                  if (container.size() == 1)
                      menuContainers.put(e, container);
                  GQuery submenuSibling = $(e).next("ul");
                  if (submenuSibling.size() == 1)
                      submenuSiblings.put(e, submenuSibling);
                  GQuery submenu = $(e).parents(".submenu");
                  if (submenu.size() == 1) {
                      submenuContainers.put(e, submenu);
                      GQuery submenuitem = $(submenu).parent("li");
                      if (submenuitem.size() == 1)
                          submenuParentItems.put(e, submenuitem);
                  }
              }
          });
          $("#content > div").each(new Function() {
              public void f(Element e) {
                  final GQuery sect = $(e);
                  sections.put(e.getId(), sect);
                  $("*[id]", e).each(new Function() {
                      public void f(Element a) {
                          subsections.put(a.getId(), sect);
                      }
                  });
              }
          });
      }

      String getLinkHash (Element a){
          String hrf = a.getAttribute("href");
          return  hrf != null && hrf.contains("#") ? hrf.replaceAll("^.*?#", "") : "";
      }
      
      native boolean isExternalLink (Element a) /*-{
      	 try {
         var r = /^\//;
         return ($wnd.location.pathname.replace(r, '') != a.pathname.replace(r, ''));
      	 } catch(e) {
      	 	alert(">> " + e + " " + a + " " + a.id);
      	 	return false;
      	 }
      }-*/;
}
