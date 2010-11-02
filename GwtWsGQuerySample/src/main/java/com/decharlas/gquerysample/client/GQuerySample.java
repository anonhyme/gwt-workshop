package com.decharlas.gquerysample.client;

import static com.google.gwt.query.client.GQuery.*;
import static com.google.gwt.query.client.plugins.Effects.Effects;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.plugins.Effects.Speed;
import com.google.gwt.user.client.Window;

/**
 * Example code for a GwtQuery application
 */
public class GQuerySample implements EntryPoint {

	public void onModuleLoad() {

		$("#hello").hover(new Function() {
			public void f(Element e) {
				$(e).css("color", "blue").as(Effects).stop().animate(
				    "fontSize: '+=10px'");
			}
		}, new Function() {
			public void f(Element e) {
				$(e).css("color", "").as(Effects).stop().animate("fontSize: '-=10px'");
			}
		});
		
		// hide all code example
		$("pre").hide();
		// configure links to show/hide code
		$("a").click(new Function() {
			public void f(Element e) {
				String letter = e.getClassName().substring(e.getClassName().length()-1);
				$("pre.code" + letter).toggle();
			}
		});

		// Code for example A
		$("input.buttonAsize").click(new Function() {
			public void f(Element e) {
				Window.alert("" + $("div.contentToChange").find("p").size());
			}
		});
		// Code for example B
		$("input.buttonBslidedown").click(new Function() {
			public void f(Element e) {
				$("div.contentToChange").find("p.firstparagraph").as(Effects).clipDown(
				    Speed.SLOW);
			}
		});
		$("input.buttonBslideup").click(new Function() {
			public void f(Element e) {
				$("div.contentToChange").find("p.firstparagraph").as(Effects).clipUp(
				    Speed.SLOW);
			}
		});
		// Code for example C
		$("input.buttonCAdd").click(new Function() {
			public void f(Element e) {
				$("div.contentToChange p:not(.alert)").append("<strong class='addedtext'> This text was just appended to this paragraph</strong>");
			}
		});
		$("input.buttonCRemove").click(new Function() {
			public void f(Element e) {
				$("strong.addedtext").remove();
			}
		});
		// Code for example D
		$("input.buttonDhide").click(new Function() {
			public void f(Element e) {
				$("div.contentToChange p.thirdparagraph").as(Effects).fadeOut(Speed.SLOW);
			}
		});
		// Code for example E
		$("input.buttonEitalics").click(new Function() {
			public void f(Element e) {
				$("div.contentToChange em").css($$("{color:'#993300', fontWeight:'bold'}"));
			}
		});
		// Code for example F
		$("input.buttonFaddclass").click(new Function() {
			public void f(Element e) {
				$("p.fifthparagraph").addClass("changeP");
			}
		});
		$("input.buttonFremoveclass").click(new Function() {
			public void f(Element e) {
				$("p.fifthparagraph").removeClass("changeP");
			}
		});
	}

}
