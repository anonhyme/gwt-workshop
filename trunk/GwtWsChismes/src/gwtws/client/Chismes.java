package gwtws.client;

import com.google.code.p.gwtchismes.client.GWTCBox;
import com.google.code.p.gwtchismes.client.GWTCButton;
import com.google.code.p.gwtchismes.client.editor.GWTCColorPicker;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

public class Chismes implements EntryPoint {
	public void onModuleLoad() {

		Button b = new GWTCButton("Click me");
		Panel box = new GWTCBox();
		box.add(b);
		

		final GWTCColorPicker picker = new GWTCColorPicker();
		picker.addValueChangeHandler(new ValueChangeHandler<GWTCColorPicker>() {
			@Override
			public void onValueChange(ValueChangeEvent<GWTCColorPicker> event) {
				Window.alert(event.getValue().getColor());
				picker.hide();
			}
		});

		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				picker.center();
			}
		});

		RootPanel.get().add(box);
	}
}
