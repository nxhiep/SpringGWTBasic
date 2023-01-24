package com.hnx.base.client.activities.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.hnx.base.client.activities.basic.BasicViewImpl;

public class HomeViewImpl extends BasicViewImpl implements HomeView {

	private static HomeViewImplUiBinder uiBinder = GWT.create(HomeViewImplUiBinder.class);

	interface HomeViewImplUiBinder extends UiBinder<Widget, HomeViewImpl> {
	}
	
	public HomeViewImpl() {
		super();
		setView(uiBinder.createAndBindUi(this));
		setActiveTab(this.getBasicLayout().getButtonHome());
	}
	
	@Override
	public void refreshView() {
		super.refreshView();
	}
}
