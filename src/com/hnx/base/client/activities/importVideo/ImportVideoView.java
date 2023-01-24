package com.hnx.base.client.activities.importVideo;

import java.util.List;

import org.gwtbootstrap3.client.ui.Button;

import com.hnx.base.client.activities.basic.BasicView;
import com.hnx.base.client.activities.importVideo.widgets.CreateVideoPopup;
import com.hnx.base.shared.model.Video;

public interface ImportVideoView extends BasicView {

	Button getButtonAdd();
	CreateVideoPopup getCreateVideoPopup();
	void show(List<Video> arg0);
}
