package com.hnx.base.client.activities.settings;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.hnx.base.client.activities.basic.BasicViewImpl;

public class SettingViewImpl extends BasicViewImpl implements SettingView {

	private static SettingViewImplUiBinder uiBinder = GWT.create(SettingViewImplUiBinder.class);

	interface SettingViewImplUiBinder extends UiBinder<Widget, SettingViewImpl> {
	}
	
	public SettingViewImpl() {
		super();
		setView(uiBinder.createAndBindUi(this));
	}
}
