package com.hnx.base.client.activities.importVideo.widgets;

import org.gwtbootstrap3.client.ui.TextArea;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.hnx.base.client.view.MyDialog;
import com.hnx.base.client.view.Toaster;
import com.hnx.base.client.view.UploaderPanel;
import com.hnx.base.shared.Callback;
import com.hnx.base.shared.model.Video;

public class CreateVideoPopup extends MyDialog {

	private static CreateVideoPopupUiBinder uiBinder = GWT.create(CreateVideoPopupUiBinder.class);

	interface CreateVideoPopupUiBinder extends UiBinder<Widget, CreateVideoPopup> {
	}
	
	@UiField TextBox nameBox, categoryBox, videoBox, imageBox;
	@UiField UploaderPanel uploaderImage, uploaderVideo;
	@UiField TextArea descriptionBox;
	private Video video = null;
	
	public CreateVideoPopup() {
		setContent(uiBinder.createAndBindUi(this));
		imageBox.getElement().getStyle().setMarginLeft(8, Unit.PX);
		videoBox.getElement().getStyle().setMarginLeft(8, Unit.PX);
		uploaderImage.setCompleteHandler(new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String arg0) {
				imageBox.setText(arg0);
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				Toaster.showError(arg0.getMessage());
			}
		});
		uploaderVideo.setCompleteHandler(new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String arg0) {
				videoBox.setText(arg0);
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				Toaster.showError(arg0.getMessage());
			}
		});
	}

	public void show(Video video, Callback<Video> callBack) {
		this.video = video;
		updateData(video);
		super.show(video == null ? "Create Video" : ("Edit " + video.getName()), null, 
			"Save", "Cancel", new AsyncCallback<Void>() {
				
				@Override
				public void onSuccess(Void arg0) {
					if(validate()) {
						callBack.onSuccess(getVideo());
						hide();
					}
				}
				
				@Override
				public void onFailure(Throwable arg0) {
					hide();
				}
			});
	}

	private void updateData(Video video) {
		if(video == null) {
			return;
		}
		nameBox.setValue(video.getName());
		descriptionBox.setValue(video.getDescription());
		categoryBox.setValue(video.getCategory());
		videoBox.setValue(video.getUrl());
		imageBox.setValue(video.getImage());
	}

	protected boolean validate() {
		Video video = getVideo();
		if(video.getName().isEmpty()) {
			Toaster.showError("Video name empty!");
			return false;
		}
		if(video.getUrl().isEmpty()) {
			Toaster.showError("Video url empty!");
			return false;
		}
		return true;
	}

	protected Video getVideo() {
		if(video == null) {
			video = new Video();
		}
		String name = nameBox.getValue();
		String description = descriptionBox.getValue();
		String category = categoryBox.getValue();
		String videoUrl = videoBox.getValue();
		String imageUrl = imageBox.getValue();
		video.setName(name);
		video.setDescription(description);
		video.setCategory(category);
		video.setUrl(videoUrl);
		video.setImage(imageUrl);
		return video;
	}

}
