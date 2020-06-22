package com.spring.gwt.client.activities.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.spring.gwt.client.activities.basic.BasicViewImpl;

public class HomeViewImpl extends BasicViewImpl implements HomeView {

	private static BasicViewImplUiBinder uiBinder = GWT.create(BasicViewImplUiBinder.class);

	interface BasicViewImplUiBinder extends UiBinder<Widget, HomeViewImpl> {
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
