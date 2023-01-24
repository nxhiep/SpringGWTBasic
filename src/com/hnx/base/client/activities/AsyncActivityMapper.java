package com.hnx.base.client.activities;

import com.google.gwt.place.shared.Place;

public interface AsyncActivityMapper{
	void getActivity(Place place, ActivityCallbackHandler activityCallbackHandler);
}
