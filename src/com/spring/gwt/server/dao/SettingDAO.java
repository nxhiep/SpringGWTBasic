package com.spring.gwt.server.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.spring.gwt.shared.model.ActionInfo;
import com.spring.gwt.shared.model.ActivityInfo;

public class SettingDAO extends DAO {

	public List<ActivityInfo> getActivities() {
		return new ArrayList<ActivityInfo>(ofy().load().type(ActivityInfo.class).list());
	}

	public List<ActionInfo> getActions() {
		return new ArrayList<ActionInfo>(ofy().load().type(ActionInfo.class).list());
	}
	
}
