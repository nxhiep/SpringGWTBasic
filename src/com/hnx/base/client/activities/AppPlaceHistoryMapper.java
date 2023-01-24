package com.hnx.base.client.activities;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.hnx.base.client.ClientUtils;
import com.hnx.base.client.activities.basic.BasicPlace;
import com.hnx.base.client.activities.home.HomePlace;
import com.hnx.base.client.activities.settings.SettingPlace;
import com.hnx.base.shared.PlaceToken;

public class AppPlaceHistoryMapper implements PlaceHistoryMapper {

	@Override
	public Place getPlace(String token) {
//		Map<String, String> params = getMapURLSearchParams(token);
		if(token.contains(PlaceToken.SETTINGS_PLACE)) {
			return new SettingPlace();
		}
		return new HomePlace();
	}

	@Override
	public String getToken(Place place) {
		return ((BasicPlace) place).getToken();
	}
	
	public static Map<String, String> getMapURLSearchParams(String url) {
		Map<String, String> map = new HashMap<String, String>();
		JavaScriptObject javaScriptObject = getURLSearchParams(url.substring(url.indexOf("?"), url.length()));
		JSONObject object = new JSONObject(javaScriptObject);
		for(String key : object.keySet()) {
			map.put(key, ClientUtils.getStringValue(object, key));
		}
		return map;
	}
	
	public static native JavaScriptObject getURLSearchParams(String url) /*-{
		var map = {};
		try {
			var mySearchParams = new $wnd.URLSearchParams(url);
			var entries = mySearchParams.entries();
			var result = entries.next();
			while (result && !result.done) {
				map[result.value[0]] = result.value[1];
				result = entries.next();
			}
		} catch(e){
			$wnd.console.log('getURLSearchParams error', e);
		}
		return map;
	}-*/;
}
