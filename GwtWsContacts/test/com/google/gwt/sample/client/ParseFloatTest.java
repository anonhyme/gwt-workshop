package com.google.gwt.sample.client;

import com.google.gwt.junit.client.GWTTestCase;

public class ParseFloatTest extends GWTTestCase {
  
  public String getModuleName() {
    return "com.google.gwt.sample.Sample";
  }
  
  public void testParseFloat(){
    assertEquals("1000000", parseFloatImpl("1e6"));
    assertEquals(1f, Float.parseFloat("1 "));
  }
  
  private native String parseFloatImpl(String s) /*-{
    return "" + parseFloat(s);
  }-*/;
}
