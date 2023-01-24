package com.hnx.base.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hnx.base.shared.model.ActionInfo;
import com.hnx.base.shared.model.ActivityInfo;
import com.hnx.base.shared.model.IBasic;
import com.hnx.base.shared.model.Product;
import com.hnx.base.shared.model.UserInfo;
import com.hnx.base.shared.model.Video;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface DataServiceAsync {

	void loginFromSession(AsyncCallback<UserInfo> callback);

	void logout(AsyncCallback<Void> callback);

	void save(IBasic iBasic, AsyncCallback<IBasic> callback);

	void saves(List<IBasic> iBasics, AsyncCallback<List<IBasic>> callback);

	void delete(IBasic iBasic, AsyncCallback<Void> callback);

	void deletes(List<IBasic> iBasics, AsyncCallback<Void> callback);

	void getAllProducts(AsyncCallback<List<Product>> callback);

	void getActivities(AsyncCallback<List<ActivityInfo>> callback);

	void getActions(AsyncCallback<List<ActionInfo>> callback);

	void getVideos(AsyncCallback<List<Video>> callback);
}
