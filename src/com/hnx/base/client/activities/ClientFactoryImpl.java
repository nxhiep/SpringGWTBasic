package com.hnx.base.client.activities;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.hnx.base.client.activities.home.HomeView;
import com.hnx.base.client.activities.home.HomeViewImpl;
import com.hnx.base.client.activities.settings.SettingView;
import com.hnx.base.client.activities.settings.SettingViewImpl;

public class ClientFactoryImpl implements ClientFactory {
	private SimpleEventBus eventBus;
	private PlaceController placeController;
	
	public ClientFactoryImpl() {
		eventBus = new SimpleEventBus();
		placeController = new PlaceController(eventBus);
	}
	
	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public HomeView getHomeView() {
		return new HomeViewImpl();
	}

	@Override
	public SettingView getSettingsView() {
		return new SettingViewImpl();
	}
}
