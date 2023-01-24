package com.hnx.base.client.activities;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.hnx.base.client.activities.home.HomeView;
import com.hnx.base.client.activities.settings.SettingView;

public interface ClientFactory {
	PlaceController getPlaceController();
	EventBus getEventBus();
	HomeView getHomeView();
	SettingView getSettingsView();
}
