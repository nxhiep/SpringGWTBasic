package com.hnx.base.client.activities.importVideo;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.hnx.base.client.ClientData;
import com.hnx.base.client.activities.ClientFactory;
import com.hnx.base.client.activities.basic.BasicActivity;
import com.hnx.base.client.event.ActionEvent;
import com.hnx.base.client.event.ActionEvent.Action;
import com.hnx.base.client.event.ActionEventHandler;
import com.hnx.base.client.view.MyDialog;
import com.hnx.base.client.view.Toaster;
import com.hnx.base.shared.Callback;
import com.hnx.base.shared.model.IBasic;
import com.hnx.base.shared.model.Video;

public class ImportVideoActivity extends BasicActivity {
	
	private ImportVideoView view;
	
	public ImportVideoActivity(Place place, ClientFactory clientFactory) {
		super(place, clientFactory);
	}
	
	@Override
	protected void getParams(Place place) {
		super.getParams(place);
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getImportVideoView();
		super.start(panel, eventBus, view);
	}
	
	@Override
	protected void bind() {
		super.bind();
		addHandlerRegistration(view.getButtonAdd().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				view.getCreateVideoPopup().show(null, new Callback<Video>() {
					
					@Override
					public void onSuccess(Video arg0) {
						onUpdateVideo(arg0);
					}
				});
			}
		}));
		view.getEventBus().addHandler(ActionEvent.TYPE, new ActionEventHandler() {
			
			@Override
			public void onEvent(ActionEvent event) {
				Video video = (Video) event.getIBasic();
				if(event.getAction() == Action.EDIT) {
					view.getCreateVideoPopup().show(video, new Callback<Video>() {
						
						@Override
						public void onSuccess(Video arg0) {
							onUpdateVideo(arg0);
						}
					});
				} else if(event.getAction() == Action.DELETE) {
					MyDialog myDialog = new MyDialog();
					myDialog.show("Remove this video?", null, "OK", "Cancel", new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void arg0) {
							onDelete(video);
							myDialog.hide();
						}
						
						@Override
						public void onFailure(Throwable arg0) {
							myDialog.hide();
						}
					});
				}
			}
		});
	}
	
	@Override
	protected void loadData() {
		ClientData.DATA_SERVICE.getVideos(new AsyncCallback<List<Video>>() {
			
			@Override
			public void onSuccess(List<Video> arg0) {
				view.show(arg0);
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				Toaster.showError("Load data failed " + arg0.getMessage());
			}
		});
	}
	
	private void onDelete(Video video) {
		ClientData.DATA_SERVICE.delete(video, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void arg0) {
				loadData();
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				Toaster.showError("Remove video error: " + arg0.getMessage());
			}
		});
	}
	
	private void onUpdateVideo(Video video) {
		ClientData.DATA_SERVICE.save(video, new AsyncCallback<IBasic>() {
			
			@Override
			public void onSuccess(IBasic arg0) {
				loadData();
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				Toaster.showError("Update video error: " + arg0.getMessage());
			}
		});
	}
	
}
