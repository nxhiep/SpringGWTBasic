package com.hnx.base.client.activities;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.hnx.base.client.activities.basic.BasicActivity;
import com.hnx.base.client.activities.home.HomeActivity;
import com.hnx.base.client.activities.home.HomePlace;
import com.hnx.base.client.activities.settings.SettingActivity;
import com.hnx.base.client.activities.settings.SettingPlace;

public class AppActivityMapper implements AsyncActivityMapper {

	private ClientFactory clientFactory;

	public AppActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void getActivity(final Place place, final ActivityCallbackHandler activityCallbackHandler) {
		if (place instanceof HomePlace) {
			runAsync(activityCallbackHandler, new HomeActivity(place, clientFactory));
		} else if (place instanceof SettingPlace) {
			runAsync(activityCallbackHandler, new SettingActivity(place, clientFactory));
		}
	}

	private void runAsync(final ActivityCallbackHandler activityCallbackHandler, final BasicActivity activity) {
		GWT.runAsync(new RunAsyncCallback() {

			@Override
			public void onFailure(Throwable err) {
				Window.alert("Vui lòng tải lại trang này");
			}

			@Override
			public void onSuccess() {
				activityCallbackHandler.onRecieveActivity(activity);
			}
		});
	}
}
