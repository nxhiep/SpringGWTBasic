package com.hnx.base.client;

import java.util.List;

import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.hnx.base.client.view.MyDialog;
import com.hnx.base.client.view.Toaster;
import com.hnx.base.shared.Callback;
import com.hnx.base.shared.model.IBasic;
import com.hnx.base.shared.model.UserInfo;

public class ClientData {
	public final static DataServiceAsync DATA_SERVICE = GWT.create(DataService.class);
	
	public static void prepareData() {
		if(ClientUtils.isLocalTestMode()) {
			ServiceDefTarget dataServiceDef = (ServiceDefTarget) DATA_SERVICE;
			dataServiceDef.setServiceEntryPoint("https://hnx-dot-deploy-temp.appspot.com/hnx/data");
		}
	}
	
	public static void loginFromSession(AsyncCallback<UserInfo> callback) {
		DATA_SERVICE.loginFromSession(new AsyncCallback<UserInfo>() {
			
			@Override
			public void onSuccess(UserInfo result) {
				LoginManager.setCurrentUser(result);
				if(callback != null) {
					callback.onSuccess(result);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				LoginManager.setCurrentUser(null);
				if(callback != null) {
					callback.onFailure(caught);
				}
			}
		});
	}
	
	public static void save(IBasic iBasic, AsyncCallback<IBasic> callback) {
		DATA_SERVICE.save(iBasic, new AsyncCallback<IBasic>() {
			
			@Override
			public void onSuccess(IBasic result) {
				Toaster.showSuccess("Lưu thành công!");
				if(callback != null) {
					callback.onSuccess(result);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Toaster.showError("Có lỗi xảy ra, thử lại!");
				if(callback != null) {
					callback.onFailure(caught);
				}
			}
		});
	}
	
	public static void saves(List<IBasic> iBasics, AsyncCallback<List<IBasic>> callback) {
		DATA_SERVICE.saves(iBasics, new AsyncCallback<List<IBasic>>() {
			
			@Override
			public void onSuccess(List<IBasic> result) {
				Toaster.showSuccess("Lưu thành công!");
				if(callback != null) {
					callback.onSuccess(result);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Toaster.showError("Có lỗi xảy ra, thử lại!");
				if(callback != null) {
					callback.onFailure(caught);
				}
			}
		});
	}
	
	public static void delete(IBasic iBasic, AsyncCallback<Void> callback) {
		DATA_SERVICE.delete(iBasic, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				Toaster.showSuccess("Xoá thành công!");
				if(callback != null) {
					callback.onSuccess(result);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Toaster.showError("Có lỗi xảy ra, thử lại!");
				if(callback != null) {
					callback.onFailure(caught);
				}
			}
		});
	}
	
	public static void deletes(List<IBasic> iBasics, AsyncCallback<Void> callback) {
		DATA_SERVICE.deletes(iBasics, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				Toaster.showSuccess("Lưu thành công!");
				if(callback != null) {
					callback.onSuccess(result);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Toaster.showError("Có lỗi xảy ra, thử lại!");
				if(callback != null) {
					callback.onFailure(caught);
				}
			}
		});
	}

	public static void loginByPassword(AsyncCallback<Void> asyncCallback) {
		MyDialog myDialog = new MyDialog();
		TextBox textBox = new TextBox();
		textBox.addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent arg0) {
				if(arg0.getNativeEvent().getKeyCode() == 13) {
					onCheckPassword(asyncCallback, textBox.getValue(), new Callback<Void>() {

						@Override
						public void onSuccess(Void arg0) {
							myDialog.hide();
						}
					});
				}
			}
		});
		textBox.getElement().setAttribute("type", "number");
		myDialog.show("Password", textBox, "OK", "Cancel", new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void arg0) {
				onCheckPassword(asyncCallback, textBox.getValue(), new Callback<Void>() {

					@Override
					public void onSuccess(Void arg0) {
						myDialog.hide();
					}
				});
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				myDialog.hide();
			}
		});
	}
	
	private static void onCheckPassword(AsyncCallback<Void> callback, String value, Callback<Void> onHide) {
		if(value.equals("1341")) {
			callback.onSuccess(null);
			onHide.onSuccess(null);
		} else {
			Toaster.showError("Password incorrect!");
		}
	}
}
