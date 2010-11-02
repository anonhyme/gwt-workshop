package com.decharlas.gquerysample.client;
import com.google.gwt.dom.client.Element;
import com.google.gwt.query.client.Function;
import static com.google.gwt.query.client.GQuery.*;
import static com.google.gwt.query.client.plugins.Effects.Effects;


import com.google.gwt.core.client.EntryPoint;

/**
 * Example code for a GwtQuery application
 */
public class GQuerySample implements EntryPoint {

  public void onModuleLoad() {
    
    $("div")
    .hover(new Function() {
      public void f(Element e) {
        $(e).css("color", "blue").as(Effects).stop().animate("fontSize: '+=10px'");
      }
    }, new Function() {
      public void f(Element e) {
        $(e).css("color", "").as(Effects).stop().animate("fontSize: '-=10px'");
      }
    });
  }

}
