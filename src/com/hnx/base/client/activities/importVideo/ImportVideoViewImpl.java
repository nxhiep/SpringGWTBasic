package com.hnx.base.client.activities.importVideo;

import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.hnx.base.client.HNX;
import com.hnx.base.client.activities.basic.BasicViewImpl;
import com.hnx.base.client.activities.importVideo.widgets.CreateVideoPopup;
import com.hnx.base.client.event.ActionEvent;
import com.hnx.base.client.event.ActionEvent.Action;
import com.hnx.base.client.view.MyDialog;
import com.hnx.base.shared.model.Video;

public class ImportVideoViewImpl extends BasicViewImpl implements ImportVideoView {

	private static ImportVideoViewImplUiBinder uiBinder = GWT.create(ImportVideoViewImplUiBinder.class);

	interface ImportVideoViewImplUiBinder extends UiBinder<Widget, ImportVideoViewImpl> {
	}
	
	@UiField Button buttonAdd;
	@UiField FlowPanel contentPanel;
	private CreateVideoPopup createVideoPopup;
	
	public ImportVideoViewImpl() {
		super();
		setView(uiBinder.createAndBindUi(this));
		buttonAdd.setIcon(IconType.PLUS);
		buttonAdd.setType(ButtonType.SUCCESS);
		getHeaderPanel().getButtonImport().setVisible(false);
	}
	
	@Override
	public Button getButtonAdd() {
		return buttonAdd;
	}
	
	@Override
	public CreateVideoPopup getCreateVideoPopup() {
		if(createVideoPopup == null) {
			createVideoPopup = new CreateVideoPopup();
		}
		return createVideoPopup;
	}

	@Override
	public void show(List<Video> arg0) {
		contentPanel.clear();
		int index = 0;
		for (Video video : arg0) {
			contentPanel.add(renderItem(video, index++));
		}
	}

	private Widget renderItem(Video video, int index) {
		FlowPanel flowPanel = new FlowPanel();
		flowPanel.addStyleName("video-item");
		flowPanel.add(new HTML("" + (1 + index)));
		String url = video.getImage();
		if(url == null || url.isEmpty()) {
			url = "https://storage.googleapis.com/deploy-temp.appspot.com/images/default.png";
		}
		Image image = new Image(url);
		flowPanel.add(image);
		FlowPanel tools = new FlowPanel();
		tools.setStyleName("tools");
		HTML title = new HTML(video.getName());
		title.setStyleName("title");
		tools.add(title);
		Button buttonEdit = new Button();
		buttonEdit.setIcon(IconType.EDIT);
		tools.add(buttonEdit);
		Button buttonDelete = new Button();
		buttonDelete.setIcon(IconType.TRASH);
		buttonDelete.setMarginLeft(8);
		tools.add(buttonDelete);
		flowPanel.add(tools);
		buttonEdit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				HNX.CLIENT_FACTORY.getEventBus().fireEvent(new ActionEvent(video, Action.EDIT));
			}
		});
		buttonDelete.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				HNX.CLIENT_FACTORY.getEventBus().fireEvent(new ActionEvent(video, Action.DELETE));
			}
		});
		image.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				showVideo(video);
			}
		});
		return flowPanel;
	}

	protected void showVideo(Video video) {
		MyDialog myDialog = new MyDialog();
		String html = "<iframe width=\"100%\" allow=\"autoplay *; fullscreen *\" allowfullscreen=\"true\" frameborder=\"0\" src=\"" + video.getUrl() + "\"></iframe>";
		HTML widget = new HTML(html);
		myDialog.show(video.getName(), widget, "", "Close", new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void arg0) {
				myDialog.hide();
				widget.removeFromParent();
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				myDialog.hide();
				widget.removeFromParent();
			}
		});
	}
}
