package gwtws.seo.client;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class TestMenu extends GWTTestCase {
    
    static String HTML = 
    "   <ul id='u1' class='menuContainer'>" +
    "     <li id='l1'><a id='a1' class='defaultItem' href='#id1'>SA</a></li>" +
    "     <li id='l2'>" +
    "       <a id='a2' href='#'>SUB</a>" +
    "       <ul id='u2' class='submenu'>" +
    "         <li id='la'><a id='aa' href='#section-B'>SB</a></li>" +
    "         <li id='lb'><a id='ab' href='#section-C'>SC</a></li>" +
    "       </ul>" +
    "     </li>" +
    "     <li id='l3'><a id='a3' href='#section-D'>SC[SD]</a></li>" +
    "     <li id='l4'><a id='a4' href='#section-E'>SE</a></li>" +
    "   </ul>" +
    "   <div id='content'>" +
    "      <div id='id1'>SA <a href='http://EX'>EX</a></div>" +
    "      <div id='section-B'>SB <a id='c1' href='#section-D'>SC[SD]</a></div>" +
    "      <div id='section-C'>SC <div id='section-D'>SD</div></div>" +
    "      <div id='section-E'>SE <a id='c2' href='PG'>PG</a></div>" +
    "   </div>     "
    ;


  public String getModuleName() {
    return "gwtws.seo.Test";
  }

  Menu mnu;
  public void gwtSetUp() {
	  RootPanel.get().clear();
      RootPanel.get().add(new HTML(HTML));
      mnu = new Menu();
      mnu.onModuleLoad();
  }
  
  public void testStartingServerAndBrowser() {
	  assertTrue(true);
  }
  
  public void testMenu() {
      
      assertEquals(5, mnu.menuLinks.size());
      assertEquals("a1", mnu.menuLinks.get("id1").get(0).getId());
      assertEquals("aa", mnu.menuLinks.get("section-B").get(0).getId());
      assertEquals("ab", mnu.menuLinks.get("section-C").get(0).getId());
      assertEquals("a3", mnu.menuLinks.get("section-D").get(0).getId());
      assertEquals("a4", mnu.menuLinks.get("section-E").get(0).getId());
   
      assertEquals(4, mnu.sections.size());
      assertEquals("id1", mnu.sections.get("id1").get(0).getId());
      
      assertEquals(7, mnu.subsections.size());
      assertEquals("section-C", mnu.subsections.get("section-D").get(0).getId());

      assertEquals(6, mnu.menuContainers.size());
      assertEquals("l1", mnu.menuContainers.get($("#a1").get(0)).get(0).getId());
      assertEquals("lb", mnu.menuContainers.get($("#ab").get(0)).get(0).getId());
      
      assertEquals(2, mnu.submenuContainers.size());
      assertEquals("u2", mnu.submenuContainers.get($("#aa").get(0)).get(0).getId());
      assertEquals("u2", mnu.submenuContainers.get($("#ab").get(0)).get(0).getId());
      
      assertEquals(2, mnu.submenuParentItems.size());
      assertEquals("l2", mnu.submenuParentItems.get($("#aa").get(0)).get(0).getId());
      assertEquals("l2", mnu.submenuParentItems.get($("#ab").get(0)).get(0).getId());
      
      assertEquals(1, mnu.submenuSiblings.size());
      assertEquals("u2", mnu.submenuSiblings.get($("#a2").get(0)).get(0).getId());
      

      assertEquals("When document loads, the item with id1 its selected", "current", $("#l1").get(0).getClassName());
      assertFalse($("#u2").visible());
      assertEquals("", DOM.getElementById("l2").getClassName());
      assertNotSame("none", DOM.getElementById("id1").getStyle().getProperty("display"));
      
      
      //GQuery.$("#ab").trigger(Event.ONCLICK);
      mnu.showSection(GQuery.$("#ab").get(0));
      
      assertEquals("First Item shouldn't have current class", "", DOM.getElementById("l1").getClassName());
      assertEquals("Submenu should be visible", "", DOM.getElementById("u2").getStyle().getProperty("display"));
      assertEquals("Parent submenu item should be selected", "current", DOM.getElementById("l2").getClassName());
      assertEquals("current submenu sould be selected", "current", DOM.getElementById("lb").getClassName());
      assertEquals("First section should be hidden", "none", DOM.getElementById("id1").getStyle().getProperty("display"));
      assertEquals("Current section should be shown", "", DOM.getElementById("section-C").getStyle().getProperty("display"));
      
      //GQuery.$("#a3").trigger(Event.ONCLICK);
      mnu.showSection(GQuery.$("#a3").get(0));
      
      assertEquals("", DOM.getElementById("l1").getClassName());
      assertEquals("none", DOM.getElementById("u2").getStyle().getProperty("display"));
      assertEquals("", DOM.getElementById("l2").getClassName());
      assertEquals("", DOM.getElementById("lb").getClassName());
      assertEquals("current", DOM.getElementById("l3").getClassName());
      assertEquals("none", DOM.getElementById("id1").getStyle().getProperty("display"));
      assertNotSame("none", DOM.getElementById("section-C").getStyle().getProperty("display"));
      
  }

}
