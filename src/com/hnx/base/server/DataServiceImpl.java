package com.hnx.base.server;

import java.util.List;

import com.hnx.base.client.DataService;
import com.hnx.base.server.dao.ProductDAO;
import com.hnx.base.server.dao.SettingDAO;
import com.hnx.base.server.dao.UserDAO;
import com.hnx.base.server.dao.VideoDAO;
import com.hnx.base.shared.model.ActionInfo;
import com.hnx.base.shared.model.ActivityInfo;
import com.hnx.base.shared.model.IBasic;
import com.hnx.base.shared.model.Product;
import com.hnx.base.shared.model.UserInfo;
import com.hnx.base.shared.model.Video;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DataServiceImpl extends CustomRemoteServiceServlet implements DataService {

	protected UserDAO USER_DAO = new UserDAO();
	protected ProductDAO PRODUCT_DAO = new ProductDAO();
	protected SettingDAO SETTING_DAO = new SettingDAO();
	
	@Override
	public IBasic save(IBasic iBasic) {
		return USER_DAO.save(iBasic);
	}
	
	@Override
	public List<IBasic> saves(List<IBasic> iBasics) {
		return USER_DAO.saves(iBasics);
	}
	
	@Override
	public void delete(IBasic iBasic) {
		USER_DAO.delete(iBasic);
	}
	
	@Override
	public void deletes(List<IBasic> iBasics) {
		USER_DAO.deletes(iBasics);
	}
	
	@Override
	public UserInfo loginFromSession() {
		return USER_DAO.loginFromSession(this.getThreadLocalRequest(), this.getThreadLocalResponse());
	}

	@Override
	public void logout() {
		USER_DAO.logout(this.getThreadLocalRequest(), this.getThreadLocalResponse());
	}
	
	@Override
	public List<Product> getAllProducts() {
		return PRODUCT_DAO.getAllProducts();
	}

	@Override
	public List<ActivityInfo> getActivities() {
		return SETTING_DAO.getActivities();
	}
	
	@Override
	public List<ActionInfo> getActions() {
		return SETTING_DAO.getActions();
	}

	@Override
	public List<Video> getVideos() {
		return new VideoDAO().getVideos();
	}
}
