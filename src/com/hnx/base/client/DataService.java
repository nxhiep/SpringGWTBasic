package com.hnx.base.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hnx.base.shared.model.ActionInfo;
import com.hnx.base.shared.model.ActivityInfo;
import com.hnx.base.shared.model.IBasic;
import com.hnx.base.shared.model.Product;
import com.hnx.base.shared.model.UserInfo;
import com.hnx.base.shared.model.Video;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("data")
public interface DataService extends RemoteService {

	UserInfo loginFromSession();

	void logout();

	IBasic save(IBasic iBasic);

	List<IBasic> saves(List<IBasic> iBasics);

	void delete(IBasic iBasic);

	void deletes(List<IBasic> iBasics);

	List<Product> getAllProducts();

	List<ActivityInfo> getActivities();

	List<ActionInfo> getActions();

	List<Video> getVideos();
}
