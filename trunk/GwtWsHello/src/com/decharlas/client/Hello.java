package com.decharlas.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class Hello implements EntryPoint {

	public void onModuleLoad() {
		
		FlexTable table = new FlexTable();
		
		final DialogBox dialog = new DialogBox();
		dialog.setAnimationEnabled(true);
		dialog.setAutoHideEnabled(true);
		dialog.add(new HTML("Hello World"));
		
		
		Button button1 = new Button("Println");
		button1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				System.out.println("Hello World");
				Window.alert("A text should be written in the develop console");
			}
		});
		Button button2 = new Button("Dialog");
		button2.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialog.center();
			}
		});
		
		table.setWidget(0, 0, button1);
		table.setText(1,1, "Whatever");
		table.setWidget(2, 2, button2);
		
		RootPanel.get().add(table);
	}
}
