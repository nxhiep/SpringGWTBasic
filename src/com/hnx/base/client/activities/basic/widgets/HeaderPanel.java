package com.hnx.base.client.activities.basic.widgets;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HeaderPanel extends Composite {

	private static HeaderPanelUiBinder uiBinder = GWT.create(HeaderPanelUiBinder.class);

	interface HeaderPanelUiBinder extends UiBinder<Widget, HeaderPanel> {
	}
	
	@UiField Button buttonImport;
	@UiField TextBox headerSearchBox;

	public HeaderPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		headerSearchBox.setPlaceholder("Tìm kiếm...");
	}

	public Button getButtonImport() {
		return buttonImport;
	}
}
