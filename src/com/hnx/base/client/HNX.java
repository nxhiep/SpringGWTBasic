package com.hnx.base.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.hnx.base.client.activities.AppActivityMapper;
import com.hnx.base.client.activities.AppPlaceHistoryMapper;
import com.hnx.base.client.activities.AsyncActivityManager;
import com.hnx.base.client.activities.AsyncActivityMapper;
import com.hnx.base.client.activities.ClientFactory;
import com.hnx.base.client.activities.ClientFactoryImpl;
import com.hnx.base.client.activities.importVideo.ImportVideoPlace;
import com.hnx.base.shared.model.UserInfo;

public class HNX implements EntryPoint {
	
	public static ClientFactory CLIENT_FACTORY = new ClientFactoryImpl();
	
	public void onModuleLoad() {
//		ClientData.prepareData();
		ClientData.loginFromSession(new AsyncCallback<UserInfo>() {
			
			@Override
			public void onSuccess(UserInfo result) {
				onLoadedUserData();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				onLoadedUserData();
			}
		});
	}

	protected void onLoadedUserData() {
		SimplePanel display = new SimplePanel();
		display.setSize("100%", "100%");
		AsyncActivityMapper activityMapper = new AppActivityMapper(CLIENT_FACTORY);
		AsyncActivityManager activityManager = new AsyncActivityManager(activityMapper, CLIENT_FACTORY.getEventBus());
		activityManager.setDisplay(display);
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(CLIENT_FACTORY.getPlaceController(), CLIENT_FACTORY.getEventBus(), new ImportVideoPlace());
		historyHandler.handleCurrentHistory();
		RootPanel.get("content").add(display);
	}
}
