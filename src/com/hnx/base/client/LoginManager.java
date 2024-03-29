package com.hnx.base.client;

import com.hnx.base.shared.Config;
import com.hnx.base.shared.model.UserInfo;

public class LoginManager {
	
	private static UserInfo currentUser;
	
	public static void setCurrentUser(UserInfo currentUser) {
		LoginManager.currentUser = currentUser;
	}
	
	public static UserInfo getCurrentUser() {
		return currentUser;
	}
	
	public static boolean isLogedIn() {
		return getCurrentUser() != null && getCurrentUser().isLogedIn();
	}
	
	public native static void openRegisterDialog() /*-{
		typeof $wnd.openRegisterDialog !== 'undefined' && $wnd.openRegisterDialog();
	}-*/;
	
	public native static void openLoginDialog() /*-{
		typeof $wnd.openLoginDialog !== 'undefined' && $wnd.openLoginDialog();
	}-*/;

	public static String getCurrentUserId() {
		return isLogedIn() ? getCurrentUser().getId() : Config.TEXT_EMPTY;
	}
}
