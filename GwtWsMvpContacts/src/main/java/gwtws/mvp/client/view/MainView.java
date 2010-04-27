package gwtws.mvp.client.view;

import gwtws.mvp.client.presenter.ApplicationController;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainView extends Composite implements ApplicationController.Display {

	static Panel p = RootPanel.get();

	public void addWidget(Widget widget) {
	}

	public void removeWidget(Widget widget) {
	}

	public void showWidget(Widget widget) {
		p.clear();
		p.add(widget);
  }

	public Widget asWidget() {
		return null;
	}
}
