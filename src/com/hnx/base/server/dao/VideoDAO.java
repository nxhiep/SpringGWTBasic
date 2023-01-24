package com.hnx.base.server.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.hnx.base.shared.model.Video;

public class VideoDAO extends DAO {

	public List<Video> getVideos() {
		return new ArrayList<Video>(ofy().load().type(Video.class).list());
	}
}
